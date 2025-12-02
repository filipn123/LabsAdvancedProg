package lab5.Zad3;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String id;
    private String name;
    private List<Book> books;
    private int borrowCount;
    
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        books = new ArrayList<>();
        borrowCount = 0;
    }
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public void removeBook(Book book) {
        books.remove(book);
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public String getId() {
        return id;
    }
    
    public int getBorrowCount() {
        return borrowCount;
    }
    
    public String getName() {
        return name;
    }
    
    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s) - borrowed now: %s, total borrows: %s", name, id, books.size(), borrowCount);
    }
}
