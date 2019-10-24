package manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Scanner;

import android.annotation.SuppressLint;
import backend.Nurse;
import backend.Physician;

/**
 * A representation of a Manager that is responsible for reading and writing
 * into a text file. A parent class for all other managers.
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 *
 */

public abstract class Manager<T> {

	File file;
	@SuppressLint("SimpleDateFormat")
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	Nurse nurse;
	Physician physician;
	
	// FILENAMES
	public final static String ACCOUNT_FILE = "accounts.txt";
	public final static String PATIENT_FILE = "patients.txt";
	public final static String VITALSIGNS_FILE = "vitalsigns.txt";
	public final static String SYMPTOMS_FILE = "symptoms.txt";
	public final static String PRESCRIPTION_FILE = "prescriptions.txt";
	
	/**
	 * Creates the file with the given directory path and file name if it
	 * doesn't exist already.
	 * @param dir Directory of where the file is to be located.
	 * @param FILENAME Name of file.
	 * @throws IOException
	 * @throws ParseException
	 */
	public Manager(File dir, String FILENAME) throws IOException, 
	ParseException {
		this.file = new File(dir, FILENAME);
		if (!this.file.exists()){
			this.file.createNewFile();
		} 
		nurse = new Nurse();
		physician = new Physician();
	}
	
	/**
	 * Writes data into text file.
	 * @param iterator Iterator of the data of type T
	 * @throws IOException
	 */
	public void writeToFile(Iterator<T> iterator) throws Exception{
		FileOutputStream writeStream = new FileOutputStream(file);
		writeIntoFile(iterator, writeStream);
		writeStream.close();
	}
	
	/**
	 * Reads data from text file and stores it in corresponding logical data
	 * structure.
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public void readFromFile() throws FileNotFoundException, ParseException{
		final Scanner linescanner = new Scanner
				(new FileInputStream(file.getPath()));
		readIntoFile(linescanner);
	}
	
	/**
	 * Store data given by linescanner into logical data structure
	 * @param linescanner
	 */
	protected abstract void readIntoFile(Scanner linescanner);
	
	/**
	 * Writes data given by iterator into text file.
	 * @param iterator Iterator of a specified T type.
	 * @param writeStream Location of where data is written.
	 * @throws Exception 
	 */
	protected abstract void writeIntoFile(Iterator<T> iterator, 
			FileOutputStream writeStream) throws Exception;
	
}