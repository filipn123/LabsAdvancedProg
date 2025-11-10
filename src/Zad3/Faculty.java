package Zad3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Faculty {
    private final String shortName;
    private final List<String> appropriateSubjects;
    private final List<StudyProgramme> studyProgrammes;
    
    public Faculty(String shortName) {
        this.shortName = shortName;
        this.appropriateSubjects = new ArrayList<>();
        this.studyProgrammes = new ArrayList<>();
    }

    @Override
    public String toString() {
        List<StudyProgramme> sortedProgrammes = studyProgrammes.stream().sorted(Comparator.comparingDouble(Faculty::calculateProgrammePercentage).reversed().thenComparing(StudyProgramme::getName)).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        sb.append("Faculty: ").append(shortName).append("\n");
        sb.append("Subjects: ").append(appropriateSubjects).append("\n");
        sb.append("Study Programmes:\n");
        sortedProgrammes.forEach(sp -> sb.append(sp).append("\n"));
        return sb.toString().trim();
    }

    private static double calculateProgrammePercentage(StudyProgramme sp) {
        double enrolled = sp.getEnrolledInPrivateQuota() + sp.getEnrolledInPublicQuota();
        double total = sp.getNumPrivateQuota() + sp.getNumPublicQuota();
        if(total == 0) {
            return 0;
        }
        else {
            return (enrolled / total) * 100;
        }
    }

    public void addStudyProgramme(StudyProgramme studyProgramme) {
        studyProgrammes.add(studyProgramme);
    }
    
    public void addSubject(String subject) {
        appropriateSubjects.add(subject);
    }
    
    public List<String> getAppropriateSubjects() {
        return appropriateSubjects;
    }
    
    public List<StudyProgramme> getStudyProgrammes() {
        return studyProgrammes;
    }
}
