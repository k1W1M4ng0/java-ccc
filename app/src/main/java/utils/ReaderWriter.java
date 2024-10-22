package utils;

import java.io.*;

/**
 * This class contains methods for outputting into files
 * 
 * @author Markus Rafeiner, Simon Gao
 * @version 2024-10-22
 */
class ReaderWriter {
    /**
     * A text is saved to a file.
     * The content of the file is deleted and overwritten.
     * 
     * @param text The text to be written to the file
     * @param path The path of the file
     */
    static void save(String text, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(text);
        } catch (IOException e) {
            System.err.println("Error while writing.");
            System.err.println(e);
            e.printStackTrace();
            throw new RuntimeException("IO Error");
        }
    }

    /**
     * A text is saved to a file.
     * The content of the file is deleted and overwritten.
     * The text to be written is trimmed.
     * 
     * @param text The text to be written to the file
     * @param path The path of the file
     */
    static void saveTrimmed(String text, String path) {
        save(text.trim(), path);
    }
}
