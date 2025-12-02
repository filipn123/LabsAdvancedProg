package lab5.Zad3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibrarySystem {
    private String name;
    private List<Book> books;
    private List<Member> members;
    
    public LibrarySystem(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }
    
    public void registerMember(String id, String fullName) {
        members.add(new Member(id, fullName));
    }
    
    public void addBook(String isbn, String title, String author, int year) {
        Book book = books.stream().filter(b -> b.getId().equals(isbn)).findFirst().orElseGet(() -> {
            Book newBook = new Book(isbn, title, author, year);
            books.add(newBook);
            return newBook;
        });
        
        book.setTotalCopies(book.getTotalCopies() + 1);
        book.setAvailableCopies(book.getAvailableCopies() + 1);
    }
    
    public void borrowBook(String memberId, String isbn) {
        Book book = books.stream().filter(b -> b.getId().equals(isbn)).findFirst().orElse(null);
        Member member = members.stream().filter(m -> m.getId().equals(memberId)).findFirst().orElse(null);
        if (book == null || member == null) {
            return;
        }
        if(book.getAvailableCopies() == 0) {
            book.addMemberToWaitingList(member);
        }
        else {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            book.setBorrowCount(book.getBorrowCount() + 1);
            member.addBook(book);
            member.setBorrowCount(member.getBorrowCount() + 1);
        }
    }
    
    public void returnBook(String memberId, String isbn) {
        Member member = members.stream().filter(m -> m.getId().equals(memberId)).findFirst().orElse(null);
        Book book = books.stream().filter(b -> b.getId().equals(isbn)).findFirst().orElse(null);
        if (book == null || member == null) {
            return;
        }
        
        member.removeBook(book);
        if(!book.getWaitingList().isEmpty()) {
            Member m = book.getWaitingList().poll();
            m.addBook(book);
            m.setBorrowCount(m.getBorrowCount() + 1);
            book.setBorrowCount(book.getBorrowCount() + 1);
        }
        else {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
        }
    }
    
    public void printBooks() {
        books.stream().sorted(Comparator.comparing(Book::getBorrowCount).reversed().thenComparing(Book::getYear)).forEach(System.out::println);
    }
    
    public void printMembers() {
        members.stream().sorted(Comparator.comparing((Member m) -> m.getBooks().size()).reversed().thenComparing(Member::getName)).forEach(System.out::println);
    }
    
    public void printBookCurrentBorrowers(String isbn) {
        Book book = books.stream().filter(s -> s.getId().equals(isbn)).findFirst().orElse(null);
        if(book == null) {
            return;
        }
        
        List<Member> borrowers = members.stream().filter(m -> m.getBooks().contains(book)).collect(Collectors.toList());
        
        StringBuilder sb = new StringBuilder();
        for(Member m : borrowers) {
            sb.append(m.getId()).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        
        System.out.println(sb.toString());
    }
    
    public void printTopAuthors() {
        Map<String, Integer> authors = books.stream().collect(Collectors.toMap(Book::getAuthor, Book::getBorrowCount, Integer::sum));
        authors.entrySet().stream().sorted(Comparator.comparingInt((Map.Entry<String, Integer> e) -> e.getValue()).reversed().thenComparing(Map.Entry::getKey)).forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));
    }
}
