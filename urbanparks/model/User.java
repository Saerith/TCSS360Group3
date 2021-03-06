package urbanparks.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represent a user of the Urban Parks system.
 * invariants: all fields non-null
 */
public abstract class User implements Serializable {

	private static final long serialVersionUID = -4751543109134325926L;
	
	/** First name of user*/
	private String firstName;
	
	/** Last name of user*/
	private String lastName;
	
	/** Email of user for log in*/
	private String email;
	
	/** Phone number of user*/
	private String phoneNum;

	/** List of all jobs that user accepted or created. */
	protected ArrayList<Long> associatedJobs;

	/**
	 * Constructor to initialize all the fields for this job.
	 * 
	 * @param firstName the first name of the user.
	 * @param lastName the last name of the user.
	 * @param email the email address of the user.
	 * @param phone the phone number of the user.
	 */
	public User(String firstName, String lastName, String email, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phone;
		this.associatedJobs = new ArrayList<Long>();
	}
	
	/**
	 * Return the first name of the user.
	 * Precondition: there is at least 1 user in file. 
	 * @return the first name.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Return the last name of the user.
	 * Precondition: there is at least 1 user in file.
	 * @return the last name.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Return the email address of the user.
	 * Precondition: there is at least 1 user in file.
	 * @return the email address.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Gets this user's phone number.
	 * Precondition: there is at least 1 user in file.
	 * @return the phone number associated with this user
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	
	/**
	 * Checks to see if the user is associated with the passed jobid.
	 * @param id The job id to check
	 * @return true if the job is in this user's collection (signed up for/creator), false otherwise
	 */
	public boolean isAssociatedWithJob(long id) {
		for (long jobID : associatedJobs) {
			if (jobID == id) {
				return true;
			}
		}
		return false;
	}
}
