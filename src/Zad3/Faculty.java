package Zad3;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private final String shortName;
    private final List<String> appropriateSubjects;
    private final List<StudyProgramme> studyProgrammes;
    
    public Faculty(String shortName) {
        this.shortName = shortName;
        this.appropriateSubjects = new ArrayList<>();
        this.studyProgrammes = new ArrayList<>();
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
