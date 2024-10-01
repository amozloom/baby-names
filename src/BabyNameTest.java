import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

public class BabyNameTest {
	
	private HashMap<Integer, ArrayList<BabyName>> createTestBabyNamesData() {
        HashMap<Integer, ArrayList<BabyName>> babyNamesData = new HashMap<>();

        // Data for the year 1880
        ArrayList<BabyName> year1880 = new ArrayList<>();
        year1880.add(new BabyName("Mary", "F", 9843, 1));
        year1880.add(new BabyName("John", "M", 9968, 1));
        year1880.add(new BabyName("Mark", "M", 6743, 2));
        babyNamesData.put(1880, year1880);

        // Data for the year 1890
        ArrayList<BabyName> year1890 = new ArrayList<>();
        year1890.add(new BabyName("Emma", "F", 8231, 1));
        year1890.add(new BabyName("John", "M", 9076, 1));
        year1890.add(new BabyName("James", "M", 6122, 2));
        babyNamesData.put(1890, year1890);

        // Data for the year 1900
        ArrayList<BabyName> year1900 = new ArrayList<>();
        year1900.add(new BabyName("Anna", "F", 7510, 1));
        year1900.add(new BabyName("William", "M", 8954, 1));
        year1900.add(new BabyName("Robert", "M", 5431, 2));
        babyNamesData.put(1900, year1900);

        return babyNamesData;
    }

    @Test
    public void testGetAllRankings() {
        HashMap<Integer, ArrayList<BabyName>> babyNamesData = createTestBabyNamesData();

        BabyNameDataReader reader = new BabyNameDataReader();
        ArrayList<Integer> rankings = reader.getAllRankings("John", "M", babyNamesData);

        assertNotNull(rankings);
        assertEquals(2, rankings.size()); // John has rankings for 1880 and 1890
        assertEquals(1, (int) rankings.get(0)); // Ranked 1 in 1880
        assertEquals(1, (int) rankings.get(1)); // Ranked 1 in 1890
    }
    
    @Test
    public void testGetRankingsInRange() {
        BabyNameDataReader reader = new BabyNameDataReader();
        HashMap<Integer, ArrayList<BabyName>> babyNamesData = createTestBabyNamesData();

        ArrayList<Integer> rankings = reader.getRankingsInRange("John", "M", 1880, 2000, babyNamesData);
        assertEquals(2, rankings.size()); //John has rankings for 1880 and 1890 
        assertEquals(1, (int) rankings.get(1)); // Ranked 1 in 1890
    }

    @Test
    public void testGetSameRankName() {
        BabyNameDataReader reader = new BabyNameDataReader();
        HashMap<Integer, ArrayList<BabyName>> babyNamesData = reader.loadBabyNamesData();

        BabyName result = reader.getSameRankName("John", "M", 1880, babyNamesData);
        assertNotNull(result);
        assertEquals("Liam", result.getName());  // Liam has the same rank as John in 2023
        //System.out.println(result);
    }

    @Test
    public void testGetAverageRank() {
        BabyNameDataReader reader = new BabyNameDataReader();
        HashMap<Integer, ArrayList<BabyName>> babyNamesData = createTestBabyNamesData();

        double averageRank = reader.getAverageRank("John", "M", 1880, 1900, babyNamesData);
        assertEquals(1.0, averageRank);  // John was ranked 1 across these years
    }
    
    @Test
    public void testGetMostPopularStartingLetter() {
        BabyNameDataReader reader = new BabyNameDataReader();
        HashMap<Integer, ArrayList<BabyName>> babyNamesData = createTestBabyNamesData();

        String result = reader.getMostPopularStartingLetter("M", 1880, 1900, babyNamesData);
        assertTrue(result.contains("Most popular letter: J"));  // 'J' is the most popular starting letter
    }
}