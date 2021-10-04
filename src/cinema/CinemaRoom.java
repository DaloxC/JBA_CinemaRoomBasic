/* In this project, you will create an application that helps manage a cinema theatre: sell tickets,
 check available seats, see sales statistics, and more.

Requirements:
The cinema must allow you to buy tickets and show the current status of the seating arrangement.
The cinema must allow you to see the statistics of the sale.
The cinema must have validations for both sales and the seating's.

DaloxC
*/
// Code of local tests
package cinema;

import java.util.Scanner;

public class CinemaRoom {
    // Block to initialize variables and scanner.
    static Scanner s = new Scanner(System.in);

    static int rows;
    static int seats;
    static int totalSeats;
    static int rowsCoordinates;
    static int seatsCoordinates;
    static int ticketPrice;
    static final int backRowsPrice = 8;
    static final int frontRowsPrice = 10;
    static final char SEAT = 'S';
    static char[][] cinema;

    // Block with the main menu of the cinema.
    enum Status {
       CHOOSE, SHOW, BUY, EXIT
    }

    static Status curStatus = Status.CHOOSE;

    public static void main(String[] args) {

        // Block to get the required input data for creation of the cinema.
        System.out.println("Enter the number of rows:");
        rows = s.nextInt();
        System.out.println("Enter the number of seats in each row");
        seats = s.nextInt();
        makeCinema();

        // Block that divides the different options to be executed.
        do {
            System.out.println(curStatus);
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("0. Exit");
            int action = s.nextInt();
            switch (action) {
                // Block that access the printCinema method.
                case 1:
                    curStatus = Status.SHOW;
                    printCinema();
                    break;
                // Block that access the seatCoordinates method.
                case 2:
                    curStatus = Status.BUY;
                    seatCoordinates();
                    break;
                // Block that access the exit method.
                case 0:
                    exit();
                    break;
            }
        } while (curStatus != Status.EXIT);
    }

    // Block to MAKE CINEMA
    public static void makeCinema() {

        // Block to create the cinema with the values input.
        if (rows <= 9 && seats <= 9) {
            cinema = new char[rows][seats];
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = SEAT;
            }
        }
        curStatus = Status.CHOOSE;
    }

    // Block to PRINT CINEMA
    public static void printCinema() {

        //Block to print the rows and seats of the cinema.
        System.out.println("\nCinema:");
        System.out.print(" ");
        for (int i = 0; i < cinema[0].length; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();

        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }
        curStatus = Status.CHOOSE;
    }

    // Block to SEAT COORDINATES
    public static void seatCoordinates() {

        // Block to get the required input data for the ticket price.
        System.out.println("\nEnter a row number:");
        rowsCoordinates = s.nextInt();
        System.out.println("Enter a seat number in that row:");
        seatsCoordinates = s.nextInt();

        // Block to define the ticket price.
        totalSeats = cinema.length * cinema[0].length;
        if (totalSeats <= 60) {
            ticketPrice = 10;
        } else {
            int seatsFront = cinema.length / 2;
            if (rowsCoordinates <= seatsFront) {
                ticketPrice = frontRowsPrice;
            } else {
                ticketPrice = backRowsPrice;
            }
        }
        System.out.println("\nTicket Price: $" + ticketPrice);

        // Block to change the cinema seating arrangement.
        if (rowsCoordinates <= cinema.length && seatsCoordinates <= cinema[0].length) {
            if (cinema[rowsCoordinates - 1][seatsCoordinates - 1] != 'B') {
                cinema[rowsCoordinates - 1][seatsCoordinates - 1] = 'B';
            }
        }
        curStatus = Status.CHOOSE;
    }

    // Block to EXIT
    public static void exit(){
        curStatus = Status.EXIT;
    }
}
