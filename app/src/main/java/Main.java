import java.util.Scanner;

import utils.CodeRunner;

public class Main {

    public static String level1(Scanner sc) {
        String line = sc.nextLine();
        return line.toUpperCase();
    }

    public static void main(String[] args) {
        // CodeRunner.formatIn = "level{level}_{case}.in";
        // CodeRunner.formatOut = "level{level}_{case}.out";
        // CodeRunner.formatExampleIn = "level{level}_example.in";
        // CodeRunner.formatExampleOut = "level{level}_example2.out";
        // CodeRunner.formatLevelFolder = "level{level}";

        CodeRunner.runCase("../files", 1, 1, Main::level1);
        CodeRunner.runAllCases("../files", 1, 3, Main::level1);
    }
}
