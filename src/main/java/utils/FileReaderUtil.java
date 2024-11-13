package main.java.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {
    public static List<String> readCarRegistrations(String filename) {
        List<String> registrations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                registrations.addAll(extractPattern(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registrations;
    }

    private static List<String> extractPattern(String line) {
        List<String> matches = new ArrayList<>();
        String regex = "[A-Z]{2}[0-9]{2} [A-Z]{3}"; 
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            matches.add(matcher.group().replace(" ", ""));
        }
        return matches;
    }

    public static List<String[]> readExpectedResults(String filename) {
        List<String[]> results = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.startsWith("VARIANT_REG")) {
                    results.add(line.split(","));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}