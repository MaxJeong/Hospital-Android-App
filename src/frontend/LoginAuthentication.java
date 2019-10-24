package frontend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import manager.AccountManager;
import manager.Manager;
import manager.PatientManager;
import manager.PrescriptionManager;
import manager.SymptomsManager;
import manager.VitalSignsManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import backend.Account;
import backend.Patient;

import com.example.hospitalapplication.R;

public class LoginAuthentication extends Activity {
	
	// File Managers
	public static PatientManager<Patient> pmanager;
	public static VitalSignsManager<Patient> vsmanager;
	public static SymptomsManager<Patient> smanager;
	public static PrescriptionManager<Patient> prmanager;
	public static AccountManager<Account> acmanager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_authentication);
		try {
			pmanager = new PatientManager<Patient>(this
					.getApplicationContext()
					.getFilesDir(), Manager.PATIENT_FILE);
			pmanager.readFromFile();
			vsmanager = new VitalSignsManager<Patient>(this
					.getApplicationContext().getFilesDir(), 
					Manager.VITALSIGNS_FILE);
			vsmanager.readFromFile();
			smanager = new SymptomsManager<Patient>(this
					.getApplicationContext().getFilesDir(), 
					Manager.SYMPTOMS_FILE);
			smanager.readFromFile();
			prmanager = new PrescriptionManager<Patient>(this
					.getApplicationContext().getFilesDir(), 
					Manager.PRESCRIPTION_FILE);
			prmanager.readFromFile();
			acmanager = new AccountManager<Account>(this
					.getApplicationContext().getFilesDir(), 
					Manager.ACCOUNT_FILE);
			acmanager.readFromFile();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	EditText user, pass;
	
	public void authenticateUser(View view) throws IOException{
		@SuppressWarnings("unused")
		Intent intent = new Intent(this, HomeScreen.class);
		
		user = (EditText) findViewById(R.id.user_name_field);
		pass = (EditText) findViewById(R.id.password_field);
		Boolean isNurse = acmanager.authenticate(user.getText().toString(),
				pass.getText().toString());
		if (isNurse!=null){
			// Get account
			Intent intent2 = new Intent(this, HomeScreen.class);
			intent2.putExtra("isNurse", isNurse.booleanValue());
			Toast.makeText(getApplicationContext(), "Success", 
					Toast.LENGTH_LONG).show();
			startActivity(intent2);
		} else {
			Toast.makeText(getApplicationContext(), "Invalid User Info", 
					Toast.LENGTH_LONG).show();
		}
	}
	
	public void promptRegister(View view) throws IOException{
		Intent intent = new Intent(this, RegisterScreen.class);
		startActivity(intent); 
	
	}

	public void readFromFile(String filePath) throws FileNotFoundException {
        
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        while (scanner.hasNext()){
        	String[] userAuthen = scanner.nextLine().split(" ");
	        if(userAuthen[1].equals(user.getText().toString())){
	        	if (userAuthen[2].equals(pass.getText().toString())){
	        		Intent homeScreenIntent = new Intent(this, 
	        				HomeScreen.class);
	        		homeScreenIntent.putExtra("nurseOrPhysician", 
	        				userAuthen[0]);
	        		startActivity(homeScreenIntent);
	        	}
	        }	
        }
        while(scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
