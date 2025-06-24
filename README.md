# 👶 Baby Names Project (Java File Processing & Analysis)

This individual project for Elon University's Software Design course involves reading and analyzing U.S. Social Security baby name data using Java. The project progresses in stages to practice file I/O, data processing, clean coding principles, and functional extension.

---

## 📁 Project Overview

The program reads data files containing baby name statistics from multiple years and allows the user to query popular names by gender, year, rank, and trends. It includes robust error handling, clean object-oriented design, and additional analytical functionality for extended use cases.

---

## 📚 Features by Phase

### ✅ Part 1: Basic Query System
- Read baby name data from text files (one file per year).
- Provide an interactive menu with the following options:
  - `P`: Show most popular names for a given year and gender.
  - `R`: Show the rank of a given name, gender, and year.
  - `Y`: Find the year a name/gender combo was most popular (by frequency).
  - `Q`: Quit the program.
- Handle ranking ties correctly.
- Efficiently read and store data only once at startup.

---

### 🧼 Part 2: Code Cleanup and Robustness
- Applied **clean code principles**:
  - Small, well-named methods with single responsibilities
  - Eliminated duplicated logic
  - Used named constants instead of stray literals
  - Simplified main method
  - Added comments where needed
- Enhanced robustness:
  - Gracefully handle:
    - Missing or invalid data files
    - Non-existent years
    - Names with inconsistent casing
    - Invalid gender input (not 'M' or 'F')

---

### 📈 Part 3: Extended Functionality
Added new **non-UI analytical methods** for testing and data exploration:

1. **getAllRankings(name, gender)**  
   → Returns a list of rankings for a given name/gender across all years.

2. **getRankingsInRange(name, gender, startYear, endYear)**  
   → Returns a list of rankings within a specified year range.

3. **getNameWithSameRank(name, gender, referenceYear)**  
   → Finds the name/gender in the most recent year with the same rank the input had in the reference year.

4. **getAverageRank(name, gender, startYear, endYear)**  
   → Computes average rank across a year range.

5. **getMostPopularLetter(gender, startYear, endYear)**  
   → Returns the most common first letter for names of that gender during the range, and all matching names.

Each method was verified through **unit tests** using a custom small test dataset.

---

## 🛠️ Technologies Used

- Java 17+
- Eclipse IDE
- HashMaps, Lists, and file I/O
- JUnit (for testing)
- Clean Code practices and design principles

---

## 👨‍💻 Author
**Anthony Mozloom**
