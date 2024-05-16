//Flight class
//Flight class is used to create a flight object 
//Flight object has attributes such as flight number, departure city, arrival city, gate number, and seat map( row, column)

package booking_system_project;

//imports--------------------------------
import java.io.PrintWriter;

public class Flight {
    // Attributes--------------------------------
    private String flightNumber;
    private String departmureCity;
    private String arrivalCity;
    private int gateNumber;
    private String[][] seatMap;
    private int row;
    private int column;

    // Constructors--------------------------------
    public Flight(String flightNumber, String departmureCity, String arrivalCity, int gateNumber, int row, int column) {
        this.flightNumber = flightNumber;
        this.departmureCity = departmureCity;
        this.arrivalCity = arrivalCity;
        this.gateNumber = gateNumber;
        this.row = row;
        this.column = column;
        this.seatMap = new String[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                seatMap[i][j] = "O";
            }
        }

    }

    // getters--------------------------------
    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartmureCity() {
        return departmureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public String[][] getSeatMap() {
        return seatMap;
    }

    // Setters--------------------------------
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartmureCity(String departmureCity) {
        this.departmureCity = departmureCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    // methods--------------------------------

    public boolean bookSeat(int row, char column, String PassengerID) {
        int indexRow = row - 1;

        // convert ascii value to index
        int indexColumn = column - 65;

        if (isSeatAvilable(indexRow, column)) {
            seatMap[indexRow][indexColumn] = PassengerID;
            return true;
        } else {
            return false;
        }

    }

    public boolean isSeatAvilable(int row, char column) {
        // convert ascii value to index
        int indexColumn = column - 65;
        // seat column should be less than the total column
        if (indexColumn < this.column && seatMap[row][indexColumn].equals("O")) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "flightNumber=" + flightNumber + ", departureCity=" + departmureCity + ", arrivalCity=" + arrivalCity
                + ", gateNumber=" + gateNumber + ", seatMap=" + seatMap + ", row=" + row + ", column=" + column;
    }

    public void printSeatPlan(PrintWriter output) {

        final String SEAT_FORMAT = " %-12s";

        // map header
        output.printf(" %-13s", "Row");
        for (int i = 0; i < column; i++) {
            int Char_Value = i + 97;
            char Char = (char) Char_Value;
            output.printf(SEAT_FORMAT, Char + "");
        }
        output.println();

        // map body
        for (int i = 0; i < seatMap.length; i++) {
            output.printf(SEAT_FORMAT, i);

            for (int j = 0; j < seatMap[i].length; j++) {
                // line to declare 'j'
                if (seatMap[i][j].equals("O")) {
                    output.printf(SEAT_FORMAT, "O");
                } else {
                    output.printf(SEAT_FORMAT, seatMap[i][j]);
                }
            }

            output.println();
        }
        output.println();
    }

}