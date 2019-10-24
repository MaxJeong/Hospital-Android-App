package frontend;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import backend.HospitalRecord;
import backend.Nurse;
import backend.Patient;
import backend.Symptoms;

import com.example.hospitalapplication.R;

public class PatientSymptoms extends Fragment{

	Patient patient;
	Nurse nurse = new Nurse();

	ArrayList<String> displayS = new ArrayList<String>();
	ArrayAdapter<String> SListAdapter;
	private ListView Slv;
	
	TextView DateSeen,Urgency;
	View rootView;
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.patient_symptoms,
				container, false);
		
		// Get Patient from previous activity
		int key = getActivity().getIntent().getExtras().getInt("PatientHCNKey"); 
		patient = HospitalRecord.getListOfPatients().get(Integer.toString(key));
		
		boolean isNurse = getActivity().getIntent().getExtras()
				.getBoolean("isNurse");
		
		// Setup OnClickListener
		Button updateS = (Button) rootView.findViewById(R.id.BTupdate_symptoms);
		updateS.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()){
				case R.id.BTupdate_symptoms:
					// Retrieve fields from EditText
					EditText Description = (EditText) rootView.findViewById(R.id.ETSymptom);
					
					if (Description.getText().toString().equals("")){
						Context context = getActivity();
						CharSequence text = "Symptoms cannot be empty";
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(context, text, duration);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					} else {
						// Call backend function
						Symptoms symptom = nurse.updateSymptoms(new Date(), patient,
								Description.getText().toString());
						
						// Clear EditText
						Description.setText("");
						
						// Update List of Symptoms
						displayS.add(symptom.displayString());
						SListAdapter.notifyDataSetChanged();
					}
				}
			}	
			
		});
		
		// Setup List of Symptoms
		Slv = (ListView) rootView.findViewById(R.id.LISTsymptoms); 
		Iterator<Map.Entry<Date, Symptoms>> ITsymptoms = 
				patient.getSymptomsMap().entrySet().iterator();
		while (ITsymptoms.hasNext()){
			Entry<Date, Symptoms> SSet = ITsymptoms.next();
			displayS.add(SSet.getValue().displayString());
		}
		SListAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, displayS);
		Slv.setAdapter(SListAdapter);
		
		if (!isNurse) {
			updateS.setVisibility(View.INVISIBLE);
		}
		
		return rootView;
	}
}
