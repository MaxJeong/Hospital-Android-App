package backend;

import java.util.Date;

/**
 * A representation of a Nurse.
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 *
 */

public class Nurse {
	
	/**
	 * Empty Constructor
	 */
	public Nurse(){
		
		// Initialize Patient RecordManager.
	}
	
	/**
	 * Creates a new Patient object and stores it in HospitalRecords
	 * @param date Date the patient was admitted
	 * @param name name of the patient
	 * @param dob date of birth of the patient 
	 * @param hcn health card number of the patient
	 * @param age age of the patient
	 */
	public Patient recordPatientData(Date date, String name, String dob, 
			int hcn, int age){
		Patient patient = new Patient(date, name, dob, hcn, age);
		// Add to Hospital Records Master Patient list
		HospitalRecord.getListOfPatients().put(Integer.toString(patient.
				getHcn()), patient);
		// Stores patient object 
		return patient;
	}
	
	/**
	 * Creates a Patient object with the date the patient was admitted
	 * and unseen date.
	 * @param date Date the patient was admitted
	 * @param name Name of the patient
	 * @param dob Date of birth of the patient
	 * @param hcn Health Card Number of the patient
	 * @param age Age of the patient
	 * @param seenbydoctor Date that the patient has been seen by a doctor.
	 * @return Patient object
	 */
	public Patient recordPatientData(Date date, String name, String dob, 
			int hcn, int age, Date unseenDate){
		Patient patient = new Patient(date, name, dob, hcn, age, unseenDate);
		// Add to Hospital Records Master Patient list
		HospitalRecord.getListOfPatients().put(Integer.toString(patient.
				getHcn()), patient);
		// Stores patient object 
		return patient;
	}
	
	/**
	 * Updates the latest Symptoms displayed by a patient.
	 * @param patient the patient 
	 * @param symptom the description of the symptom
	 */
	public Symptoms updateSymptoms(final Date date, Patient patient, String 
			symptom){
		Symptoms symptoms = new Symptoms(symptom, date);
		
		// Patient handles writing of symptoms to file.
		patient.addSymptoms(symptoms); 
		return symptoms;
	}
	
	/**
	 * Updates the latest vital signs held by a patient
	 * @param patient the patient
	 * @param date the date the vital signs were read
	 * @param temperature the patient's temperature
	 * @param systolicBP the systolic blood pressure of the patient
	 * @param diastolicBP the diastolic blood pressure of the patient
	 * @param heartRate the patient's heart rate
	 * @return the vital signs of the patient
	 */
	public VitalSigns updateVitalSigns(final Patient patient, final Date date,
			double temperature, int systolicBP, int diastolicBP, 
			int heartRate){
		BloodPressure bp = new BloodPressure(systolicBP, diastolicBP);
		VitalSigns vs = new VitalSigns(date, temperature, bp, heartRate);
		patient.addVitalSigns(vs);
		return vs;
	}
	
	/**
	 * Sets time patient has been seen by doctor
	 * @param patient the patient
	 */
	public void setTimeSeenByDoctor(Patient patient, Date d){
		patient.setDateSeenByDoctor(d);
	}
}
