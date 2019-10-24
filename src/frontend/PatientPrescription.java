package frontend;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import backend.HospitalRecord;
import backend.Patient;
import backend.Physician;
import backend.Prescription;


import com.example.hospitalapplication.R;

public class PatientPrescription extends Fragment implements OnClickListener{

	private ArrayList<String> displayPrescription = new ArrayList<String>();
	private ArrayAdapter<String> ListAdapter;
	private ListView lv;
	Patient patient;
	Physician p;
	View rootView;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.patient_prescription,
				container, false);
		Button add = (Button) rootView.findViewById(R.id.BTaddprescription);
		add.setOnClickListener(this);
		
		// Get Patient from previous activity
		int key = getActivity().getIntent().getExtras().getInt("PatientHCNKey"); 
		patient = HospitalRecord.getListOfPatients().get(Integer.toString(key));
		boolean isNurse = getActivity().getIntent().getExtras().getBoolean("isNurse");
		if (isNurse){
			add.setVisibility(View.INVISIBLE);
		} else {
			p = new Physician();
		}
		
		// Setup List Adapters and List View
		lv = (ListView) rootView.findViewById(R.id.LVPrescription); 
		displayPrescription.add(" ");
		ListAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, displayPrescription);
		lv.setAdapter(ListAdapter);
		
		// Initialize diplayPrescription
		updateList();
		
		return rootView;
	}	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.BTaddprescription:
			EditText ETprescription_data = (EditText) rootView.findViewById(R.id.ETPrescription);
			String medName = ETprescription_data.getText().toString();
			EditText ETmed_instruction = (EditText) rootView.findViewById(R.id.ETMedicationInstructions);
			String med_instruction =ETmed_instruction.getText().toString();
			if (!medName.equals("") && !med_instruction.equals("")){
				p.updatePrescription(new Date(), patient, medName, med_instruction);
				updateList();
				ETprescription_data.setText("");
				ETmed_instruction.setText("");
			} else {
				Toast.makeText(getActivity(), "Can't be empty string", Toast.LENGTH_LONG).show();
			}
		}
	}
	
	public void updateList(){
		displayPrescription.clear();
		Iterator<Prescription> it = patient.getPrescriptionMap().values().iterator();
		while (it.hasNext()){
			displayPrescription.add(it.next().toString());
		}
		ListAdapter.notifyDataSetChanged();
	}
}
