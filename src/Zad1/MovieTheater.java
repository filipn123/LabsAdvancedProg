package Zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

class MovieTheater {
    private ArrayList<Movie> movies;
    
    public MovieTheater() {
        this.movies = new ArrayList<>();
    }
    
    public void readMovies(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String title = br.readLine();
            String genre = br.readLine();
            int year = Integer.parseInt(br.readLine());
            String[] ratings = br.readLine().split(" ");
            double avg = 0;
            for (String r : ratings) {
                avg += Integer.parseInt(r);
            }
            avg /= ratings.length;
            movies.add(new Movie(title, genre, year, avg));
        }
    }
    
    public void printByGenreAndTitle() {
        movies.stream().sorted(Comparator.comparing(Movie::getGenre).thenComparing(Movie::getTitle)).forEach(System.out::println);
    }
    
    public void printByYearAndTitle() {
        movies.stream().sorted(Comparator.comparing(Movie::getYear).thenComparing(Movie::getTitle)).forEach(System.out::println);
    }
    
    public void printByRatingAndTitle() {
        movies.stream().sorted(Comparator.comparing(Movie::getAvgRating).reversed().thenComparing(Movie::getTitle)).forEach(System.out::println);
    }
}
