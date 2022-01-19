import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;


public class travellingSalesmanProblem {
    private static ArrayList <Path> oldPopulation = new ArrayList<Path>();
    private static ArrayList <Path> newPopulation = new ArrayList<Path>();
    private static int generationCount = 250;
    Path path = new Path();
    private static int[][] testDistances = {
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

    public static void main(String[] args) {
        oldPopulation = initializePopulation();
        sortPaths(oldPopulation);
        System.out.println("First population is " +oldPopulation);
        for (int i = 0; i < 10000; i++) {
            newPopulation = getNewPopulation(oldPopulation);
            System.out.println("New pop is " + newPopulation);
            oldPopulation = newPopulation;
        }
        System.out.println(getFittest(newPopulation));
        sortPaths(newPopulation);
        System.out.println("Final population is " + newPopulation);

    }



    public static ArrayList<Path> initializePopulation() {
        ArrayList<Path> initialPop = new ArrayList<Path>();

        for (int i = 0; i < generationCount; i++) {
            initialPop.add(new Path());
        }
        return initialPop;
    }


    public static Path getFitter (Path pathOne, Path pathTwo) {
        if (pathOne.getDistance() > pathTwo.getDistance()) {
            return pathOne;
        }
        return pathTwo;
    }

    public static Path getFittest (ArrayList<Path> pathList) {
        int fitness = 5000;
        Path fittest = pathList.get(0);
        for (int i = 0; i < pathList.size(); i++) {
            if (pathList.get(i).getDistance() < fitness) {
                fitness = pathList.get(i).getDistance();
                fittest = pathList.get(i);
            }
        }
        return fittest;
    }

    public static void sortPaths (ArrayList<Path> pathList) {

        int n = pathList.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (pathList.get(j).getDistance() > pathList.get(j+1).getDistance())
                {
                    // swap arr[j+1] and arr[j]
                    Collections.swap(pathList, j, j+1);
                }
    }

    public static Path tournamentSelection(ArrayList<Path> pathList) {
        int fitness = 5000;
        Path fittest = pathList.get(0);
        for (int i = 0; i < 64; i++) {
            int randNum = (int) (Math.random()) * pathList.size();
            if (pathList.get(randNum).getDistance() < fitness) {
                fitness = pathList.get(randNum).getDistance();
            }
        }
        return fittest;
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

    public static ArrayList<Path> getNewPopulation(ArrayList<Path> pathList) {
        double randNum = ((double) (Math.random() * 20)+ 50)/100;
//        System.out.println(randNum);
        double remainder = 1-randNum;
        ArrayList<Path> newPop = new ArrayList<Path>();
        int firstCount = 0;
        int addCount = 0;
        while (firstCount < generationCount * (remainder*0.75)) {
            sortPaths(pathList);
            if (!newPop.contains(pathList.get(addCount))) {
                newPop.add(pathList.get(addCount));
                firstCount++;
                addCount = firstCount;
            }
            else {
                addCount ++;
            }
        }
        String newPathString = "";
//        String newNewPathtring = "";
        int fit = 0;
        int count = 0;
        while (count < generationCount * randNum) {

            newPathString = crossover(tournamentSelection(oldPopulation).getRoute(), tournamentSelection(oldPopulation).getRoute());
//             newNewPathtring = crossover(tournamentSelection(oldPopulation).getRoute(), tournamentSelection(oldPopulation).getRoute());
//            System.out.println(newNewPathtring);
            fit = getDistance(newPathString);
            if (!pathInList(pathList, newPathString)) {
                newPop.add(new Path(newPathString, fit));
                count ++;
            }
        }
        int countTwo = 0;
       while (countTwo < generationCount * (remainder*0.25)) {
           String mutatedPath = mutatePath(newPathString);
            Path newPath = new Path(mutatedPath, getDistance(mutatedPath));

            if (!pathList.contains(newPath)) {
                newPop.add(newPath);
                countTwo++;
            }


        }



        return newPop;
    }
    public static boolean pathInList(ArrayList<Path> pathList, String path) {
        for (int i =0; i< pathList.size(); i++) {
            if (pathList.get(i).getRoute().equals(path))
                return true;
        }
        return false;
    }
    public static int getDistance(String route) {
        int distance = 0;
        for (int i = 0; i < route.length() -1; i++) {

            distance += testDistances[getNum(route.charAt(i))] [getNum(route.charAt(i+1))];

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

    public static String mutatePath(String path) {
        String Newpath = "";
        int randInt = (int) Math.random() * path.length();
        int randIntTwo = (int) Math.random() * path.length();
        ArrayList<Character> characterList = new ArrayList<Character>();
        for (int i = 0; i < path.length(); i++) {
            characterList.add(path.charAt(i));
            char temp = characterList.get(randInt);
            characterList.set(randInt,characterList.get(randIntTwo));
            characterList.set(randIntTwo, temp);
        }
        for (int i = 0; i < characterList.size(); i++) {
            Newpath += characterList.get(i);
        }
        return Newpath;

    }


}
