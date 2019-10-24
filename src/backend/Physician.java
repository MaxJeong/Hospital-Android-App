package backend;

import java.util.Date;

/**
 * A representation of a Physician.
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 *
 */

public class Physician {
	/**
	 * Empty constructor
	 */
	public Physician(){
	}
	/**
	 * Updates the latest prescription prescribed to the patient
	 * @param date the date the prescription was prescribed
	 * @param patient the patient the prescription was prescribed to
	 * @param medName the name of the medication prescribed
	 * @param medInstructions the instructions for the prescribed medication
	 * @return the prescription
	 */
	public Prescription updatePrescription(Date date, Patient patient, 
			String medName, String medInstructions){
		Prescription newPres = new Prescription(medName, medInstructions, 
				date);
		patient.addPrescription(newPres);
		return newPres;
	}
}
