package Zad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class EnrollmentsIO {
    
    public static void printRanked(List<Faculty> allFaculties) {
    
    }
    
    public static void readEnrollments(List<StudyProgramme> allProgrammes, InputStream in) {
        Scanner scanner = new Scanner(in);
        String line = scanner.nextLine();
        while (!line.isEmpty()) {
            String[] parts = line.split(";");
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            double grade = Double.parseDouble(parts[2]);
            StudyProgramme programme = allProgrammes.stream().filter(s -> s.getCode().equals(parts[parts.length - 1])).findFirst().orElse(null);
            Applicant applicant = new Applicant(grade, name, id, programme);
            programme.addApplicant(applicant);
            
            for (int i = 3; i < parts.length - 1; i+=2) {
                applicant.addSubjectAndGrade(parts[i], Integer.parseInt(parts[i+1]));
            }
            line = scanner.nextLine();
        }
    }
}
