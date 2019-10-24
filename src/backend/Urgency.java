package backend;

import java.io.Serializable;

/**
 * Urgency object that stores points of a patient based on the hostpial's
 * policy and the 'urgent' category that they fall under.
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 *
 */
public class Urgency implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8371670428536609050L;
	private int points;
	private String patientCategory;
	/**
	 * Constructor for this Urgency Object.
	 * @param patient Patient object that this Urgency Object will correspond
	 * to.
	 */
	public Urgency(Patient patient, VitalSigns vs) {
		points = 0;
		pointAllocation(patient, vs);
	}
	
	/**
	 * Empty Constructor that sets point to 0.
	 */
	public Urgency(){
		points = 0;
		setPatientCategory();
	}
	
	/**
	 * Allocates points for patient based on Hospital Policy.
	 * @param patient Patient object
	 */
	private void pointAllocation(Patient patient, VitalSigns vs){
		if (patient.getAge() < 2){
			points++;
		}
		if (vs.getTemperature() >= 39.0){
			points++;
		}
		if (vs.getBloodPressure().getSystolicBP() >= 140 
		|| vs.getBloodPressure().getDiastolicBP() >= 90){
			points++;
		}
		if (vs.getHeartRate()>=100 || vs.getHeartRate()<=50){
			points++;
		}
		setPatientCategory();
	}
	
	/**
	 * Sets patientCategory according to points.
	 */
	private void setPatientCategory(){
		switch(points){
		case 0:
			patientCategory = "No VitalSigns";
			break;
		case 1:
			patientCategory = "Non Urgent";
			break;
		case 2:
			patientCategory = "Less Urgent";
			break;
		case 3:
		case 4:
			patientCategory = "Urgent";
			break;
		}
	}
	
	/**
	 * Gets the category of the patient that he/she falls under.
	 * @return String patientCategory 
	 */
	public String getPatientCategory(){
		return patientCategory;	
	}	
	
	/**
	 * Gets the amount of urgency points.
	 * @return int points
	 */
	public int getPoints(){
		return points;	
	}	
	
	/**
	 * Returns this Urgency object as a String, contains the category and 
	 * points.
	 */
	public String toString(){
		return patientCategory + " " + points + " points";
	}
}