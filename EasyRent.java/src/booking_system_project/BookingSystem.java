//BookingSystem class
//This class is the main class of the project, it contains the main method, and the methods to generate the flight invoice, generate the invoice, book the ticket, search for the passenger, search for the flight, and search for the ticket.

package booking_system_project;

//Importing the necessary:
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookingSystem {
    // Attributes--------------------------------
    private final int MAX_Ticket = 100;
    static PrintWriter output = null;

    // methods----------------------------------------------

    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // main method--------------------------------
    // main method with exception FileNotFoundException
    public static void main(String[] args) throws FileNotFoundException {
        // files; input and output--------------------------------
        File input1 = new File(
                "C:\\Users\\abdul\\Desktop\\CPCS203\\Ass1_Abdulellah_Mojalled_2339058\\Ass1_cpcs203\\flight_passenger.txt");

        File input2 = new File(
                "C:\\Users\\abdul\\Desktop\\CPCS203\\Ass1_Abdulellah_Mojalled_2339058\\Ass1_cpcs203\\inputCommands.txt");

        File outputFile = new File("output.txt");

        // make sure files exist--------------------------------
        if (!input1.exists() || !input2.exists()) {
            System.out.println("File not found");
            System.exit(0);
        }
        // Scanner and printwriter--------------------------------
        Scanner inputFP = new Scanner(input1);
        Scanner inputC = new Scanner(input2);

        output = new PrintWriter(outputFile);

        // number of flights and passengers--------------------------------
        int numFlights = inputFP.nextInt();
        int numPassengers = inputFP.nextInt();
        // creating arrays--------------------------------
        // using the number of flights and passengers to create the arrays
        Flight[] flights = new Flight[numFlights];
        Passenger[] passengers = new Passenger[numPassengers];
        // counters for flights and passengers
        int flightcount = 0;
        int passengercount = 0;

        // ticket array (max 100?)--------------------------------
        Ticket[] ticket = new Ticket[100];
        int ticketcount = 0;

        // reading from fileFP--------------------------------

        // explanaition of the do while loop:
        // the loop will keep reading from the fileFP until there is no more data to
        // read
        // the loop will read the command from the file and then check if the command is
        // AddFlight or AddPassenger
        do {
            // read command
            String command = inputFP.next();

            // if the command is AddFlight, the loop will read the (flight number, departure
            // city, arrival city, gate number, rows, and columns), it will create a new
            // flight object and add it to the flights array

            if (command.equalsIgnoreCase("AddFlight")) {
                // read flight number, departure city, arrival city, gate number, rows, and
                // columns
                String flightNumber = inputFP.next();
                String departureCity = inputFP.next();
                String arrivalCity = inputFP.next();
                int gateNumber = inputFP.nextInt();
                int rows = inputFP.nextInt();
                int column = inputFP.nextInt();
                // flight object, and add it to the flights array
                Flight flight = new Flight(flightNumber, departureCity, arrivalCity, gateNumber, rows, column);
                flights[flightcount] = flight;
                flightcount++;
                // wrtie to out file, that the flight has been added successfully
                output.println("Flight " + flightNumber + " added successfully");
            }

            // if the command is AddPassenger, the loop will read the (passport number and
            // name), it will create a new passenger object and add it to the passengers
            // array

            else if (command.equalsIgnoreCase("AddPassenger")) {
                // read passport number and name
                String PassportNumber = inputFP.next();
                String Name = inputFP.next();
                // passenger object, and add it to the passengers array
                Passenger passenger = new Passenger(PassportNumber, Name);
                passengers[passengercount] = passenger;
                passengercount++;
                // write to out file, that the passenger has been added successfully
                output.println("Passenger " + Name + " added successfully");

            }

        } while (inputFP.hasNext());
        // end of reading from fileFP

        // --------------------------------

        // reading from fileC
        // explanaition of the do while loop:
        // the loop will keep reading from the fileC until there is no more data to read
        // the loop will read the command from the file and then check if the command is
        // BookTicket or GenerateFlightInvoice or GenerateInvoice
        do {
            String command = inputC.next();
            // if the command is BookTicket, the loop will read the (passport number, flight
            // number, seat row, seat column, and class type), it will check if the
            // passenger is registered, if the flight exists, and if the seat is available,
            // then it will book the ticket and add it to the ticket array

            if (command.equalsIgnoreCase("BookTicket")) {
                output.println("\n*********************BookTicket************************** \n");
                // read passport number, flight number, seat row, seat column, and class type
                String PassengerPassNumb = inputC.next();
                String flightNumb = inputC.next();
                int seatRow = inputC.nextInt();
                String seatCol = inputC.next();
                String classType = inputC.next();

                // check if the passenger is registered
                bookTicket(ticket, ticketcount++, passengers, PassengerPassNumb, flights, flightNumb, seatRow, seatCol,
                        classType);
            }
            // if the command is GenerateFlightInvoice, the loop will read the flight
            // number, it will check if the flight exists, then it will generate the flight
            // invoice
            else if (command.equalsIgnoreCase("GenerateIFlightnvoice")) {
                output.println("\n*********************Generate Flight Invoice************************** \n");
                // read flight number
                String flightNumb = inputC.next();
                // check if the flight exists
                int flightIndex = SearchFlight(flights, flightNumb);
                // if the flight exists, generate the flight invoice
                GenerateIFlightnvoice(ticket, flightIndex, flights, flightNumb);
            }

            // if the command is GenerateInvoice, the loop will read the reservation number,
            // it will check if the ticket exists, then it will generate the invoice
            else if (command.equalsIgnoreCase("GenerateInvoice")) {
                output.println("\n*********************Generate Ticket Invoice************************** \n");
                // read reservation number
                int Res = inputC.nextInt();
                // check if the ticket exists
                int ticketIndex = SearchTicket(ticket, ticketcount, Res);
                // if the ticket exists, generate the invoice
                GenerateInvoice(ticket, ticketIndex, Res);
            }

            // if the command is GenerateInvoice, the loop will read the reservation number,
            // it will check if the ticket exists, then it will generate the invoice

        } while (inputC.hasNext());
        // end of reading from fileC

        // flush the output file--------------------------------
        output.flush();
        // close the input and output files--------------------------------
        inputFP.close();
        inputC.close();
        output.close();
    }
    // end of main method

    // -----------------------------------------------

    public static void GenerateIFlightnvoice(Ticket[] ticket, int tindex, Flight[] flights, String flightnumber) {
        if (tindex == -1) {
            // if the flight does not exist, write to the output file that the flight does
            // not exist
            output.println("Flight Not Found or No Ticket booked for this flight");
        } else {
            // flight exist, generate the flight invoice

            // write to the output file the flight number
            output.println("Seat Plane for flight " + flightnumber + " is: ");

            output.println("************************************");
            // print the seat plan
            flights[tindex].printSeatPlan(output);
            // total invoice price e be updated later
            double totalInvoice_Price = 0;
            String[][] seatMap = flights[tindex].getSeatMap();
            // loop through the seat map to find the booked seats
            for (int i = 0; i < seatMap.length; i++) {

                for (int j = 0; j < seatMap[i].length; j++) {
                    // search for the ticket
                    if (!seatMap[i][j].equals("O")) {
                        // search for the ticket
                        int ticketind = -1;
                        for (int k = 0; k < ticket.length; k++) {
                            // if the ticket exists, update the ticket index and break
                            if (ticket[k] != null && ticket[k].getPassenger().getPassportNumber().equals(seatMap[i][j])
                                    && ticket[k].getFlight().getFlightNumber().equals(flightnumber)) {

                                // update the ticket index
                                ticketind = k;

                                break;

                            }
                        }
                        // write to the output file the ticket information
                        output.println("Ticket Information: ");
                        output.println(ticket[ticketind].toString());
                        // update the total invoice price
                        totalInvoice_Price += ticket[ticketind].ticketPrice();
                    }
                }
            }
            output.println();
            // write to the output file the total invoice price
            output.println("Total Invoice price =" + totalInvoice_Price);

        }
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // Generate invoice method--------------------------------
    public static void GenerateInvoice(Ticket[] ticket, int tindex, int Res) {

        if (tindex == -1) {
            // if the ticket does not exist, write to the output file that the ticket does
            // not exist
            output.println("Reservation Number is not available");

        } else {
            // ticket exists, generate the invoice

            // write to the output file the ticket information
            output.println("Ticket Information: ");
            output.println(ticket[tindex].toString());
            // write to the output file the total invoice price
            output.println("Total ticket price = " + ticket[tindex].ticketPrice());
        }
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // Book ticket method--------------------------------
    public static boolean bookTicket(Ticket[] tickets, int tindex, Passenger[] passenger, String passPort,
            Flight[] flights, String flightNumber, int seatRow, String seatcol, String classType) {

        // search for the passenger
        int passIndex = SearchPassenger(passenger, passPort);
        if (passIndex == -1) {
            // if the passenger is not registered, write to the output file that the
            // passenger is not registered
            output.println("Passenger with Passport number " + passPort + " is not Registered");
            return false;
        }
        // check if the flight exists
        else {
            // search for the flight
            int flightIndex = SearchFlight(flights, flightNumber);
            if (flightIndex == -1) {
                // if the flight does not exist, write to the output file that the flight does
                // not exist
                output.println("Flight " + flightNumber + " Not Found");
                return false;
            } else {
                // check if the seat is available
                boolean seatAvilable = flights[flightIndex].isSeatAvilable(seatRow, seatcol.charAt(0));
                if (seatAvilable == false) {
                    // if the seat is not available, write to the output file that the seat is not
                    // available
                    output.println("Seat " + seatRow + seatcol + " is already Reserved Or Not found");
                    return false;
                } else {
                    // if the passenger is registered, the flight exists, and the seat is available,
                    // book the ticket

                    // create a new ticket object and add it to the ticket array
                    Ticket ticketO = new Ticket(flights[flightIndex], passenger[passIndex], seatRow, seatcol,
                            classType);
                    tickets[tindex] = ticketO;

                    // book the seat
                    flights[flightIndex].bookSeat(seatRow, seatcol.charAt(0), passPort);

                    // write to the output file that the seat has been booked successfully
                    output.println("Seat booked successfully.");
                    // write to the output file the ticket information
                    output.println("Ticket Information: ");
                    output.println(tickets[tindex].toString());
                    return true;
                }
            }
        }

    }
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Search methods--------------------------------

    // Search passenger method--------------------------------
    public static int SearchPassenger(Passenger[] passenger, String passPort) {
        // loop through the passenger array to find the passenger with the passport
        // number
        int index = -1;
        for (int i = 0; i < passenger.length; i++) {
            if (passenger[i].getPassportNumber().equalsIgnoreCase(passPort)) {
                index = i;
                break;
            }
        }
        return index;
    }
    // -----------------------------------------------

    // Search flight method--------------------------------
    public static int SearchFlight(Flight[] flights, String flightNumber) {
        // loop through the flight array to find the flight with the flight number
        int index = -1;
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getFlightNumber().equalsIgnoreCase(flightNumber)) {
                index = i;
                break;
            }
        }
        return index;

    }
    // -----------------------------------------------

    // Search ticket method--------------------------------
    public static int SearchTicket(Ticket[] ticket, int tindex, int Res) {
        // loop through the ticket array to find the ticket with the reservation number
        int index = -1;
        for (int i = 0; i < tindex; i++) {
            if (ticket[i] != null && ticket[i].getReaervationConfirmationNumber() == Res) {
                index = i;
                break;
            }

        }
        return index;
    }

}
