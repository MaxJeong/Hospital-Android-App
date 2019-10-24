package frontend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.annotation.SuppressLint;
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
import backend.VitalSigns;

import com.example.hospitalapplication.R;

public class PatientVS extends Fragment{

	Patient patient;
	Nurse nurse = new Nurse();
	String FILENAME = "patientfile.txt";
	
	@SuppressLint("SimpleDateFormat")
	public static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateandtimeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	ArrayList<String> displayVS = new ArrayList<String>();
	ArrayAdapter<String> VSListAdapter;
	private ListView VSlv;
	
	TextView DateSeen,Urgency;
	View rootView;
	
	//This is a helper method that checks if the input string 
	//contains only numeric characters
	public static boolean isNumeric(String str) {  
		try {  
		    @SuppressWarnings("unused")
			double d = Double.parseDouble(str);  
		} catch(NumberFormatException nfe) {  
		    return false;  
		}  
	    return true;  
	}


	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.patient_vital_signs,
				container, false);
		
		// Get Patient from previous activity
		int key = getActivity().getIntent().getExtras().getInt("PatientHCNKey"); 
		patient = HospitalRecord.getListOfPatients().get(Integer.toString(key));
		
		boolean isNurse = getActivity().getIntent().getExtras().getBoolean("isNurse");
		
		// Setup OnClickListener
		Button updateVS = (Button) rootView.findViewById(R.id.BTupdate_vital_signs);
		updateVS.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()){
				case R.id.BTupdate_vital_signs:
					// Retrieve all fields from EditText
					EditText Temperature = (EditText) rootView.findViewById(R.id.ETtemperature);
					EditText BPSystolic = (EditText) rootView.findViewById(R.id.ETSystolicBP);
					EditText BPDiastolic = (EditText) rootView.findViewById(R.id.ETDiastolicBP);
					EditText HeartRate = (EditText) rootView.findViewById(R.id.ETHeartRate);
					
					// Check for correct format
					if (isNumeric(Temperature.getText().toString())==false){
						Context context = getActivity();
						CharSequence text = "Temperature can only contain numeric characters";
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(context, text, duration);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					}
					
					else if (isNumeric(BPSystolic.getText().toString())==false){
						Context context = getActivity();
						CharSequence text = "Systolic BP can only contain numeric characters";
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(context, text, duration);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					}	
					
					else if (isNumeric(BPDiastolic.getText().toString())==false){
						Context context = getActivity();
						CharSequence text = "Diastolic BP can only contain numeric characters";
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(context, text, duration);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					}
					
					else if (isNumeric(HeartRate.getText().toString())==false){
						Context context = getActivity();
						CharSequence text = "Heart Rate can only contain numeric characters";
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(context, text, duration);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					} else {
						// Call backend function
						VitalSigns vitalsigns = nurse.updateVitalSigns(patient, new Date(),
								Double.parseDouble(Temperature.getText().toString()), 
								(int)Double.parseDouble(BPSystolic.getText().toString()), 
								(int)Double.parseDouble(BPDiastolic.getText().toString()), 
								(int)Double.parseDouble(HeartRate.getText().toString())); 
		
						// Clear EditTexts
						Temperature.setText("");
						BPSystolic.setText("");
						BPDiastolic.setText("");
						HeartRate.setText("");
						
						// Update List of VitalSigns
						displayVS.add(vitalsigns.displayString());
						VSListAdapter.notifyDataSetChanged();
					
					break;
				}
			}
			}
			
		});
		
		// Setup List of Vital Signs
		VSlv = (ListView) rootView.findViewById(R.id.LISTvitalSigns); 
		Iterator<Map.Entry<Date, VitalSigns>> ITvitalsigns = patient.getVitalSignsMap().entrySet().iterator();
		while (ITvitalsigns.hasNext()){
			Entry<Date, VitalSigns> VSSet = ITvitalsigns.next();
			displayVS.add(VSSet.getValue().displayString());
		}
		VSListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, displayVS);
		VSlv.setAdapter(VSListAdapter);
		
		if (!isNurse) {
			updateVS.setVisibility(View.INVISIBLE);
		}
		
		return rootView;
	}
}
