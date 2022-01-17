import java.util.ArrayList;
import java.util.*;


public class travellingSalesmanProblem {
    private static ArrayList <Path> oldPopulation = new ArrayList<Path>();
    private static ArrayList <Path> newPopulation = new ArrayList<Path>();
    private static int generationCount = 10;
    Path path = new Path();
    private static int [][]  testDistances =
            {{0, 94, 76, 141, 91, 60,},
                    {94, 0, 156, 231, 64, 93},
                    {76, 156, 0, 80, 167, 133},
                    {141, 231, 80, 0, 229, 185},
                    {91, 64, 167, 229, 0, 49},
                    {60, 93, 133, 185, 49, 0}};

    public static void main(String[] args) {

        oldPopulation = initializePopulation();
        sortPaths(oldPopulation);
        System.out.println(oldPopulation);
        newPopulation = getNewPopulation(oldPopulation);
        System.out.println("New pop is " + newPopulation);

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
        int fitness = 1000;
        Path fittest = pathList.get(0);
        for (int i = 0; i < 4; i++) {
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
        ArrayList<Path> newPop = new ArrayList<Path>();
        newPop.add(getFittest(pathList));
        String newPathString = "";
//        String newNewPathtring = "";
        int fit = 0;
        for (int i = 0; i < generationCount-1; i++) {
             newPathString = crossover(tournamentSelection(oldPopulation).getRoute(), tournamentSelection(oldPopulation).getRoute());
//             newNewPathtring = crossover(tournamentSelection(oldPopulation).getRoute(), tournamentSelection(oldPopulation).getRoute());
//            System.out.println(newNewPathtring);
             fit = getDistance(newPathString);
             newPop.add(new Path(newPathString, fit));
        }


        return newPop;
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


}



