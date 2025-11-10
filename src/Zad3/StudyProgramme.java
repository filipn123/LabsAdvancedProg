package Zad3;

import java.util.List;

public class StudyProgramme {
    private String code;
    private String name;
    private final Faculty faculty;
    private int numPublicQuota;
    private int numPrivateQuota;
    private int enrolledInPublicQuota;
    private int enrolledInPrivateQuota;
    private List<Applicant> applicants;
    
    public StudyProgramme(String code, String name, Faculty faculty, int numPublicQuota, int numPrivateQuota) {
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.numPublicQuota = numPublicQuota;
        this.numPrivateQuota = numPrivateQuota;
    }
    
    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
    }
    
    public void calculateEnrollmentNumbers() {
    }
    
    public Faculty getFaculty() {
        return faculty;
    }
    
    public String getCode() {
        return code;
    }
}
