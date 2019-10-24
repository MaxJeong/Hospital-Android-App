package backend;

import java.util.Date;

/**
 * An interface for the date the patient has been seen by a doctor.
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 *
 */
public interface Doctor {
		
	/**
	 * Returns the date.
	 * @return the date
	 */
	public Date getDateSeenByDoctor();
	
	/**
	 * Sets the date seen by doctor
	 */
	public void setDateSeenByDoctor(Date d);
	
}
