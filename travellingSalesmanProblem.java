import java.util.ArrayList;
import java.util.*;

public class travellingSalesmanProblem {
   private static Hashtable<String, Integer> oldPopulation = new Hashtable<String, Integer>();
    private static Hashtable<String, Integer> newPopulation = new Hashtable<String, Integer>();

    private static int generationCount = 10;


        String[] names = {"X", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
        int[][] distances = {
                {0, 94, 76, 141, 91, 60, 120, 145, 91, 74, 90, 55, 145, 108, 41, 49, 33, 151, 69, 111, 24},
                {94, 0, 156, 231, 64, 93, 108, 68, 37, 150, 130, 57, 233, 26, 62, 140, 61, 229, 120, 57, 109},
                {76, 156, 0, 80, 167, 133, 124, 216, 137, 114, 154, 100, 141, 161, 116, 37, 100, 169, 49, 185, 84},
                {141, 231, 80, 0, 229, 185, 201, 286, 216, 139, 192, 178, 113, 239, 182, 92, 171, 155, 128, 251, 137},
                {91, 64, 167, 229, 0, 49, 163, 65, 96, 114, 76, 93, 200, 91, 51, 139, 72, 185, 148, 26, 92},
                {60, 93, 133, 185, 49, 0, 165, 115, 112, 65, 39, 91, 151, 117, 39, 99, 61, 139, 128, 75, 49},
                {120, 108, 124, 201, 163, 165, 0, 173, 71, 194, 203, 74, 254, 90, 127, 136, 104, 269, 75, 163, 144},
                {145, 68, 216, 286, 65, 115, 173, 0, 103, 179, 139, 123, 265, 83, 104, 194, 116, 250, 186, 39, 152},
                {91, 37, 137, 216, 96, 112, 71, 103, 0, 160, 151, 39, 236, 25, 75, 130, 61, 239, 95, 93, 112},
                {74, 150, 114, 139, 114, 65, 194, 179, 160, 0, 54, 127, 86, 171, 89, 77, 99, 80, 134, 140, 50},
                {90, 130, 154, 192, 76, 39, 203, 139, 151, 54, 0, 129, 133, 155, 78, 117, 99, 111, 159, 101, 71},
                {55, 57, 100, 178, 93, 91, 74, 123, 39, 127, 129, 0, 199, 61, 53, 91, 30, 206, 63, 101, 78},
                {145, 233, 141, 113, 200, 151, 254, 265, 236, 86, 133, 199, 0, 251, 171, 118, 176, 46, 182, 226, 125},
                {108, 26, 161, 239, 91, 117, 90, 83, 25, 171, 155, 61, 251, 0, 83, 151, 75, 251, 119, 81, 127},
                {41, 62, 116, 182, 51, 39, 127, 104, 75, 89, 78, 53, 171, 83, 0, 90, 24, 168, 99, 69, 49},
                {49, 140, 37, 92, 139, 99, 136, 194, 130, 77, 117, 91, 118, 151, 90, 0, 80, 139, 65, 159, 50},
                {33, 61, 100, 171, 72, 61, 104, 116, 61, 99, 99, 30, 176, 75, 24, 80, 0, 179, 76, 86, 52},
                {151, 229, 169, 155, 185, 139, 269, 250, 239, 80, 111, 206, 46, 251, 168, 139, 179, 0, 202, 211, 128},
                {69, 120, 49, 128, 148, 128, 75, 186, 95, 134, 159, 63, 182, 119, 99, 65, 76, 202, 0, 161, 90},
                {111, 57, 185, 251, 26, 75, 163, 39, 93, 140, 101, 101, 226, 81, 69, 159, 86, 211, 161, 0, 115},
                {24, 109, 84, 137, 92, 49, 144, 152, 112, 50, 71, 78, 125, 127, 49, 50, 52, 128, 90, 115, 0}};

   private static String[] testNames = {"A", "B", "C", "D", "E"};
   private static int [][]  testDistances =
                              {{0, 94, 76, 141, 91, 60,},
                              {94, 0, 156, 231, 64, 93},
                              {76, 156, 0, 80, 167, 133},
                              {141, 231, 80, 0, 229, 185},
                              {91, 64, 167, 229, 0, 49},
                              {60, 93, 133, 185, 49, 0}};

   private static ArrayList<String> routes = initializePopulation(testNames);


    public static void main(String[] args) {



        addToDictionary(routes);
        System.out.println(oldPopulation);
        sortPopulation();

//        for (int i = 0; i < routes.size(); i++) {
//            System.out.println(routes.get(i));
//            System.out.println(getDistance(routes.get(i), testDistances));
//        }
//        System.out.println("----------------------");
//        System.out.println("New population:");
//        for (int i = 0; i < routes.size(); i++) {
//            int parentOne = (int) (Math.random() * routes.size());
//            int parentTwo = (int) (Math.random() * routes.size());
//            String offSpring = crossover(routes.get(parentOne), routes.get(parentTwo));
//            System.out.println(offSpring);
//            System.out.println(getDistance(offSpring, testDistances));
//        }

    }

    public static void sortPopulation() {
    }


    public static void addToDictionary(ArrayList<String> pop) {
        for (int i = 0; i < pop.size(); i++) {
            oldPopulation.put(pop.get(i), getDistance(pop.get(i), testDistances));

        }
    }


    public static ArrayList<String> initializePopulation(String [] placeNames) {
        ArrayList<String> initialPop = new ArrayList<String>();

        for (int i = 0; i < generationCount; i++) {
            String entity = "X";
            while (entity.length() != placeNames.length + 1) {

                int randInt = (int) (Math.random() * placeNames.length);
                if (!entity.contains(placeNames[randInt])) {
                    entity += placeNames[randInt];
                }
            }
            entity += "X";
            initialPop.add(entity);
        }

        System.out.println("Fittest is " + getFittest(initialPop));
        return initialPop;
    }


    public static String getFittest (ArrayList<String> population) {
        int index = 0;
        for (int i = 0; i < population.size(); i++) {
            int fittest = 0;

            int fitness = getDistance(population.get(i), testDistances);
           if (fitness > fittest) {
               fittest = fitness;
               index = i;
           }
        }
        return population.get(index);
    }

    public static int getDistance(String route, int[][] distances) {
        int distance = 0;
        for (int i = 0; i < route.length() -1; i++) {

            distance += distances[getNum(route.charAt(i))] [getNum(route.charAt(i+1))];

        }

        return distance;
    }

    public static int getNum(char letter) {
        letter = Character.toLowerCase(letter);
        int num;
        if (letter == 'x')
            num = 0;
        else {
            num = ((int) letter) - 96;
        }
//        System.out.println(num);
        return num;
    }

    public static String crossover (String parentOne, String parentTwo) {
        String result = "";

        parentOne = parentOne.substring(1);
        parentOne = parentOne.substring(0, parentOne.length()-1);
        parentTwo = parentTwo.substring(1);
        parentTwo = parentTwo.substring(0, parentTwo.length()-1);

        int randIntOne = (int) (Math.random() * parentOne.length());

        String subString = parentOne.substring(randIntOne);
        result += subString;
        for (int i = 0; i < parentTwo.length(); i++) {
            int index = subString.indexOf(parentTwo.charAt(i));

            if (index == -1) {
                int randNum = (int) (Math.random() * 2);
                if (randNum == 1) {
                    result = parentTwo.charAt(i) + result;
                }
                else if (randNum == 0) {
                    result += parentTwo.charAt(i);
                }

            }
        }
        result = 'X' + result;
        result += 'X';

        return result;
    }


}



