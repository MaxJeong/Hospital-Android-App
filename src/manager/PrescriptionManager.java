package manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import backend.HospitalRecord;
import backend.Patient;
import backend.Prescription;

/**
 * Manages reading and writing all of a patient's prescriptions to file
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 * @param <T> Patient
 */
public class PrescriptionManager<T> extends Manager<T> {
	
	/**
	 * Constructor for the PrescriptionManager class
	 * @param dir the location for the file for the prescriptions
	 * @param FILENAME the name of the file for the prescriptions
	 * @throws IOException 
	 * @throws ParseException
	 */
	public PrescriptionManager(File dir, String FILENAME) throws IOException,
	ParseException {	
		super(dir, FILENAME);
	}
	
	@Override
	protected void readIntoFile(Scanner linescanner){
		while (linescanner.hasNext()){
			String stringline = linescanner.nextLine();
			if (!stringline.equals("")){
				final String[] presData = stringline.split(" ");
				Patient patient = HospitalRecord.getListOfPatients()
						.get(presData[0]);
				String presInst = presData[3].replace("_", " ");
				String[] date = presData[1].split("-");
				Calendar cal = Calendar.getInstance(); 

				cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
				cal.set(Calendar.MONTH, Integer.parseInt(date[1]));
				cal.set(Calendar.DATE, Integer.parseInt(date[2]));
				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				final Date fixedDate = cal.getTime();
				
				physician.updatePrescription(fixedDate, patient, presData[2],
						presInst);
			}
		}
	}
	
	@Override
	protected void writeIntoFile(Iterator<T> iter, 
			FileOutputStream writeStream) throws Exception {
	// TODO Auto-generated method stub
		while (iter.hasNext()){
			Patient patient = (Patient) iter.next();
			String hcn = Integer.toString(patient.getHcn());
			Iterator<Prescription> itertwo = patient.getPrescriptionMap()
					.values().iterator();
			while (itertwo.hasNext()){
				Prescription presToWrite = itertwo.next();
				String presString = presToWrite.getTimeOfRecording() + " " 
				+ presToWrite.getMedicationName() + " " 
				+ presToWrite.getMedicationInstructions().replace(" ", "_");
				writeStream.write((hcn+" "+presString).getBytes());
				writeStream.write(("\r\n").getBytes());
		
			}
		}

	}
}