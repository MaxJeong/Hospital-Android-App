package frontend;

import java.util.Date;

import manager.PatientManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import backend.Nurse;
import backend.Patient;

import com.example.hospitalapplication.R;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class NewPatient extends Activity implements OnClickListener{
	
	EditText nameText, dobText, hcnText, ageText;
	Nurse nurse = new Nurse();
	PatientManager<Patient> pmanager;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_patient_dialog);
		
		nameText = (EditText) findViewById(R.id.NameText);
		dobText = (EditText) findViewById(R.id.DobText);
		hcnText = (EditText) findViewById(R.id.HcnText);
		ageText = (EditText) findViewById(R.id.AgeText);
		
		Button submit = (Button) findViewById(R.id.btn_custom_alert_ok);
	    submit.setOnClickListener(this);
	}
	
	
	//This is a helper method that checks if the input string 
	//contains only numeric characters
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    @SuppressWarnings("unused")
		double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	
	//This is a helper method that checks if the input String
	//contains only alphabetic characters
	public boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	@Override
	public void onClick(View v){
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_custom_alert_ok:
			
		if(nameText.getText().toString().isEmpty() || 
				hcnText.getText().toString().isEmpty() || 
				dobText.getText().toString().isEmpty() || 
				ageText.getText().toString().isEmpty()){
			Context context = getApplicationContext();
			CharSequence text = "None of the fields can be left empty";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();	
		}
			
		else if (isAlpha(nameText.getText().toString()) == false){
				Context context = getApplicationContext();
				CharSequence text = "Name cannot contain numeric characters";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();		
			}
			else if (isNumeric(hcnText.getText().toString()) == false){
				Context context = getApplicationContext();
				CharSequence text = "Health Card Number can only contain " +
						"numeric characters";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
			else if (isNumeric(ageText.getText().toString()) == false){
				Context context = getApplicationContext();
				CharSequence text = "Age can only contain numeric characters";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
			
			else if (dobText.getText().toString()
					.matches("\\d{4}\\-\\d{2}\\-\\d{2}") == false){
				Context context = getApplicationContext();
				CharSequence text = "Date of Birth has to be in this " +
						"format: yyyy-mm-dd";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
			
			else{
			String name = nameText.getText().toString();
			String dob = dobText.getText().toString();
			int hcn = (int)Double.parseDouble(hcnText.getText().toString());
			int age = (int)Double.parseDouble(ageText.getText().toString());
			
			boolean isNurse = getIntent().getExtras().getBoolean("isNurse");
			nurse.recordPatientData(new Date(),name, dob, hcn, age);
			Intent mainScreen = new Intent(this, HomeScreen.class);
			mainScreen.putExtra("isNurse", isNurse);
			finish();
		}
		}}}
	
	


