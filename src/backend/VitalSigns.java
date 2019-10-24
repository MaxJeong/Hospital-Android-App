package backend;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import frontend.PatientInfo;

/**
 * A representation of VitalSigns which includes temperature, blood pressure,
 * and heart rate.
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 *
 */

public class VitalSigns implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 279724511585928900L;
	private Date recordTime;
	private double temperature;
	private BloodPressure bloodPressure;
	private int heartRate;
	@SuppressLint("SimpleDateFormat")
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Creates a new VitalSigns with the given temperature. heart rate, 
	 * systolicBP and diastolicBP for the blood pressure.
	 * @param temperature
	 * @param heartRate
	 * @param systolicBP
	 * @param diastolicBP
	 */
	public VitalSigns(Date date, double temperature, 
			BloodPressure bloodPressure, int heartRate) {
		this.recordTime = date;
		this.temperature = temperature;
		this.bloodPressure = bloodPressure;
		this.heartRate = heartRate;
	}

	/**
	 * Returns recorded time for this VitalSigns.
	 * @return this VitalSigns's recorded time.
	 */
	public Date getTimeOfRecording() {
		return recordTime;
	}
	
	/**
	 * Returns temperature for this VitalSigns.
	 * @return this VitalSigns's temperature.
	 */
	public double getTemperature() {
		return temperature;
	}
	
	/**
	 * Returns blood pressure for this VitalSigns.
	 * @return this VitalSigns's blood pressure.
	 */
	public BloodPressure getBloodPressure() {
		return bloodPressure;
	}
	
	/**
	 * Returns heart rate for this VitalSigns.
	 * @return this VitalSigns's heart rate.
	 */
	public int getHeartRate() {
		return heartRate;
	}
	
	/**
	 * Returns a string representation of this VitalSigns.
	 */
	public String toString() {
		return (dateformat.format(recordTime)) + " " + 
				this.temperature + " " + bloodPressure.fileString() + " " 
				+ heartRate;
	}
	
	/**
	 * Returns a string representation of this VitalSigns for display in the
	 * android screen
	 * @return a string representation of this VitalSigns
	 */
	public String displayString(){
		return "Date: "+PatientInfo.dateformat.format(this.recordTime)+
		"\nTemperature: " +temperature+"\nSystolic Blood Pressure: "
				+this.bloodPressure.getDiastolicBP()
				+"\nDiastolic Blood Pressure: "
				+this.bloodPressure.getSystolicBP()+"\nHeart Rate: "
				+this.getHeartRate();
	}

}
