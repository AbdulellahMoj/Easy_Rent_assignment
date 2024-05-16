//Passenger class
//Passenger class is used to create passenger objects with passport number and name

package booking_system_project;

public class Passenger {
    private String passportNumber;
    private String name;

    // Constructors--------------------------------
    public Passenger(String passportNumber, String name) {
        this.passportNumber = passportNumber;
        this.name = name;
    }

    // getters--------------------------------
    public String getPassportNumber() {
        return passportNumber;
    }

    public String getName() {
        return name;
    }

    // Setters--------------------------------
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateDetails(String newName) {

        this.name = newName;
    }

    // toString--------------------------------
    public String toString() {

        return "Passenger: " + name + " Passport Number: " + passportNumber;
    }

}
