/* In this project, you will create an application that helps manage a cinema theatre: sell tickets,
 check available seats, see sales statistics, and more.

Requirements:
The cinema must allow you to buy tickets and show the current status of the seating arrangement.
The cinema must allow you to see the statistics of the sale.
The cinema must have validations for both sales and the seating's.

DaloxC
*/
package cinema;

import java.util.*;

public class MasterCinemaRoom {

    // Block to initialize variables and scanner.
    static Scanner s = new Scanner(System.in);

    static int rows, seats, totalSeats, rowsCoordinates, seatsCoordinates, ticketPrice;
    static int purchasedTickets, currentIncome, totalIncome;
    static double percentage;
    static final int backRowsPrice = 8, frontRowsPrice = 10;
    static final char SEAT = 'S';
    static char[][] cinema;


    // Block with the main menu of the cinema.
    enum Index {
        OPTION, SHOW, BUY, STATS, EXIT
    }

    static Index curIndex = Index.OPTION;

    public static void main(String[] args) {

        // Block to get the required input data for creation of the cinema.
        System.out.println("Enter the number of rows:");
        rows = s.nextInt();
        System.out.println("Enter the number of seats in each row");
        seats = s.nextInt();
        totalSeats = rows * seats;
        makeCinema();

        // Block that divides the different options to be executed.
        do {
            System.out.println("\n" + curIndex);
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int action = s.nextInt();
            switch (action) {
                // Block that access the printCinema method.
                case 1:
                    curIndex = Index.SHOW;
                    printCinema();
                    break;
                // Block that access the seatCoordinates method.
                case 2:
                    curIndex = Index.BUY;
                    seatCoordinates();
                    break;
                // Block that access the statistics method.
                case 3:
                    curIndex = Index.STATS;
                    statistics();
                    break;
                // Block that access the exit method.
                case 0:
                    exit();
                    break;
            }
        } while (curIndex != Index.EXIT);
    }

    // Block to MAKE CINEMA
    public static void makeCinema() {

        // Block to create the cinema with the values input.
        if (rows <= 9 && seats <= 9) {
            cinema = new char[rows][seats];
        } else {
            System.out.println("Wrong input");
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = SEAT;
            }
        }
        curIndex = Index.OPTION;
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
        curIndex = Index.OPTION;
    }

    // Block to SEAT COORDINATES
    public static void seatCoordinates() {

        // Block to get the required input data for the ticket price & seating arrangement.
        System.out.println("\nEnter a row number:");
        rowsCoordinates = s.nextInt();
        System.out.println("Enter a seat number in that row:");
        seatsCoordinates = s.nextInt();

        // Block to change the cinema seating arrangement.
        if (rowsCoordinates <= cinema.length && seatsCoordinates <= cinema[0].length) {
            if (cinema[rowsCoordinates - 1][seatsCoordinates - 1] != 'B') {
                cinema[rowsCoordinates - 1][seatsCoordinates - 1] = 'B';
                ticketPrice();
                // Block that prints if the seat is already taken.
            } else if (cinema[rowsCoordinates - 1][seatsCoordinates - 1] == 'B') {
                System.out.println("\nThat ticket has already been purchased!");
                seatCoordinates();
            }
        }
        // Block to print when the coordinates are outside the limits.
        if (rowsCoordinates > 9 || seatsCoordinates > 9 || rowsCoordinates < 0 || seatsCoordinates < 0) {
            System.out.println("\nWrong input!");
            seatCoordinates();
        }
        curIndex = Index.OPTION;
    }

    // Block to TICKET PRICE
    public static void ticketPrice() {

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
        //Variables for stats.
        purchasedTickets += 1;
        currentIncome += ticketPrice;

        System.out.println("\nTicket Price: $" + ticketPrice);
    }

    // Block to STATISTICS
    public static void statistics() {

        //Block to print stats.
        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %s%%\n", percentage());
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome());
        curIndex = Index.OPTION;
    }

    // Block to PERCENTAGE

    public static String percentage() {

        percentage = (double) purchasedTickets * 100 / totalSeats;
        return String.format("%.2f", percentage);
    }

    // Block to TOTAL INCOME
    public static int totalIncome() {
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        }
        if (totalSeats > 60) {
            if (rows % 2 != 0) {
                totalIncome = (rows - 1) / 2 * seats * 10 + ((rows + 1) / 2 * seats * 8);
            } else {
                totalIncome = rows / 2 * seats * 10 + (rows / 2 * seats * 8);
            }
        }
        return totalIncome;
    }

    // Block to EXIT
    public static void exit() {
        curIndex = Index.EXIT;
    }
}

