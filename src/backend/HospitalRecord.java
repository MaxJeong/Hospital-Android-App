package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Master HashMap of all patients.
 * 
 * @author SeongMinJeong, JunaidPatel, EricCyr, JackYiu, YiChenZhu
 * 
 */
public class HospitalRecord {
	// Maps HealthCardNumber to Patient Object
	private static Map<String, Patient> listofPatients = new HashMap<String, 
			Patient>();
	// Helps compare patient's arrival time.
	private static Comparator<Patient> byTime = new Comparator<Patient>(){
		@Override
		public int compare(Patient arg0, Patient arg1) {
			// TODO Auto-generated method stub
			
			if (arg0.getAdmissionDate().after(arg1.getAdmissionDate())){
				System.out.println(arg0.getHcn()+" is after "+arg1.getHcn());
				return -1;
			} else if (arg0.getAdmissionDate().
					before(arg1.getAdmissionDate())){
				System.out.println(arg0.getHcn()+" is before "+arg1.getHcn());
				return +1;
			} else {
				System.out.println(arg0.getHcn()+" is same as "+
						arg1.getHcn());
				return 0;
			}
		}
	};
	
	/**
	 * Constructor for a hospital record. Takes no parameters.
	 */
	public HospitalRecord() {
	}

	/**
	 * Returns the list of patients.
	 * 
	 * @return a HashMap of patients
	 */
	public static Map<String, Patient> getListOfPatients() {
		return listofPatients;
	}

	/**
	 * Query listofPatients and return a List of patients unseen by doctor.
	 * 
	 * @return a List of patients
	 */
	private static ArrayList<Patient> getListOfUnseenPatients() {
		System.out.println("ListofPateints: "
				+ listofPatients.entrySet().size());
		Iterator<Entry<String, Patient>> it = listofPatients.entrySet()
				.iterator();
		ArrayList<Patient> unseenPatients = new ArrayList<Patient>();
		while (it.hasNext()) {
			Patient curPatient = it.next().getValue();
			if (curPatient.getDateSeenByDoctor() == null) {
				unseenPatients.add(curPatient);
			}
		}
		System.out.println("Collect list of unseen patients: "
				+ unseenPatients.size());
		return unseenPatients;
	}

	/**
	 * Returns a Map of the most urgent patients ordered according to hospital
	 * policy that are unseen by doctor.
	 * @return
	 */
	public static Map<String, Patient> getListOfMostUrgentPatients(){
		Map<String, Patient> orderedPatients = new LinkedHashMap
				<String, Patient>();
		Map<String, Patient> urgentPatients = new LinkedHashMap
				<String, Patient>();
		Map<String, Patient> lessUrgentPatients = new LinkedHashMap
				<String, Patient>();
		Map<String, Patient> nonUrgentPatients = new LinkedHashMap
				<String, Patient>();
		Map<String, Patient> noVSPatients = new LinkedHashMap
				<String, Patient>();
		Iterator<Patient> it = getListOfUnseenPatients().iterator();
		// Retrieve Urgent Patients and place them into 3 separate map
		while (it.hasNext()){
			Patient curPatient = it.next();
			if (curPatient.getUrgency().getPatientCategory().
					equals("Urgent")){
				urgentPatients.put(Integer.toString(curPatient.getHcn()), 
						curPatient);
			} else if (curPatient.getUrgency().getPatientCategory().
					equals("Less Urgent")){
				lessUrgentPatients.put(Integer.toString(curPatient.getHcn()), 
						curPatient);
			} else if (curPatient.getUrgency().getPatientCategory().
					equals("Non Urgent")){
				nonUrgentPatients.put(Integer.toString(curPatient.getHcn()), 
						curPatient);
			} else {
				// Patient Category is null, should never happen.
				noVSPatients.put(Integer.toString(curPatient.getHcn()), 
						curPatient);
			}
		}
		// Merge the 3 urgent lists into one.
		orderedPatients.putAll(urgentPatients);
		orderedPatients.putAll(lessUrgentPatients);
		orderedPatients.putAll(nonUrgentPatients);
		orderedPatients.putAll(noVSPatients);
		System.out.println("Sort:\n UrgentPatients: "+urgentPatients.size()+ 
				"\nLess: "+lessUrgentPatients.size()+"\nnon:"+
				nonUrgentPatients.size()+"\nnoVS:"+noVSPatients.size());
		Iterator<Entry<String, Patient>> it2 = orderedPatients.entrySet().
				iterator();
		while (it2.hasNext()){
			System.out.println(it2.next().toString());
		}
		return orderedPatients;
	}
	
	
	/**
	 * Returns a Map of the earliest arriving patients that are unseen by the
	 * doctor.
	 * @return Map of the earliest arriving patients that are unseen by the
	 * doctor.
	 */
	public static Map<String, Patient> getListOfEarliestArrivingPatients(){
		ArrayList<Patient> unseenPatients = getListOfUnseenPatients();
		Iterator<Patient> it = unseenPatients.iterator();
		Collections.sort(unseenPatients, byTime);
		Map<String, Patient> orderedPatients = new LinkedHashMap<String, 
				Patient>();
		while (it.hasNext()){
			Patient curPatient = it.next();
			orderedPatients.put(Integer.toString(curPatient.getHcn()), 
					curPatient);
		}
		return orderedPatients;
		
	}
}