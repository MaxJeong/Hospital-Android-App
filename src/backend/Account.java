package backend;

/**
 * A representation of a Nurse or Physician Account
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 *
 */

public class Account {

	private String username;
	private String password;
	private boolean isNurse;	//true for Nurse and false for Physician
	
	/**
	 * Create a new Account object.
	 * @param username the username of this Account.
	 * @param password the password of this Account.
	 * @param isNurse the boolean representation of Nurse or Physician.
	 */
	public Account(String username, String password, boolean isNurse) {
		this.username = username;
		this.password = password;
		this.isNurse = isNurse;
	}
	
	/**
	 * Returns the username of this Account.
	 * @return the username of this Account.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of this Account.
	 * @param username the username of this Account.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns the password of this Account.
	 * @return the password of this Account.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of this Account.
	 * @param password the password of this Account.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns true for Nurse and false for Physician.
	 * @return true for Nurse and false for Physician.
	 */
	public boolean isNurse() {
		return isNurse;
	}

	/**
	 * Sets the boolean for this Account.
	 * @param isNurse the boolean representation of Nurse or Physician.
	 */
	public void setNurse(boolean isNurse) {
		this.isNurse = isNurse;
	}
	
	/**
	 * Returns a string representation of this Account.
	 */
	public String toString(){
		return username + " " + password + " " + isNurse;
	}
}
