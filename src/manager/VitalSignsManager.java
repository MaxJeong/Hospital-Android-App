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
import backend.VitalSigns;

/**
 * Manages the reading and writing of Symptom objects into or out of text 
 * files.
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 * @param <T> Patient
 */
public class VitalSignsManager<T> extends Manager<T>{

	/**
	 * Locates or creates the file to be written or read from.
	 * @param dir Directory of where the file is to be located.
	 * @param FILENAME Name of file.
	 * @throws IOException
	 * @throws ParseException
	 */
	public VitalSignsManager(File dir, String FILENAME) throws IOException,
			ParseException {
		super(dir, FILENAME);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void readIntoFile(Scanner linescanner) {
		// TODO Auto-generated method stub
		while (linescanner.hasNext()){
			String curLine = linescanner.nextLine();
			if (!curLine.equals("")){
				final String[] data = curLine.split(" "); 
				Patient patient = HospitalRecord.getListOfPatients()
						.get(data[0]);
				String[] date = data[1].split("-");
				Calendar cal = Calendar.getInstance(); 
				System.out.println("FormatDate:"+Integer.parseInt(date[0])
						+Integer.parseInt(date[1])+Integer.parseInt(date[2]));
				cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
				cal.set(Calendar.MONTH, Integer.parseInt(date[1]));
				cal.set(Calendar.DATE, Integer.parseInt(date[2]));
				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				final Date j = cal.getTime();
				
				nurse.updateVitalSigns(patient, j, 
						Double.parseDouble(data[2]), 
						Integer.parseInt(data[3]), Integer.parseInt(data[4]),
						Integer.parseInt(data[5]));
			}
		}
	}

	@Override
	public void writeIntoFile(Iterator<T> iterator, 
			FileOutputStream writeStream) throws IOException {
		// TODO Auto-generated method stub
		while (iterator.hasNext()){
			Patient patient = (Patient) iterator.next();
			String hcn = Integer.toString(patient.getHcn());
			Iterator<VitalSigns> it = patient.getVitalSignsMap()
					.values().iterator();
			while (it.hasNext()){
				writeStream.write((hcn+" "+it.next().toString()).getBytes());
				writeStream.write(("\r\n").getBytes());
			}
		}
	}


}
