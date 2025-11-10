package Zad3;

import java.util.ArrayList;
import java.util.List;

public class Applicant {
    private int id;
    private String name;
    private double gpa;
    private List<SubjectWithGrade> subjectsWithGrade;
    private StudyProgramme studyProgramme;
    private double score;
    
    public Applicant(double gpa, String name, int id, StudyProgramme studyProgramme) {
        this.gpa = gpa;
        this.name = name;
        this.id = id;
        this.studyProgramme = studyProgramme;
        this.subjectsWithGrade = new ArrayList<>();
    }
    
    public void addSubjectAndGrade(String subject, int grade) {
        subjectsWithGrade.add(new SubjectWithGrade(subject, grade));
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s, GPA: %.1f - %s", id, name, gpa, score);
    }

    public double calculatePoints() {
        double totalPoints = 0;
        totalPoints += gpa * 12;
        for (SubjectWithGrade subjectWithGrade : subjectsWithGrade) {
            if (studyProgramme.getFaculty().getAppropriateSubjects().contains(subjectWithGrade.getSubject())) {
                totalPoints += subjectWithGrade.getGrade() * 2;
            }
            else {
                totalPoints += subjectWithGrade.getGrade() * 1.2;
            }
        }
        score = totalPoints;
        return totalPoints;
    }
}
