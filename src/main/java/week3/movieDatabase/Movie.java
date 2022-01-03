package week3.movieDatabase;

import java.util.ArrayList;

public class Movie {
    private String name;
    private ArrayList<Actor> actors;
    private double rating;

    public Movie() {
        this.name = null;
        this.actors = new ArrayList<Actor>();
        this.rating = -1;
    }

    public Movie(String name) {
        this.name = name;
        this.actors = new ArrayList<Actor>();
        this.rating = -1;
    }

    public Movie(String name, ArrayList<Actor> actors) {
        this.name = name;
        this.actors = actors;
        this.rating = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
