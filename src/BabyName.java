//Anthony Mozloom
// Class that represents a BabyName object
// This object holds all data for one baby name in a given year
// Includes name, gender, frequency, and rank

public class BabyName {
    private String name;
    private String gender;
    private int frequency;
    private int rank;

    // Constructor to initialize BabyName object
    public BabyName(String name, String gender, int frequency, int rank) {
        this.name = name;
        this.gender = gender;
        this.frequency = frequency;
        this.rank = rank;
    }

    // Getter methods for accessing the private fields
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return name + " " + gender + " " + frequency + " " + rank;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BabyName) {
            BabyName other = (BabyName) obj; // Cast obj to BabyName
            // Compare all fields using equals for objects and == for primitives
            return this.name.equals(other.name) &&
                   this.gender.equals(other.gender) &&
                   this.frequency == other.frequency &&
                   this.rank == other.rank;
        }
        // If the object is not a BabyName or is null, return false
        return false;
    }
    
}
