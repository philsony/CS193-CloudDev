package sample.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class DoctorModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String specialization;
    private String contactNumber;
    private String birthDay;
    private String userName;
    private String passWord;
    private String email;
    
 

    /**
     * Creates an instance of 'Doctor'
     */
    public DoctorModel(){
        
    }
    
    /**
     * Creates an instance of 'Doctor' with attribute initialization
     */
    public DoctorModel(String firstName, String lastName, String address, String specialization,
            String contactNumber, String birthDay, String userName, String passWord,  String email){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setSpecialization(specialization);
        this.setContactNumber(contactNumber);
        this.setBirthDay(birthDay);
        this.setUserName(userName);
        this.setPassWord(passWord);
        this.setEmail(email);
    }
    
    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

 
    /**
     * get the email.
     *
     * @get the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Returns the email.
     *
     * @return the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DoctorModel other = (DoctorModel) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Sets the id.
     *
     * @param id
     *            the id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Returns the firstName.
     *
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Sets the firstName.
     *
     * @param firstName
     *            the firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Returns the lastName.
     *
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Sets the lastName.
     *
     * @param lastName
     *            the lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Returns the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Sets the address.
     *
     * @param address
     *            the address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Returns the specialization.
     *
     * @return the specialization
     */
    public String getSpecialization() {
        return specialization;
    }
    
    /**
     * Sets the specialization.
     *
     * @param specialization
     *            the specialization
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    /**
     * Returns the contactNumber.
     *
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }
    
    /**
     * Sets the contactNumber.
     *
     * @param contactNumber
     *            the contactNumber
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    /**
     * Returns the birthDay.
     *
     * @return the birthDay
     */
    public String getBirthDay() {
        return birthDay;
    }
    
    /**
     * Sets the birthDay.
     *
     * @param birthDay
     *            the birthDay
     */
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
    
    /**
     * Returns the serialVersionUID.
     *
     * @return the serivalVersionUID
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    /**
     * Returns the userName.
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * Sets the userName.
     *
     * @param userName
     *            the userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * Returns the passWord.
     *
     * @return the passWord
     */
    public String getPassWord() {
        return passWord;
    }
    
    /**
     * Sets the passWord.
     *
     * @param passWord
     *            the passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
