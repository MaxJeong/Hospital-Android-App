package frontend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import backend.HospitalRecord;
import backend.Patient;

import com.example.hospitalapplication.R;

public class HomeScreen extends Activity implements OnItemClickListener, 
OnClickListener{

	ArrayList<String> displayHCN = new ArrayList<String>();
	ArrayList<String> HCNList = new ArrayList<String>();
	ArrayList<Patient> PatientList = new ArrayList<Patient>();
	ArrayAdapter<String> ListAdapter;
	String[] HCNArray;
	Patient[] PatientArray;
	private ListView lv;
	EditText inputSearch;
	boolean isNurse;
	int sortSelected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		
		// Setup New Patient button
		Button newpatient = (Button) findViewById(R.id.BTnewpatient);
		newpatient.setOnClickListener(this);
		Button save = (Button) findViewById(R.id.BTSave);
		save.setOnClickListener(this);
		
		isNurse = getIntent().getExtras().getBoolean("isNurse");
			
		if (!isNurse) {
			newpatient.setVisibility(View.INVISIBLE);
		}
		
		// Setup List Adapters and List View
		lv = (ListView) findViewById(R.id.list); 
		inputSearch = (EditText) findViewById(R.id.inputSearch);
		displayHCN.add(" "); // Empty placeholder.
		ListAdapter = new ArrayAdapter<String>(this, android.R.layout
				.simple_list_item_1, displayHCN);
		lv.setAdapter(ListAdapter);
		lv.setOnItemClickListener(this);
		
		// Setup Patient Search Function
		inputSearch.addTextChangedListener(new TextWatcher(){
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				HomeScreen.this.ListAdapter.getFilter().filter(cs);
			}

			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
			}

			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		// Initialize ListView with patient data
		Iterator<Map.Entry<String, Patient>> listofpatients = HospitalRecord
				.getListOfPatients().entrySet().iterator();
		while (listofpatients.hasNext()){
			Entry<String, Patient> currentRecord = listofpatients.next();
			HCNList.add(currentRecord.getValue().getName()+" "+ currentRecord
					.getValue().getDob()+" "+currentRecord.getKey());
			PatientList.add(currentRecord.getValue());
		}
		HCNArray = HCNList.toArray(new String[HCNList.size()]);
		PatientArray = PatientList.toArray(new Patient[PatientList.size()]);
		displayHCN.clear();
		displayHCN.addAll(HCNList);
		ListAdapter.notifyDataSetChanged();
		
		sortSelected = 0;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, 
			long arg3) {
		// TODO Auto-generated method stub
		Intent patientViewIntent = new Intent(this, PatientMasterSwipe.class);
		patientViewIntent.putExtra("PatientHCNKey", PatientArray[position]
				.getHcn());
		patientViewIntent.putExtra("isNurse", isNurse);
		startActivityForResult(patientViewIntent, 1);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub 
		switch(v.getId()){
		case R.id.BTnewpatient:
			Intent intent = new Intent(this, NewPatient.class);
			intent.putExtra("isNurse", isNurse);
			startActivityForResult(intent, 1);
			break;
		case R.id.BTSave:
			try {
				LoginAuthentication.pmanager.writeToFile(HospitalRecord
						.getListOfPatients().values().iterator());
				LoginAuthentication.vsmanager.writeToFile(HospitalRecord
						.getListOfPatients().values().iterator());
				LoginAuthentication.smanager.writeToFile(HospitalRecord
						.getListOfPatients().values().iterator());
				LoginAuthentication.prmanager.writeToFile(HospitalRecord
						.getListOfPatients().values().iterator());
				Toast.makeText(getApplicationContext(), "Saved", Toast
						.LENGTH_LONG).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * OnClickListener for group of radio buttons 
	 * @param view
	 */
	public void onRadioButtonClicked(View view){
		boolean checked = ((RadioButton) view).isChecked();
		switch(view.getId()){
		case R.id.RBAll:
			if (checked){
				sortSelected = 0;
				updateList(HospitalRecord.getListOfPatients().entrySet()
						.iterator());
			}
			break;
		case R.id.RBTime:
			if (checked){
				sortSelected = 1;
				updateList(HospitalRecord.getListOfEarliestArrivingPatients()
						.entrySet().iterator());
			}
			break;
		case R.id.RBUrgency:
			if (checked){
				sortSelected = 2;
				updateList(HospitalRecord.getListOfMostUrgentPatients()
						.entrySet().iterator());
			}
			break;
		}
	}
	
	public void updateList(Iterator<Map.Entry<String, Patient>> 
	listofpatients){
		// Empty our current Lists
		HCNList.clear();
		PatientList.clear();
		
		while (listofpatients.hasNext()){
			Entry<String, Patient> currentRecord = listofpatients.next();
			HCNList.add(currentRecord.getValue().getName()+" "+ currentRecord
					.getValue().getDob()+" "+currentRecord.getKey());
			PatientList.add(currentRecord.getValue());
		}
		PatientArray = PatientList.toArray(new Patient[PatientList.size()]);
		displayHCN.clear();
		displayHCN.addAll(HCNList);
		ListAdapter.notifyDataSetChanged();
	}
	
	  protected void onActivityResult(int requestCode, int resultCode
			  , Intent data) {
		    switch(sortSelected){
		    case 0:
		    	updateList(HospitalRecord.getListOfPatients().entrySet()
		    			.iterator());
		    	break;
		    case 1: 
		    	updateList(HospitalRecord.getListOfEarliestArrivingPatients()
		    			.entrySet().iterator());
		    	break;
		    case 2: 
		    	updateList(HospitalRecord.getListOfMostUrgentPatients()
		    			.entrySet().iterator());
		    	break;
		    }
	  }
}
