package utils;

import java.util.function.Function;
import java.io.File;
import java.util.Scanner;

/**
 * This class contains static methods for automatically running the testcases in
 * a CCC level.
 * 
 * Usage:
 * - Put all testcases for each level in a folder, for ex.:
 * the 'level1' folder has 'level1_1.in', 'level1_2.in', etc.
 * - Each level has its own folder
 * - If needed, configure the file name format by changing, for ex.:
 * {@code CodeRunner.formatIn = "level{level}-{case}.in"}
 * - Write a method with a Scanner parameter that returns a String
 * - Call one of the methods: runExample, runCase (for a single testcase) or
 * runAllCases
 *
 * An example is n
 *
 *
 * @author Simon Gao
 * @version 2024-10-09
 */
public class CodeRunner {
    // file name formats
    public static String formatIn = "level{level}_{case}.in";
    public static String formatOut = "level{level}_{case}.out";
    public static String formatExampleIn = "level{level}_example.in";
    public static String formatExampleOut = "level{level}_example2.out";
    public static String formatLevelFolder = "level{level}";

    public static boolean printOutput = true;

    /**
     * Runs a method for a single file in a coding contest level.
     * The input filenames have to be called levelx_y.in where x is the level and y
     * is the testcase
     * Usage: {@code CodeRunner.runCase("path", 1, 1, Simon::level1);}
     * 
     * @param path     the path in which the folder with the level files is located
     *                 in.
     *                 The folder has to be named levelx where x is the level.
     * @param level    the level of the files. Enter 1 for the first level.
     * @param testcase the file that should be run.
     * @param method   A method with a <b>Scanner parameter from which the input
     *                 lines can be read</b>,
     *                 which returns the output to write
     *                 This method will be called with the correct filenames for a
     *                 single testfile.
     *                 This method is only responsible for returning the correct
     *                 output with the input lines as parameters.
     */
    public static void runCase(String path, int level, int testcase, Function<Scanner, String> method) {
        String filenameIn = createFilename(formatIn, path, level, testcase);
        String filenameOut = createFilename(formatOut, path, level, testcase);

        System.out.println("Running case: level" + level + "-" + testcase);

        CodeRunner.executeCode(filenameIn, filenameOut, method);
    }

    /**
     * Runs a method for all files (+ example) in a coding contest level.
     * The input filenames have to be called levelx_y.in where x is the level and y
     * is the testcase
     * Usage: {@code CodeRunner.runAllCases("path", 1, 5, Simon::level1);}
     *
     * @param path   the path in which the folder with the level files is located
     *               in.
     *               The folder has to be named levelx where x is the level.
     * @param level  the level of the files. Enter 1 for the first level.
     * @param cases  the amount of cases. Enter 3 for testing case1, case2, case3.
     * @param method A method with a <b>Scanner parameter from which the input lines
     *               can be read</b>,
     *               which returns the output to write
     *               This method will be called with the correct filenames for all
     *               testfiles.
     *               This method is only responsible for returning the correct
     *               output with the input lines as parameters.
     */
    public static void runAllCases(String path, int level, int cases, Function<Scanner, String> method) {
        runExample(path, level, method);
        for (int i = 1; i <= cases; i++) {
            runCase(path, level, i, method);
        }
    }

    /**
     * Runs a method for the example file in a coding contest level.
     * The input filename have to be called levelx_example.in where x is the level.
     *
     * @param path   the path in which the folder with the level files is located
     *               in.
     *               The folder has to be named levelx where x is the level.
     * @param level  the level of the files. Enter 1 for the first level.
     * @param method A method with a String[] parameter for the input lines which
     *               returns the output to write
     *               This method will be called with the correct filenames for all
     *               testfiles.
     *               This method is only responsible for returning the correct
     *               output with the input lines as parameters.
     */
    public static void runExample(String path, int level, Function<Scanner, String> method) {
        String filenameIn = createFilename(formatExampleIn, path, level, 0);
        String filenameOut = createFilename(formatExampleOut, path, level, 0);

        System.out.println("Running example for level" + level);

        CodeRunner.executeCode(filenameIn, filenameOut, method);
    }

    private static String createFilename(String format, String path, int level, int testcase) {
        String filename = format
                .replace("{level}", Integer.toString(level))
                .replace("{case}", Integer.toString(testcase));
        String folderPath = path + "/" +
                formatLevelFolder
                        .replace("{level}", Integer.toString(level));
        return folderPath + "/" + filename;
    }

    private static void executeCode(String filenameIn, String filenameOut, Function<Scanner, String> method) {
        try (
                Scanner sc = new Scanner(new File(filenameIn));) {
            String textToWrite = method.apply(sc);

            if (CodeRunner.printOutput) {
                System.out.println("Writing text to " + filenameOut + ": ");
                System.out.println(textToWrite);
            }

            ReaderWriter.save(textToWrite, filenameOut);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
