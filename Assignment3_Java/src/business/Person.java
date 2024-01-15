package business;

import exceptions.OutOflimitException;

public class Person {
	
	private String firstName;
	private String lastName;
	private String phone;
	private int age;
	private int recordId;
	
	public static final int MAX_FIRSTNAME_SIZE = 20;
	public static final int MAX_LASTTNAME_SIZE = 25;
	public static final int MAX_PHONE_SIZE = 10;
	
	//custom constructor 
	public Person(int recordId, String firstName,String lastName,String phone,int age) throws OutOflimitException {
		setRecordId(recordId);
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setAge(age);
		
	}
	
	//getter setter methods for firstName,lastName,phone,age,recordId
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws OutOflimitException{
		if(firstName.length()>MAX_FIRSTNAME_SIZE) {
			throw new OutOflimitException("The legnth of "+firstName+" exceeds maximum size of first name. The size limit is : "+MAX_FIRSTNAME_SIZE);
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws OutOflimitException{
		if(lastName.length()>MAX_LASTTNAME_SIZE) {
			throw new OutOflimitException("The legnth of "+lastName+" exceeds maximum size of last name. The size limit is : "+MAX_LASTTNAME_SIZE);
		}
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws OutOflimitException{
		if(phone.length()>MAX_PHONE_SIZE) {
			throw new OutOflimitException("The legnth of "+phone+" exceeds maximum size of Phone number. The size limit is : "+MAX_PHONE_SIZE);
		}
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) throws OutOflimitException{
		if(recordId >= 1000 || recordId < 1) {
			throw new OutOflimitException("The legnth of "+recordId+" exceeds maximum size of recordId. The size limit is 3");
		}
		this.recordId = recordId;
	}
	

}


