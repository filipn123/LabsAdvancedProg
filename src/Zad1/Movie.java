package Zad1;

class Movie {
    private String title;
    private String genre;
    private int year;
    private double avgRating;
    
    public Movie(String title, String genre, int year, double avgRating) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.avgRating = avgRating;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public int getYear() {
        return year;
    }
    
    public double getAvgRating() {
        return avgRating;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
    
    @Override
    public String toString() {
        return String.format("%s, %s, %d, %.2f", title, genre, year, avgRating);
    }
}
