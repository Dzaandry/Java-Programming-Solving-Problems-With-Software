package miniproject;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total boys = " + totalBoys);
        System.out.println("Total girls = " + totalGirls);
    }
    public void totalBirths() {
        FileResource fr = new FileResource();
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int totalGirlsNames = 0;
        int totalBoysNames = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoysNames++;
            }
            else {
                totalGirls += numBorn;
                totalGirlsNames++;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total boys = " + totalBoys);
        System.out.println("Total girls = " + totalGirls);
        System.out.println("Total girls' names = " + totalGirlsNames);
        System.out.println("Total boys' names = " + totalBoysNames);
    }
    public int getRank(String name, String gender) {
        FileResource fr = new FileResource();
        int rank = -1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (name.equals(rec.get(0)) && gender.equals(rec.get(1))) {
                rank = 1;
                break;
            }
        }
        if (rank == -1) {
            return rank;
        }
        for (CSVRecord rec : fr.getCSVParser(false)) {
            boolean isNameFound = name.equals(rec.get(0));
            boolean isGenderCorrect = gender.equals(rec.get(1));
            if (isNameFound && isGenderCorrect) {
                break;
            }
            else if (isNameFound == false && isGenderCorrect) {
                rank++;
            }
        }
        return rank;
    }
    public int anotherGetRank(String name, String gender, FileResource fr) {
        int rank = -1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (name.equals(rec.get(0)) && gender.equals(rec.get(1))) {
                rank = 1;
                break;
            }
        }
        if (rank == -1) {
            return rank;
        }
        for (CSVRecord rec : fr.getCSVParser(false)) {
            boolean isNameFound = name.equals(rec.get(0));
            boolean isGenderCorrect = gender.equals(rec.get(1));
            if (isNameFound && isGenderCorrect) {
                break;
            }
            else if (isNameFound == false && isGenderCorrect) {
                rank++;
            }
        }
        return rank;
    }
    public String getName(int rank, String gender) {
        FileResource fr = new FileResource();
        int getRank = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            boolean isGenderCorrect = gender.equals(rec.get(1));
            if (getRank < rank && isGenderCorrect) {
                getRank++;
            }
            else if (getRank == rank && isGenderCorrect) {
                return rec.get(0);
            }
        }
        return "NO NAME";
    }
    public void whatIsNameInYear(String name, String gender, int year, int newYear) {
        int rank = getRank(name, gender);
        String newName = "";
        if (rank == -1) {
            System.out.println("No babies were born with this name and gender");
        }
        else {
            newName = getName(rank, gender);
        }
        if (newName.equals("NO NAME")) {
            System.out.println("this rank doesn't exist in the newly specified year");
        }
        else {
            System.out.println(name + " born in " + year + " would be " + newName + " born in " + newYear);
        }
    }
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rank = -1;
        int yearOfHighestRank = -1;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currentRank = anotherGetRank(name, gender, fr);
            if (rank == -1 && currentRank != -1) {
                rank = currentRank;
                yearOfHighestRank = Integer.parseInt(f.getName().substring(3, 7));
            }
            else if (currentRank < rank) {
                rank = currentRank;
                yearOfHighestRank = Integer.parseInt(f.getName().substring(3, 7));
            }
        }
        return yearOfHighestRank;
    }
    public Double getAverageRank(String name, String gender) {
        int numOfAppearances = 0;
        Double averageRank = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currentRank = anotherGetRank(name, gender, fr);
            if (currentRank != -1) {
                averageRank += currentRank;
                numOfAppearances++;
            }
        }
        if (averageRank == 0.0) {
            return -1.0;
        }
        return averageRank / numOfAppearances;
    }
    public int getTotalBirthsRankedHigher(String name, String gender) {
        FileResource fr = new FileResource();
        int total = 0;
        int ranksHigher = 1;
        int givenRank = anotherGetRank(name, gender, fr);
        if (givenRank == -1) {
            System.out.println("no such name and/or gender in the given file");
            return -1;
        }
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (ranksHigher == givenRank) {
                break;
            }
            else if (gender.equals(rec.get(1))) {
                total += Integer.parseInt(rec.get(2));
                ranksHigher++;
            }
        }
        return total;
    }
}
