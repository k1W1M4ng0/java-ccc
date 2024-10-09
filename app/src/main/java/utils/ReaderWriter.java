package utils;

import java.io.*;

/**
 * Diese Klasse beinhaltet Methoden für das einlesen und ausgeben von Dateien
 * @author Markus Rafeiner, Simon Gao
 * @version 2023-04-02
 */
public class ReaderWriter {
    /**
     * Es wird ein Text in eine Datei gespeichert.
     * Dabei wird der Inhalt der Datei gelöscht und neu überschrieben.
     * @param text Der Text der in die Datei geschrieben werden soll
     * @param path Der Pfad der Datei
     */
    public static void save(String text, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(text);
        } catch (IOException e) {
            System.err.println("Es ist ein Fehler beim Speichern aufgetreten");
            System.err.println(e);
            e.printStackTrace();
            throw new RuntimeException("IO Fehler");
        }
    }

    /**
     * Es wird ein Text in eine Datei gespeichert.
     * Dabei wird der Inhalt der Datei gelöscht und neu überschrieben.
     * Der zu schreibende Text wird getrimmt.
     * @param text Der Text der in die Datei geschrieben werden soll
     * @param path Der Pfad der Datei
     */
    public static void saveTrimmed(String text, String path) {
        save(text.trim(), path);
    }

    /**
     * Es wird ein String Array mit allen Zeilen einer Datei erstellt.
     * Pro Zeile der Datei hat das Array ein Element, welches mit dem Inhalt der Zeile gefüllt wird.
     * @param path Der Pfad der Datei
     * @return Ein String Array mit allen Zeilen der Datei
     */
    public static String[] loadLines(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // .lines gibt einen String stream zurück, mit der .toArray methode kann man den in String Arrays umwandeln
            return br.lines().toArray(String[]::new);
        } catch (IOException e) {
            System.err.println("Es ist ein Fehler beim Laden aufgetreten");
            System.err.println(e);
            e.printStackTrace();
            throw new RuntimeException("IO Fehler");
        }
    }

    /**
     * Es wird ein 2d char Array mit allen Zeichen und deren Zeichen einer Datei erstellt.
     * Pro Zeile der Datei hat das Array ein Element, welches mit einem char Array gefüllt wird.
     * Dieses Char Array hat die Länge der Zeile und beinhaltet alle Zeichen der Zeile.
     * @param path Der Pfad der Datei
     * @return Ein 2d char Array mit allen Zeichen der Datei.
     */
    public static char[][] loadChars(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String[] lines = br.lines().toArray(String[]::new);
            char[][] result = new char[lines.length][];
            for (int i = 0; i < lines.length; i++) {
                result[i] = lines[i].toCharArray();
            }
            return result;
        } catch (IOException e) {
            System.err.println("Es ist ein Fehler beim Laden aufgetreten");
            System.err.println(e);
            e.printStackTrace();
            throw new RuntimeException("IO Fehler");
        }
    }
}
