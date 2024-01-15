package data;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import business.Person;
import exceptions.OutOflimitException;
import exceptions.PersonAlreadyExists;

public class RandomIO {
	private RandomAccessFile personFile;
	
	//custom constructor
	public RandomIO() {
		try {
			personFile = new RandomAccessFile("person.dat", "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//method to add person
		public void addPerson(Person person) throws IOException, PersonAlreadyExists {
			if(doesRecordExist(person))
				throw new PersonAlreadyExists("person with record id "+person.getRecordId()+ " already exists");
			personFile.seek(personFile.length());
			personFile.writeInt(person.getRecordId());
			personFile.writeUTF(person.getFirstName());
			personFile.writeUTF(person.getLastName());
			personFile.writeUTF(person.getPhone());
			personFile.writeInt(person.getAge());
			
		}

		//method to check record already exist
		public boolean doesRecordExist(Person person) {
			for(Person currentPerson : fetchPersonRecords()) {
				if(currentPerson.getRecordId() == person.getRecordId())
					return true;
			}
			return false;
		}
		
		//method to get person
		public Person getPerson(int recordID) {
	        for(Person person : fetchPersonRecords()) {
	        	if(person.getRecordId() == recordID)
	        		return person;
	         }
			return null;
		}

		//method to fetch person record
		public List<Person> fetchPersonRecords(){
			List<Person> persons = new ArrayList<Person>();
			
			try {
				personFile.seek(0);
				while (true) {
					int recordId = personFile.readInt();
					String firstName = personFile.readUTF();
					String lastName = personFile.readUTF();
					String phone = personFile.readUTF();
					int age = personFile.readInt();
					
					try {
						persons.add(new Person(recordId, firstName, lastName, phone, age));
					} catch (OutOflimitException e) {
						e.printStackTrace();
					}
				}
			} catch (EOFException ex) {
				return persons;
			} catch (IOException e) {
				return persons;
			}
			

		}
		
}
