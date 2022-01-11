import java.time.LocalDate;

public class PhoneBook {
    private String phoneNumber;
    private String groupOfContacts;
    private String fullName;
    private String gender;
    private String address;
    private LocalDate dateOfBirth;
    private String email;

    public PhoneBook(String phoneNumber, String groupOfContacts, String fullName, String gender, String address, LocalDate dateOfBirth, String email) {
        this.phoneNumber = phoneNumber;
        this.groupOfContacts = groupOfContacts;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public PhoneBook(String groupOfContacts, String fullName, String gender, String address, LocalDate dateOfBirth, String email) {
        this.groupOfContacts = groupOfContacts;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroupOfContacts() {
        return groupOfContacts;
    }

    public void setGroupOfContacts(String groupOfContacts) {
        this.groupOfContacts = groupOfContacts;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return phoneNumber + "," + groupOfContacts + "," + fullName + "," +  gender + "," + address + "," + dateOfBirth.toString() + "," + email;
    }
}
