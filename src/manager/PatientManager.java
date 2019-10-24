package manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

/**
 * Manages the reading and writing of Patient objects into or out of text 
 * files.
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 * @param <T> Patient
 */
public class PatientManager<T> extends Manager<T>{

	/**
	 * Locates or creates the file to be written or read from.
	 * @param dir Directory of where the file is to be located.
	 * @param FILENAME Name of file.
	 * @throws IOException
	 * @throws ParseException
	 */
	public PatientManager(File dir, String FILENAME) throws IOException,
			ParseException { 
		super(dir, FILENAME);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void readIntoFile(Scanner linescanner) {
		// TODO Auto-generated method stub
		String data[];
		int j = 0;
		while (linescanner.hasNext()){
			String line = linescanner.nextLine();
			System.out.println(j++ +": "+line);
			data = line.split(" "); 
			try {
				nurse.recordPatientData(new SimpleDateFormat("yyyy-MM-dd", 
						Locale.ENGLISH).parse(data[4]), data[0], 
						data[1], Integer.parseInt(data[2]), 
						Integer.parseInt(data[3]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

	@Override
	public void writeIntoFile(Iterator<T> iterator, 
			FileOutputStream writeStream) throws IOException {
		// TODO Auto-generated method stub
		while (iterator.hasNext()){
			writeStream.write(iterator.next().toString().getBytes());
			if (iterator.hasNext())
				writeStream.write("\r\n".getBytes());
		}
	}
}
