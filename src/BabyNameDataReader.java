//Anthony Mozloom

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BabyNameDataReader {

    // Constants for file path and year range
    private static final String FILE_PATH = "names/yob";
    private static final int START_YEAR = 1880;
    private static final int END_YEAR = START_YEAR + 143;

    // Method to create a data structure containing baby name data for each year
    // Creates a HashMap containing Integers and an ArrayList of BabyNames
    // Reads in baby names from each year into the HashMap
    public HashMap<Integer, ArrayList<BabyName>> loadBabyNamesData() {
        HashMap<Integer, ArrayList<BabyName>> babyNamesByYear = new HashMap<>();

        for (int year = START_YEAR; year <= END_YEAR; year++) {
            ArrayList<BabyName> babyNamesForYear = processYearData(year);
            babyNamesByYear.put(year, babyNamesForYear);
        }

        return babyNamesByYear;
    }

    // Method to process baby name data for a specific year
    private ArrayList<BabyName> processYearData(int year) {
        ArrayList<BabyName> babyNames = new ArrayList<>();
        String filename = FILE_PATH + year + ".txt";

        try (Scanner scanner = new Scanner(new File(filename))) {
            String currentGender = "F";
            int rank = 0;
            int lastFrequency = -1;

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                rank = processLine(babyNames, data, currentGender, rank, lastFrequency);

                currentGender = data[1];
                lastFrequency = Integer.parseInt(data[2]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return babyNames;
    }

    // Method to process a single line of baby name data and update the list
    // "data" is an array of baby data that was split on commas
    // Name should be at position 0 
    // Gender at position 1 and frequency at position 2
    private int processLine(ArrayList<BabyName> babyNames, String[] data, String currentGender, int rank, int lastFrequency) {
        String name = data[0];
        String gender = data[1];
        int frequency = Integer.parseInt(data[2]);

        if (!gender.equals(currentGender)) {
            rank = 1;
        } else if (frequency != lastFrequency) {
            rank++;
        }

        babyNames.add(new BabyName(name, gender, frequency, rank));
        return rank;
    }

    // Method to get the most popular name for a given year and gender
    public String getMostPopularNameForYear(String gender, int year, HashMap<Integer, ArrayList<BabyName>> babyNames) {
        if (year < START_YEAR || year > END_YEAR) {
            return "Invalid input year.";
        }

        ArrayList<BabyName> babyNamesForYear = babyNames.get(year);

        for (BabyName baby : babyNamesForYear) {
            if (baby.getGender().equals(gender)) {
                return formatBabyNameInfo(baby, year);
            }
        }

        return "No data available.";
    }

    // Method to get the rank of a given name for a specific year and gender
    public String getRankForName(String name, String gender, int year, HashMap<Integer, ArrayList<BabyName>> babyNames) {
        if (year < START_YEAR || year > END_YEAR) {
            return "Invalid input year.";
        }

        ArrayList<BabyName> babyNamesForYear = babyNames.get(year);

        for (BabyName baby : babyNamesForYear) {
            if (baby.getName().equals(name) && baby.getGender().equals(gender)) {
                return formatBabyNameInfo(baby, year);
            }
        }

        return "No data available.";
    }

    // Method to find the year when a name was most popular
    public String getMostPopularYearForName(String name, String gender, HashMap<Integer, ArrayList<BabyName>> babyNames) {
        int mostPopularYear = -1;
        int highestFrequency = 0;
        BabyName bestBabyYear = null;

        for (int year : babyNames.keySet()) {
            for (BabyName baby : babyNames.get(year)) {
                if (baby.getName().equals(name) && baby.getGender().equals(gender) && baby.getFrequency() > highestFrequency) {
                    highestFrequency = baby.getFrequency();
                    mostPopularYear = year;
                    bestBabyYear = baby;
                }
            }
        }

        if (bestBabyYear != null) {
            return formatBabyNameInfo(bestBabyYear, mostPopularYear);
        }

        return "No data available.";
    }

    // Method to format the baby name information for output
    private String formatBabyNameInfo(BabyName baby, int year) {
        return "The name " + baby.getName() + ", gender " + baby.getGender() + ", in the year " + year +
                ", occurred with frequency " + baby.getFrequency() + ", and rank " + baby.getRank() + ".";
    }

    public ArrayList<Integer> getAllRankings(String name, String gender, HashMap<Integer, ArrayList<BabyName>> babyNamesData) {
        ArrayList<Integer> rankings = new ArrayList<>();
    
        for (int year : babyNamesData.keySet()) {
            for (BabyName baby : babyNamesData.get(year)) {
                if (baby.getName().equals(name) && baby.getGender().equals(gender)) {
                    rankings.add(baby.getRank());
                    break;
                }
            }
        }
        return rankings;
    }
    
    public ArrayList<Integer> getRankingsInRange(String name, String gender, int startYear, int endYear, HashMap<Integer, ArrayList<BabyName>> babyNamesData) {
        ArrayList<Integer> rankings = new ArrayList<>();

        for (int year = startYear; year <= endYear; year++) {
            if (babyNamesData.containsKey(year)) {
                for (BabyName baby : babyNamesData.get(year)) {
                    if (baby.getName().equals(name) && baby.getGender().equals(gender)) {
                        rankings.add(baby.getRank());
                        break;
                    }
                }
            }
        }
        return rankings;
    }
    
    public BabyName getSameRankName(String name, String gender, int year, HashMap<Integer, ArrayList<BabyName>> babyNamesData) {
        int mostRecentYear = 2023;  // Replace with actual most recent year in data
        int targetRank = -1;

        // Get rank of the given name in the given year
        if (babyNamesData.containsKey(year)) {
            for (BabyName baby : babyNamesData.get(year)) {
                if (baby.getName().equals(name) && baby.getGender().equals(gender)) {
                    targetRank = baby.getRank();
                    break;
                }
            }
        }
      
        // Find the name with the same rank in the most recent year
        if (targetRank != -1 && babyNamesData.containsKey(mostRecentYear)) {
            for (BabyName baby : babyNamesData.get(mostRecentYear)) {
                if (baby.getRank() == targetRank && baby.getGender().equals(gender)) {
                    return baby;  // Return BabyName object with same rank in the most recent year
                }
            }
        }

        return null; // Return null if no matching rank found
    }
    
    public double getAverageRank(String name, String gender, int startYear, int endYear, HashMap<Integer, ArrayList<BabyName>> babyNamesData) {
        ArrayList<Integer> rankings = getRankingsInRange(name, gender, startYear, endYear, babyNamesData);
        
        if (rankings.isEmpty()) {
            return -1;  // Return -1 if no rankings are found
        }
        
        double total = 0;
        for (int rank : rankings) {
            total += rank;
        }
        return total / rankings.size();
    }
    
    public String getMostPopularStartingLetter(String gender, int startYear, int endYear, HashMap<Integer, ArrayList<BabyName>> babyNamesData) {
        HashMap<Character, ArrayList<String>> letterCount = new HashMap<>();

        // Iterate over each year in the range
        for (int year = startYear; year <= endYear; year++) {
            if (babyNamesData.containsKey(year)) {
                for (BabyName baby : babyNamesData.get(year)) {
                    if (baby.getGender().equals(gender)) {
                        char firstLetter = baby.getName().charAt(0);
                        letterCount.putIfAbsent(firstLetter, new ArrayList<>());
                        if (!letterCount.get(firstLetter).contains(baby.getName())) {
                            letterCount.get(firstLetter).add(baby.getName());
                        }
                    }
                }
            }
        }

        // Find the letter with the most names
        char mostPopularLetter = ' ';
        int maxCount = 0;

        for (char letter : letterCount.keySet()) {
            if (letterCount.get(letter).size() > maxCount) {
                mostPopularLetter = letter;
                maxCount = letterCount.get(letter).size();
            }
        }

        // Return the most popular letter and the alphabetized list of names starting with that letter
        if (letterCount.containsKey(mostPopularLetter)) {
            ArrayList<String> names = letterCount.get(mostPopularLetter);
            names.sort(String::compareTo); // Alphabetical order
            return "Most popular letter: " + mostPopularLetter + "\nNames: " + names.toString();
        }

        return "No names found for the specified range.";
    }
}
