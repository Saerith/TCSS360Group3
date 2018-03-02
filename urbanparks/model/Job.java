package urbanparks.model;

import static urbanparks.model.Constants.MIN_DAYS_BEFORE_SIGNUP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a job with its all information.
 */
public class Job implements Serializable {

	private static final long serialVersionUID = 928850375626876361L;
	
	private long jobId;
	private String description;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private String parkName;
	private String location;
	private int maxLightWorkers;
	private int maxMediumWorker;
	private int maxHeavyWorkers;
	private int minTotalVolunteers;
	private boolean isAvailable;
	private boolean isCancelled;

	private ArrayList<String> volunteers;

	/**
	 * Constructor to initialize all the fields for this job.
	 * 
	 * @param description the job description.
	 * @param startDateTime the job start date and time.
	 * @param endDateTime the job end date and time.
	 * @param parkName the park name.
	 * @param location the job location.
	 * @param maxLightWorkers the number of volunteers required for light workload.
	 * @param theMediumm the number of volunteers required for medium workload.
	 * @param maxHeavyWorkers the number of volunteers required for heavy workload.
	 * @param minTotalVolunteers the minimum number of volunteers required for this job.
	 */
	public Job(final String description, final LocalDateTime startDateTime, final LocalDateTime endDateTime, 
			final String parkName, final String location, final int maxLightWorkers, final int maxMediumWorker, 
			final int maxHeavyWorkers, final int minTotalVolunteers) {
		this.jobId = generateJobID();
		this.description = description;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.parkName = parkName;
		this.location = location;
		this.maxLightWorkers = maxLightWorkers;
		this.maxMediumWorker = maxMediumWorker;
		this.maxHeavyWorkers = maxHeavyWorkers;
		this.minTotalVolunteers = minTotalVolunteers;

		volunteers = new ArrayList<String>(maxLightWorkers + maxLightWorkers + maxHeavyWorkers);
		isAvailable = true;
		isCancelled = false;
	}
	
	/**
	 * Generate jobID using the current data, time and random number.
	 *  
	 * @return generated JobID
	 */
	private long generateJobID() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		Random random = new Random();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
		String number = dateFormat.format(currentDateTime) + random.nextInt(Constants.RANDOM_NEXTINT);
		return Long.parseLong(number);
	}

	/**
	 * Adds a volunteer to the list of signed up volunteers. 
	 * Takes in a volunteer's email address, returns true if the volunteer
	 * was signed up successfully, false otherwise.
	 * @param theVolunteer the email address to sign-up
	 * @return true if the volunteer was added successfully, false otherwise
	 * @author Alec
	 */
	public boolean addVolunteer(String theVolunteer) {
		volunteers.add(theVolunteer);
		if(volunteers.contains(theVolunteer)) {
			return true;
		}
		return false;//if arraylist didn't add it for some reason
	}
	
	// Getters:
	/**
	 * Return the jobID.
	 * @return job ID.
	 */
	public long getJobId() {
		return jobId;
	}
	/**
	 * Gets the job's description
	 * @return The job's description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Return the job start date and time.
	 * @return the job start date and time.
	 */
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	
	/**
	 * Return the job end date and time.
	 * @return the job end date and time.
	 */
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}
	/**
	 * Gets the park name the job takes place in.
	 * @return Job's park name
	 */
	public String getParkName() {
		return parkName;
	}
	/**
	 * Gets the location the job takes place in.
	 * @return Job's location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * Gets the maximum light workers for the job
	 * @return Job's light workers maximum
	 */
	public int getMaxLightWorkers() {
		return maxLightWorkers;
	}
	/**
	 * Gets the maximum medium workers for the job
	 * @return Job's medium workers maximum
	 */
	public int getMaxMediumWorkers() {
		return maxMediumWorker;
	}
	/**
	 * Gets the maximum heavy workers for the job
	 * @return Job's heavy workers maximum
	 */
	public int getMaxheavyWorkers() {
		return maxHeavyWorkers;
	}
	/**
	 * Gets the minimum total volunteers required for the job.
	 * @return Job's minimum total volunteers
	 */
	public int getMinTotalVolunteers() {
		return minTotalVolunteers;
	}
	/**
	 * Gets a temporary flag representing the job's availability to a volunteer
	 * @return
	 */
	public boolean getIsAvailable() {
		return isAvailable;
	}

	//Setters:
	/**
	 * Sets a temporary flag representing the job's availability to a volunteer
	 * @param isAvailable
	 */
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	// Others:
	
	/**
	 * Gets a the start time of a Job as a string
	 * @return The start time of this job formatted as a string
	 */
	public String getStartDateFormatted() {
		return DateUtils.formatDateTime(startDateTime);
	}
	
	/**
	 * Gets a the end time of a Job as a string
	 * @return The end time of this job formatted as a string
	 */
	public String getEndDateFormatted() {
		return DateUtils.formatDateTime(endDateTime);
	}
	
	public String getIsCancelledFormatted() {
		if (isCancelled) {
			return "Yes";
		} else {
			return "No";
		}
	}
	
	public String getIsAvailableFormatted() {
		if (isAvailable) {
			return "Yes";
		} else {
			return "No";
		}
	}

	/**
	 * Determines if the start or end times of 2 jobs overlap
	 * 
	 * @param otherJob
	 * @return
	 */
	public boolean doJobsOverlap(Job otherJob) {
		if (DateUtils.are2DatesOnSameDay(startDateTime,  otherJob.getStartDateTime())) {
			return true;
		}
		if (DateUtils.are2DatesOnSameDay(startDateTime,  otherJob.getEndDateTime())) {
			return true;
		}
		if (DateUtils.are2DatesOnSameDay(endDateTime,  otherJob.getEndDateTime())) {
			return true;
		}
		return false;
	}

	/**
	 * Checks whether the time between now and the job start time is at least the minimum value,
	 *  for job sign up.
	 * 
	 * @param theCandidateJob
	 * @return true if there is enough time between now and when the job starts, false otherwise.
	 */
	public static boolean isSignupEarlyEnough(Job theCandidateJob) {
		/**
		 * A volunteer may sign up only if the job begins 
		 * at least a minimum number of calendar days after the current date
		 */
		return DateUtils.daysBetweenNowAndDate(theCandidateJob.getStartDateTime()) >= MIN_DAYS_BEFORE_SIGNUP;
	}

	/**
	 * Checks weather this job is between the given two date and time..
	 * Precondition: the given two dates are not null.
	 * 
	 * @param startDate the start time to be compare with job start and end time.
	 * @param endDate the end time to be compare with job start and end time.
	 * @return true if this job is between the given two dates, false otherwise.
	 */
	public boolean isBetween2Dates(LocalDateTime startDate, LocalDateTime endDate) {
		return startDateTime.compareTo(startDate) >= 0 && 
				endDateTime.compareTo(endDate) <= 0;
	}

	/**
	 * Check if the start date and time of this job is greater than or 
	 * equal to today date and time.
	 * 
	 * @return true if this job in the future, false otherwise.
	 */
	public boolean isInFuture() {
		return startDateTime.isAfter(LocalDateTime.now());
	}

//	/**
//	 * Shows job info
//	 * precondition: All Job fields must be non-null
//	 */
//	public void showInfo() {
//		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss");
//		System.out.println("Starting time: " + startDateTime.format(dateFormat));
//		System.out.println("Ending time: " + endDateTime.format(dateFormat));
//		System.out.println("Park name: " + parkName);
//		System.out.println("Location: " + location);
//		System.out.println("Job description: " + description);
//		System.out.println("Max volunteers for work levels: " 
//				+ "Light - " + maxLightWorkers
//				+ ", Medium - " + maxMediumWorker
//				+ ", Heavy - " + maxHeavyWorkers);
//		System.out.println("Min total volunteers: " + minTotalVolunteers);
//	}
}
