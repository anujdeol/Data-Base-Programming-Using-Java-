package data;

import java.io.*;

import business.Person;

public class RandomIO {
	private static final long serialVersionUID = -1752296510868884441L;

	public static FileInputStream file1;
	public static ObjectInputStream input;
	public static FileOutputStream filewrite;
	public static ObjectOutputStream oos;

	static {
		try {
			filewrite = new FileOutputStream("src/persons.dat", true);
			oos = new ObjectOutputStream(filewrite);

			file1 = new FileInputStream("src/persons.dat");
			input = new ObjectInputStream(file1);

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	// Method to add person object to the binary file
	public static void addPerson(Person person) throws IOException {

		 if (person == null) {
		        throw new IllegalArgumentException("Person object cannot be null.");
		    }

		    try {
		        System.out.println(person);
		        oos.writeObject(person);

		    } catch (IOException e) {
		        System.out.println(e);
		    }
	}

	// Method to find a person based on record number and return person
	public static Person findPerson(int recordNumber) {
		if (recordNumber < 0) {
	        throw new IllegalArgumentException("Record number cannot be negative.");
	    }
	    Person p = null;
	    try {
	        while (true) {
	            p = (Person) input.readObject();
	            int recid = p.getrecordid();
	            if (recid == recordNumber) {
	                return p;
	            }
	        }
	    } catch (ClassNotFoundException | IOException ex) {
	    }
	    return p;        
	}

}
