package backend;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Prescription object which stores, and has getters for a prescription
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 *
 */
public class Prescription implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4792449139582731182L;
	@SuppressLint("SimpleDateFormat")
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	private Date timeOfRecording;
	private String medicationName;
	private String medicationInstructions;
	
	/**
	 * Constructor for the Prescription object
	 * @param medName Name of the meds
	 * @param medInstructions Instructions for the meds
	 * @param date date the prescription was created on
	 */
	public Prescription(String medName, String medInstructions, Date date){
		this.timeOfRecording = date;
		this.medicationName = medName;
		this.medicationInstructions = medInstructions;
			
	}
	/**
	 * Returns the time the prescription was created
	 * @return the time the prescription was created
	 */
	public Date getTimeOfRecording() {
		return this.timeOfRecording;
	}
	/**
	 * Returns the name of the medication prescribed
	 * @return the name of the medication prescribed
	 */
	public String getMedicationName() {
		return this.medicationName;
	}
	/**
	 * Returns the instructions to go with the medication
	 * @return the instructions to go with the medication
	 */
	public String getMedicationInstructions() {
		return this.medicationInstructions;
	}
	/**
	 * Returns a string representation of the prescriptionq
	 * @return a string representation fo the prescription
	 */
	public String toString() {
		return "Date: "+ dateformat.format(timeOfRecording) + 
		"\nMedication:  " + medicationName + "\nInstructions: " + 
				medicationInstructions;
	}
}
