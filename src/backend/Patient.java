package backend;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import frontend.PatientInfo;

import android.annotation.SuppressLint;

/**
 * A representation of a Patient.
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 *
 */

public class Patient implements Doctor, Serializable{
	
	private static final long serialVersionUID = 6743028506959888117L;
	private Map<Date, VitalSigns> vitalSignsMap;
	private Map<Date, Symptoms> symptomsMap;
	private Map<Date, Prescription> prescriptionMap;
	private Urgency urgency;
	@SuppressLint("SimpleDateFormat")
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	
	//date when the patient has been seen by a doctor.
	private Date seenDate;
	
	//date when the patient was registered to a hospital.
	private Date admissionDate;
	private String name;
	private String dob;  //dob is date of birth, in the format year/month/day
	private int hcn;	 //hcn is health card number
	private int age;
	
	/**
	 * Creates a patient object with nothing but their basic information
	 * stored.
	 * @param date Date the patient was admitted
	 * @param name Name of the patient
	 * @param dob Date of birth of the patient
	 * @param hcn Health Card Number of the patient
	 * @param age Age of the patient
	 */
	public Patient(Date date, String name, String dob, int hcn, int age) {
		this.vitalSignsMap = new HashMap<Date, VitalSigns>();
		this.symptomsMap = new HashMap<Date, Symptoms>();
		this.prescriptionMap = new HashMap<Date, Prescription>();
		this.urgency = new Urgency();
		this.name = name;
		this.dob = dob;
		this.hcn = hcn;
		this.age = age;
		this.seenDate = null;
		admissionDate = date;
	}
	
	/**
	 * Creates a patient object that's been seen by a doctor.
	 * @param date Date the patient was admitted
	 * @param name Name of the patient
	 * @param dob Date of birth of the patient
	 * @param hcn Health Card Number of the patient
	 * @param age Age of the patient
	 * @param seenbydoctor Date that the patient has been seen by a doctor.
	 */
	public Patient(Date date, String name, String dob, int hcn, int age, Date seenbydoctor) {
		this.vitalSignsMap = new HashMap<Date, VitalSigns>();
		this.symptomsMap = new HashMap<Date, Symptoms>();
		this.urgency = new Urgency();
		this.name = name;
		this.dob = dob;
		this.hcn = hcn;
		this.age = age;
		this.seenDate = seenbydoctor;
		admissionDate = date;
	}
	
	/**
	* Sets this Patient's name
	* @param name the name of the patient
	*/
	public void setName(String name){
		this.name = name;
	}
	
	/**
	* Sets this Patient's date of birth
	* @param year year of birthday
	* @param month month of birthday
	* @param day day of birthday
	*/
	public void setDOB(String year, String month, String day){
		this.dob = year+"/"+month+"/"+day;
	}
	
	/**
	* Sets this Patient's Health Card Number
	* @param hcn the Patient's health card number
	*/
	public void setHCN(int hcn){
		this.hcn = hcn;
	}
	
	/**
	* Sets this Patient's age
	* @param age the patient's age
	*/
	public void setAge(int age){
		this.age = age;
	}
	
	/**
	 * Returns a Map of Date to VitalSigns for this Patient.
	 * @return Map of Date to VitalSigns for this Patient.
	 */
	public Map<Date, VitalSigns> getVitalSignsMap() {
		return vitalSignsMap;
	}
	
	/**
	 * Returns a Map of Date to Symptoms for this Patient.
	 * @return Map of Date to Symptoms
	 */
	public Map<Date, Symptoms> getSymptomsMap() {
		return symptomsMap;
	}
	
	/**
	 * Returns the map of date to prescriptions for this patient
	 * @return Map of date to prescription
	 */
	public Map<Date, Prescription> getPrescriptionMap(){
		return prescriptionMap;
	}
	
	/**
	 * Returns the urgency category and point for this Patient.
	 * @return the urgency category and point for this Patient.
	 */
	public Urgency getUrgency() {
		return urgency;
	}
	
	public Date getAdmissionDate() {
		return admissionDate;
	}
	
	/**
	 * Returns the name of this Patient.
	 * @return the name of this Patient.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the date of birth for this Patient.
	 * @return the date of birth for this Patient.
	 */
	public String getDob() {
		return dob;
	}
	
	/**
	 * Returns the health card number for this Patient.
	 * @return the health card number for this Patient.
	 */
	public int getHcn() {
		return hcn;
	}
	
	/**
	 * Returns the age for this Patient.
	 * @return the age for this Patient.
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Adds the recorded time of symptoms and the actual symptoms for this 
	 * Patient.
	 * @param symptoms the symptoms that needs to be added.
	 */
	public void addSymptoms(Symptoms symptoms) {
		Date symptomsRecordTime = symptoms.getTimeOfRecording();
		symptomsMap.put(symptomsRecordTime, symptoms);
		// Write to patient.symptoms file
	}
	
	/**
	 * Adds the recorded time of vital signs and the actual vital signs for 
	 * this Patient.
	 * @param vitalSigns the vital signs that needs to be added.
	 */
	public void addVitalSigns(VitalSigns vitalSigns) {
		Date vitalRecordTime = vitalSigns.getTimeOfRecording();
		vitalSignsMap.put(vitalRecordTime, vitalSigns);
		this.urgency = new Urgency(this, vitalSigns);
		// Write to patient.vitalsigns file
	}
	/**
	 * Adds an additional prescription for this patient, mapped with it's recording time as a key
	 * @param prescription the prescription to be added
	 */
	public void addPrescription(Prescription prescription) {
		Date prescriptionRecordTime= prescription.getTimeOfRecording();
		prescriptionMap.put(prescriptionRecordTime, prescription);
	}
	
	/**
	 * Returns a string representation of Patient.
	 * Contains their basic information.
	 */
	public String toString() {
		String s;
		if (this.seenDate==null){
			s = this.getName() + " " +
					this.getDob() + " " + 
					this.getHcn() + " " +
					this.getAge() + " " +
					dateformat.format(this.getAdmissionDate());
		} else {
			s = this.getName() + " " +
					this.getDob() + " " + 
					this.getHcn() + " " +
					this.getAge() + " " +
					dateformat.format(this.getAdmissionDate()) + " " +
					PatientInfo.dateandtimeformat.format(this.seenDate);
		}
		
		return s;
	}
	@Override
	public void setDateSeenByDoctor(Date d) {
		// TODO Auto-generated method stub
		seenDate = d;
	}
	
	
	/**
	 * Returns the date seen by a doctor for this Patient.
	 * @return the date seen by a doctor for this Patient.
	 */
	public Date getDateSeenByDoctor() {
		return seenDate;
	}
}
