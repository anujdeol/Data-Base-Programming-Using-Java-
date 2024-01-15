package business;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class Person implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1752296510868884441L;
	public static final int MAX_FIRST_NAME_LENGTH = 20;
    public static final int MAX_LAST_NAME_LENGTH = 25;
    public static final int MAX_PHONE_LENGTH = 10;

    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private int recordid;
    public Person( String firstName, String lastName, String phone, int age, int recordid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.recordid = recordid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() > MAX_FIRST_NAME_LENGTH) {
        	JOptionPane.showMessageDialog(null, "First name is too long.", "Message",
					JOptionPane.INFORMATION_MESSAGE);
        	
            throw new IllegalArgumentException("First name is too long.");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > MAX_LAST_NAME_LENGTH) {
        	JOptionPane.showMessageDialog(null, "Last name is too long.", "Message",
					JOptionPane.INFORMATION_MESSAGE);
            throw new IllegalArgumentException("Last name is too long.");
        }
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.length() > MAX_PHONE_LENGTH) {
        	JOptionPane.showMessageDialog(null, "Phone number is too long", "Message",
					JOptionPane.INFORMATION_MESSAGE);
            throw new IllegalArgumentException("Phone number is too long.");
            
        }
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getrecordid() {
        return recordid;
    }

    public void setrecordid(int recordid) {
        this.recordid = recordid;
    }

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", age=" + age
				+ ", recordid=" + recordid + "]";
	}
    

}
