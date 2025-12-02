package lab5.Zad3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Book {
    private String id;
    private String title;
    private String author;
    private int year;
    private int totalCopies;
    private int availableCopies;
    private int borrowCount;
    private Queue<Member> waitingList;
    
    public Book(String id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.borrowCount = 0;
        this.waitingList = new LinkedList<>(); {
        };
        this.totalCopies = 0;
        this.availableCopies = 0;
    }
    
    public void addMemberToWaitingList(Member member) {
        waitingList.add(member);
    }
    
    public Queue<Member> getWaitingList() {
        return waitingList;
    }
    
    public int getTotalCopies() {
        return totalCopies;
    }
    
    public int getAvailableCopies() {
        return availableCopies;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public int getYear() {
        return year;
    }
    
    public String getId() {
        return id;
    }
    
    public int getBorrowCount() {
        return borrowCount;
    }
    
    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }
    
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
    
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
    
    @Override
    public String toString() {
        return String.format("%s - " + '"' + "%s" + '"' + " by %s (%s), available: %s, total borrows: %s", id, title, author, year, availableCopies, borrowCount);
    }
}
