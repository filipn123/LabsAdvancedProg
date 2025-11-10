package Zad3;

import java.util.*;
import java.util.stream.Collectors;

public class StudyProgramme {
    private String code;
    private String name;
    private final Faculty faculty;
    private int numPublicQuota;
    private int numPrivateQuota;
    private int enrolledInPublicQuota;
    private int enrolledInPrivateQuota;
    private final List<Applicant> applicants;
    private List<Applicant> publicQuotaApplicants = new ArrayList<>();
    private List<Applicant> privateQuotaApplicants = new ArrayList<>();
    private List<Applicant> rejectedApplicants = new ArrayList<>();
    
    public StudyProgramme(String code, String name, Faculty faculty, int numPublicQuota, int numPrivateQuota) {
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.numPublicQuota = numPublicQuota;
        this.numPrivateQuota = numPrivateQuota;
        applicants = new ArrayList<>();
    }
    
    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
    }
    
    public void calculateEnrollmentNumbers() {
        Map<Applicant, Double> applicantsScores = new HashMap<>();
        for (Applicant applicant : applicants) {
            double points = applicant.calculatePoints();
            applicantsScores.put(applicant, points);
        }
        publicQuotaApplicants = applicantsScores.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(numPublicQuota).map(Map.Entry::getKey).collect(Collectors.toList());
        privateQuotaApplicants = applicantsScores.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).map(Map.Entry::getKey).filter(a -> !publicQuotaApplicants.contains(a)).limit(numPublicQuota).collect(Collectors.toList());
        rejectedApplicants = applicantsScores.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).map(Map.Entry::getKey).filter(a -> !publicQuotaApplicants.contains(a) && !privateQuotaApplicants.contains(a)).collect(Collectors.toList());

        enrolledInPublicQuota = publicQuotaApplicants.size();
        enrolledInPrivateQuota = privateQuotaApplicants.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Public Quota:\n");
        publicQuotaApplicants.forEach(a -> sb.append(a).append("\n"));
        sb.append("Private Quota:\n");
        privateQuotaApplicants.forEach(a -> sb.append(a).append("\n"));
        sb.append("Rejected:\n");
        rejectedApplicants.forEach(a -> sb.append(a).append("\n"));
        return sb.toString();
    }

    public int getNumPublicQuota() {
        return numPublicQuota;
    }

    public int getNumPrivateQuota() {
        return numPrivateQuota;
    }

    public int getEnrolledInPublicQuota() {
        return enrolledInPublicQuota;
    }

    public int getEnrolledInPrivateQuota() {
        return enrolledInPrivateQuota;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
