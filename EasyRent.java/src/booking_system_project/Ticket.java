//Ticket class
//Ticket class is a class that represents a ticket for a passenger on a flight. 
package booking_system_project;

public class Ticket {
    // Attributes--------------------------------
    private Flight flight;
    private Passenger passenger;
    private int seatRow;
    private String seatNumber;
    private String classType;
    private int reaervationConfirmationNumber;
    static int reaervationNumber = 0;

    // Constructors--------------------------------
    public Ticket(Flight flight, Passenger passenger, int seatRow, String seatNumber, String classType) {
        this.flight = flight;
        this.passenger = passenger;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.reaervationConfirmationNumber = reaervationNumber + 100;
        reaervationNumber++;
    }

    // getters--------------------------------
    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getClassType() {
        return classType;
    }

    public int getReaervationConfirmationNumber() {
        return reaervationConfirmationNumber;
    }

    // Setters--------------------------------
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public void setReaervationConfirmationNumber(int reaervationConfirmationNumber) {
        this.reaervationConfirmationNumber = reaervationConfirmationNumber;
    }

    // methods--------------------------------
    public double ticketPrice() {
        double price = 2000;
        double tPrice;
        // Class type price
        if (classType.equals("FirstClass")) {
            price += (price * 4);
        } else if (classType.equals("BusinessClass")) {
            price += (price * 2);
        }
        // Hajj discount
        if (flight.getArrivalCity().equalsIgnoreCase("JED")) {
            price = price - (price * 0.2);
        }
        // VAT 15%
        price += (price * 0.15);
        return price;
    }

    public String toString() {
        return "Reservation Confirmation Number= " + reaervationConfirmationNumber + ", Flight Number="
                + flight.getFlightNumber() + ", Passenger Name= " + passenger.getName() + ", Seat Number= " + seatRow
                + "" + seatNumber + " , classType= " + classType;
    }
}
