package manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.text.ParseException;
import backend.Account;

/**
 * Manages reading and writing all of nurse and physician accounts.
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 *
 */

public class AccountManager<T> extends Manager<T>{
	public Map<String, Account> credentials = new HashMap<String, Account>();
	
	public AccountManager(File dir, String FILENAME) throws IOException, 
	ParseException {
		super(dir, FILENAME);
	}
	
	@Override
	public void readIntoFile(Scanner linescanner) {
		while (linescanner.hasNext()) {
			String line = linescanner.nextLine();
			if (!line.equals("")) {
				final String[] account = line.split(" "); 
				Account ac = new Account(account[0], account[1], 
						Boolean.parseBoolean(account[2]));
				credentials.put(account[0], ac);
			}
		}
	}
	
	@Override
	public void writeIntoFile(Iterator<T> iterator, 
			FileOutputStream writeStream) throws Exception {
		while (iterator.hasNext()) {
			writeStream.write(iterator.next().toString().getBytes());
			writeStream.write("\r\n".getBytes());
		}
		
	}
	
	public Boolean authenticate(String username, String password){
		if (credentials.containsKey(username) 
				&& credentials.get(username).getPassword().equals(password)){
			return credentials.get(username).isNurse();
		} else {
			return null;
		}
	}
}
