package miniproject;

import edu.duke.*;


public class TestBabyNames {
    BabyNames test = new BabyNames();

    public void testTotalBirths() {
        FileResource fr = new FileResource("yob1905.csv");
        test.totalBirths(fr);
    }
    public void testGetRank() {
        String name = "Mason";
        String genderM = "M";
        String genderF = "F";
        int rank1 = test.getRank(name, genderM);
        int rank2 = test.getRank(name, genderF);
        System.out.println("Mason male: " + rank1 + " Mason female: " + rank2);
    }
    public void testGetName() {

        int rank1 = 2;
        int rank2 = 5;
        int rank3 = 1;
        int rank4 = 16;
        String genderM = "M";
        String genderF = "F";
        System.out.println(test.getName(rank1, genderM));
        System.out.println(test.getName(rank1, genderF));
        System.out.println(test.getName(rank2, genderM));
        System.out.println(test.getName(rank2, genderF));
        System.out.println(test.getName(rank3, genderM));
        System.out.println(test.getName(rank3, genderF));
        System.out.println(test.getName(rank4, genderM));
        System.out.println(test.getName(rank4, genderF));
    }
    public void testYearOfHighestRank() {
        String name = "Mason";
        String genderM = "M";
        String genderF = "F";
        System.out.println(test.yearOfHighestRank(name, genderM));
        System.out.println(test.yearOfHighestRank(name, genderF));
    }
    public void testGetAverageRank() {
        String name1 = "Mason";
        String genderM = "M";
        String name2 = "Jacob";
        String genderF = "F";
        System.out.println(test.getAverageRank(name1, genderM));
        System.out.println(test.getAverageRank(name2, genderM));
        System.out.println(test.getAverageRank(name1, genderF));
    }
    public void testTotalBirthsRankedHigher() {
        String name = "Ethan";
        String gender = "M";
        System.out.println(test.getTotalBirthsRankedHigher(name, gender));
    }

    public static void main(String[] args) {
        TestBabyNames test = new TestBabyNames();
        test.testTotalBirths();
        test.testGetRank();
        test.testGetName();
        test.testYearOfHighestRank();
        test.testGetAverageRank();
        test.testTotalBirthsRankedHigher();
    }
}
