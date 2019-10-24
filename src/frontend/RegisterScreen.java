package frontend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import backend.Account;

import com.example.hospitalapplication.R;

public class RegisterScreen extends Activity {
	private RadioButton radioTypeButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_screen);
	}
	
	public void submitRegister(View view) throws Exception{
		EditText userText = (EditText) findViewById(R.id.usernameSubmit);
    	String username = userText.getText().toString();
    	
    	EditText passwordText = (EditText) findViewById(R.id.passwordSubmit);
    	String password = passwordText.getText().toString();
    	
    	RadioGroup nurseOrPhysicianGroup = (RadioGroup) 
    			findViewById(R.id.radioGroup1);
    	int selectedbuttonID = nurseOrPhysicianGroup.getCheckedRadioButtonId();
    	radioTypeButton = (RadioButton) findViewById(selectedbuttonID);
    	String nurseOrPhysician = radioTypeButton.getText().toString();
    	Account ac;
    	
    	if (nurseOrPhysician.equals("Nurse")){
    		ac = new Account(username, password, true);
    	} else {
    		ac = new Account(username, password, false);
    	}
    	
    	// Add to logical data structure
    	LoginAuthentication.acmanager.credentials.put(username, ac);
    	
    	// Write to file
    	LoginAuthentication.acmanager.writeToFile(LoginAuthentication
    			.acmanager.credentials.values().iterator());
    	
    	Intent intent = new Intent(this, LoginAuthentication.class);
    	startActivity(intent);
    	
	}

}
