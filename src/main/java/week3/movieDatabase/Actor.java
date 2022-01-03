package week3.movieDatabase;

import java.util.ArrayList;

public class Actor {
    private String name;
    private ArrayList<Movie> movies;

    public Actor() {
        this.name = null;
        this.movies = new ArrayList<Movie>();
    }

    public Actor(String name) {
        this.name = name;
        this.movies = new ArrayList<Movie>();
    }

    public Actor(String name, ArrayList<Movie> movies) {
        this.name = name;
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
