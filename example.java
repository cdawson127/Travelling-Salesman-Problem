import java.util.ArrayList;

public class Path {

    private static String[] testNames = {"A", "B", "C", "D", "E"};
    private static int [][]  testDistances =
            {{0, 94, 76, 141, 91, 60,},
                    {94, 0, 156, 231, 64, 93},
                    {76, 156, 0, 80, 167, 133},
                    {141, 231, 80, 0, 229, 185},
                    {91, 64, 167, 229, 0, 49},
                    {60, 93, 133, 185, 49, 0}};



    private String route = "";
    private int distance =0;


    //Constructor to create random path
    public Path() {
        initializePath();
        setDistance();
    }

    public Path(String path, int fitness) {
        route = path;
        distance = fitness;
    }

    public static void main(String[] args) {
       // System.out.println(initializePath(testNames));

    }

    public void initializePath() {

            String entity = "X";
            while (entity.length() != testNames.length + 1) {

                int randInt = (int) (Math.random() * testNames.length);
                if (!entity.contains(testNames[randInt])) {
                    entity += testNames[randInt];
                }
            }
            entity += "X";
            route= entity;

    }

    public int getDistance() {
     return distance;
    }

    public void setDistance() {

        for (int i = 0; i < route.length() -1; i++) {

            distance += testDistances[getNum(route.charAt(i))] [getNum(route.charAt(i+1))];

        }
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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }


    @Override
    public String toString() {
        return "Path{" +
                "route='" + route + '\'' +
                ", distance=" + distance +
                '}';
    }
}
