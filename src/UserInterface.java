import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {

    public void runBabyNames() {
        BabyNameDataReader reader = new BabyNameDataReader();
        HashMap<Integer, ArrayList<BabyName>> babyNamesData = reader.loadBabyNamesData();

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("Q")) {
            printMenu();

            input = scanner.nextLine();
            System.out.println();

            handleUserInput(input, scanner, reader, babyNamesData);
            System.out.println();
        } 

        scanner.close();
    }

    public void printMenu() {
        System.out.println("Choose an option: ");
        System.out.println("P  Show most popular names for a given year and gender.");
        System.out.println("R  Show rank for a given name, gender, and year.");
        System.out.println("Y  Find the year in which the given name-gender combination was most popular.");
        System.out.println("Q  Quit");
    }

    // Method to handle user input and delegate to the appropriate action
    public void handleUserInput(String input, Scanner scanner, BabyNameDataReader reader, HashMap<Integer, ArrayList<BabyName>> babyNamesData) {
        if (input.equals("P")) {
            processMostPopularName(scanner, reader, babyNamesData);
        } else if (input.equals("R")) {
            processRankForName(scanner, reader, babyNamesData);
        } else if (input.equals("Y")) {
            processMostPopularYear(scanner, reader, babyNamesData);
        } else if (input.equals("Q")) {
            System.out.println("Quitting program.");
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    private String getName(Scanner scanner) {
        System.out.println("Enter the name.");
        return scanner.nextLine();
    }
    private String getGender(Scanner scanner) {
        System.out.println("Enter the gender as a capital letter.");
        return scanner.nextLine();
    }
    private int getYear(Scanner scanner) {
        System.out.println("Enter the year.");
        return scanner.nextInt();
    }

    // Method to process the option 'P' (Most popular names for a given year and gender)
    public void processMostPopularName(Scanner scanner, BabyNameDataReader reader, HashMap<Integer, ArrayList<BabyName>> babyNamesData) {
        String gender = getGender(scanner);
        int year = getYear(scanner);
        System.out.println();
        System.out.println(reader.getMostPopularNameForYear(gender, year, babyNamesData));
    }

    // Method to process the option 'R' (Rank for a given name, gender, and year)
    public void processRankForName(Scanner scanner, BabyNameDataReader reader, HashMap<Integer, ArrayList<BabyName>> babyNamesData) {
        String name = getName(scanner);
        String gender = getGender(scanner);
        int year = getYear(scanner); 
        System.out.println();
        System.out.println(reader.getRankForName(name, gender, year, babyNamesData));
    }

    // Method to process the option 'Y' (Most popular year for a given name-gender combination)
    public void processMostPopularYear(Scanner scanner, BabyNameDataReader reader, HashMap<Integer, ArrayList<BabyName>> babyNamesData) {
        String name = getName(scanner);
        String gender = getGender(scanner);
        System.out.println();
        System.out.println(reader.getMostPopularYearForName(name, gender, babyNamesData));
    }
}
