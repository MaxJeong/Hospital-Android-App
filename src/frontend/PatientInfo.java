package frontend;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import backend.HospitalRecord;
import backend.Nurse;
import backend.Patient;

import com.example.hospitalapplication.R;

public class PatientInfo extends Fragment{

	Patient patient;
	Nurse nurse = new Nurse();
	View rootView;
	
	@SuppressLint("SimpleDateFormat")
	public static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateandtimeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	TextView DateSeen,Urgency;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.patient_info,
				container, false);

		// Get Patient from previous activity
		int key = getActivity().getIntent().getExtras().getInt("PatientHCNKey"); 
		patient = HospitalRecord.getListOfPatients().get(Integer.toString(key));
		
		boolean isNurse = getActivity().getIntent().getExtras().getBoolean("isNurse");
		
		// Setup Patient General Information
		TextView name = (TextView) rootView.findViewById(R.id.TVname);
		name.setText("Patient Name: "+patient.getName());
		TextView HCN = (TextView) rootView.findViewById(R.id.TVhcn);
		HCN.setText("Health Card Number: "+patient.getHcn());
		TextView DOB = (TextView) rootView.findViewById(R.id.TVdob);
		DOB.setText("Date of Birth: "+patient.getDob());
		TextView ArrivalTime = (TextView) rootView.findViewById(R.id.TVarrivaltime);
		ArrivalTime.setText("Arrival Time: "+dateformat.format(patient.getAdmissionDate()));
		Urgency = (TextView) rootView.findViewById(R.id.TVurgency);
		Urgency.setText("Urgency: "+patient.getUrgency().getPatientCategory());
		DateSeen = (TextView) rootView.findViewById(R.id.TVseenbydoctor);
		if (patient.getDateSeenByDoctor()!=null)
			DateSeen.setText("Seen By Doctor: "+dateandtimeformat.format(patient.getDateSeenByDoctor()));
		
		// Setup OnClickListener
		Button seenbyDoctor = (Button) rootView.findViewById(R.id.BTSeenByDoctor);
		seenbyDoctor.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()){
				case R.id.BTSeenByDoctor:
		            Date d = new Date();
		            nurse.setTimeSeenByDoctor(patient, d);
		            DateSeen.setText("Seen by Doctor: "+dateandtimeformat.format(patient.getDateSeenByDoctor()));
					break;
				}
			}
		});
		
		if (!isNurse) {
			seenbyDoctor.setVisibility(View.INVISIBLE);
		}
		return rootView; 
	}
}
