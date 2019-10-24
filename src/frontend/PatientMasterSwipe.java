package frontend;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.hospitalapplication.R;

public class PatientMasterSwipe extends FragmentActivity{

	SectionsPagerAdapter mSectionsPagerAdapter;
	Bundle extras;
	ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe);
		setTitle("Hospital Application");
		extras = getIntent().getExtras();
		
		// Set onclick listener for 
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		// Set onclick listener for Join button
		
	}
	
	public class SectionsPagerAdapter extends FragmentPagerAdapter{

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment;
			if (position == 0){
				fragment = new PatientInfo();
				Bundle args = new Bundle();
				args.putInt("PatientHCNKey", getIntent()
						.getExtras().getInt("PatientHCNKey"));
				args.putBoolean("isNurse", getIntent()
						.getExtras().getBoolean("isNurse"));
				
				fragment.setArguments(args);
			} else if (position == 1){
				fragment = new PatientVS();
				Bundle args = new Bundle();
				args.putInt("PatientHCNKey", getIntent()
						.getExtras().getInt("PatientHCNKey"));
				args.putBoolean("isNurse", getIntent()
						.getExtras().getBoolean("isNurse"));
				
				fragment.setArguments(args);
			} else if (position == 2){
				fragment = new PatientSymptoms();
				Bundle args = new Bundle();
				args.putInt("PatientHCNKey", getIntent()
						.getExtras().getInt("PatientHCNKey"));
				args.putBoolean("isNurse", getIntent()
						.getExtras().getBoolean("isNurse"));
				
				fragment.setArguments(args);
			} else {
				fragment = new PatientPrescription();
				Bundle args = new Bundle();
				args.putInt("PatientHCNKey", getIntent()
						.getExtras().getInt("PatientHCNKey"));
				args.putBoolean("isNurse", getIntent()
						.getExtras().getBoolean("isNurse"));
				
				fragment.setArguments(args);
			}
			
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return ("PatientInfo").toUpperCase(l);
			case 1:
				return ("VitalSigns").toUpperCase(l);
			case 2: 
				return ("Symptoms").toUpperCase(l);
			case 3:
				return ("Prescription").toUpperCase(l);
			}
			return null;
		}
	}
}
