package backend;

import java.io.Serializable;

/**
 * A representation of BloodPressure measured in systolic and diastolic.
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 *
 */

public class BloodPressure implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6919692996219657546L;
	public Integer systolicBP;
	public Integer diastolicBP;
	
	/**
	 * Creates a new BloodPressure with the given systolicBP and diastolicBP.
	 * @param systolicBP
	 * @param diastolicBP
	 */
	public BloodPressure(Integer systolicBP, Integer diastolicBP) {
		this.systolicBP = systolicBP;
		this.diastolicBP = diastolicBP;
	}

	/**
	 * Returns systolicBP
	 * @return systolicBP
	 */
	public Integer getSystolicBP() {
		return systolicBP;
	}

	/**
	 * Returns diastolicBP
	 * @return diastolicBP
	 */
	public Integer getDiastolicBP() {
		return diastolicBP;
	}
	
	/**
	 * Sets systolicBP for this BloodPressure.
	 * @param systolicBP
	 */
	public void setSystolicBP(Integer systolicBP) {
		this.systolicBP = systolicBP;
	}

	/**
	 * Sets diastolicBP for this BloodPressure.
	 * @param diastolicBP
	 */
	public void setDiastolicBP(Integer diastolicBP) {
		this.diastolicBP = diastolicBP;
	}
	
	/**
	 * Returns a string representation of this BloodPressure.
	 */
	public String toString(){
		return "SBP: "+systolicBP+", DBP: "+diastolicBP;
	}
	
	/**
	 * Returns a string representation of this BloodPressure that will be
	 * stored in a text file.
	 * @return a string representation of this BloodPressure.
	 */
	public String fileString(){
		return systolicBP + " " + diastolicBP;
	}
}
