package week3.movieDatabase;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieDatabase {
    private final ArrayList<Movie> movieList;
    private final ArrayList<Actor> actorList;

    public MovieDatabase() {
        this.movieList = new ArrayList<Movie>();
        this.actorList = new ArrayList<Actor>();
    }

    public void addMovie(String name, String[] actors) {
        for(Movie movie : movieList) {
            if(movie.getName().equals(name)) {
                System.out.println("Movie already registered.");
                return;
            }
        }

        ArrayList<Actor> tmpActors = new ArrayList<Actor>();

        Movie movie = new Movie(name, tmpActors);
        movieList.add(movie);

        for (String actor : actors) {
            Actor act = new Actor(actor);

            ArrayList<Movie> actorMoviesList = act.getMovies();
            actorMoviesList.add(movie);
            act.setMovies(actorMoviesList);

            tmpActors.add(act);
        }

        if (actorList.isEmpty()) {
            actorList.addAll(tmpActors);
        } else {
            for (Actor a : tmpActors) {
                boolean already = false;
                for (Actor act : actorList) {
                    if (act.getName().equals(a.getName())) {
                        ArrayList<Movie> actorMoviesList = act.getMovies();
                        actorMoviesList.add(movie);
                        act.setMovies(actorMoviesList);

                        already = true;
                    }
                }
                if (!already) {
                    actorList.add(a);
                }
            }
        }
    }

    public void addRating(String name, double rating) {
        for (Movie m : movieList) {
            if (m.getName().equals(name)) {
                if (m.getRating() == -1) {
                    try {
                        if (rating >= 0) {
                            m.setRating(rating);
                        } else {
                            throw new NumberFormatException();
                        }
                    } catch (Exception e) {
                        System.out.println("Couldn't set the rating.");
                    }
                } else {
                    System.out.println("There already is a rating for this movie!");
                }
                return;
            }
        }
        System.out.println("Movie not found.");
    }

    public void updateRating(String name, double newRating) {
        for (Movie m : movieList) {
            if (m.getName().equals(name)) {
                if (m.getRating() != -1) {
                    try {
                        if (newRating >= 0) {
                            m.setRating(newRating);
                        } else {
                            throw new NumberFormatException();
                        }
                    } catch (Exception e) {
                        System.out.println("Couldn't set the rating.");
                    }
                } else {
                    System.out.println("There is no rating yet!");
                }
                return;
            }
        }
        System.out.println("Movie not found.");
    }

    public String getBestActor() {
        String bestName = "";
        double bestRating = 0;

        for (Actor a : actorList) {
            int average = 0;
            ArrayList<Movie> movie = a.getMovies();

            for (Movie m : movie) {
                average += m.getRating();
            }
            average = average / movie.toArray().length;

            if (average > bestRating) {
                bestRating = average;
                bestName = a.getName();
            }
        }

        if (bestName.equals("")) {
            return "There is no actor is the list.";
        }

        return bestName;
    }

    public String getBestMovie() {
        String bestName = "";
        double bestRating = 0;

        for (Movie m : movieList) {
            if (m.getRating() > bestRating) {
                bestRating = m.getRating();
                bestName = m.getName();
            }
        }

        if (bestName.equals("")) {
            return "There is no movie is the list.";
        }

        return bestName;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public ArrayList<Actor> getActorList() {
        return actorList;
    }

    public static void main(String[] args) {
        MovieDatabase mdb = new MovieDatabase();
        Scanner userInput = new Scanner(System.in); // Can be added so the user can add his own movies

        mdb.addMovie("Sleepers", new String[]{"Brad Pitt", "Dustin Hoffman", "Kevin Bacon"});
        mdb.addRating("Sleepers", 74);
        mdb.addMovie("Troy", new String[]{"Brad Pitt", "Diane Kruger", "Eric Bana"});
        mdb.addRating("Troy", 54);
        mdb.addMovie("Meet Joe Black", new String[]{"Brad Pitt", "Anthony Hopkins"});
        mdb.addRating("Meet Joe Black", 51);
        mdb.addMovie("Oceans Eleven", new String[]{"Brad Pitt", "Julia Roberts", "George Clooney"});
        mdb.addRating("Oceans Eleven", 82);
        /*
        *
        * Should be a long list of movies
        * Unfortunately, the "movies.txt" file is a real pain in the ass to read
        * (and I'm quite lazy to write a method to read it)
        *
        */
        mdb.addMovie("Seven", new String[]{"Brad Pitt", "Morgan Freeman"});
        mdb.addRating("Seven", 79);
        mdb.addMovie("Finding Neverland", new String[]{"Johnny Depp", "Kate Winslet"});
        mdb.addRating("Finding Neverland", 83);

        System.out.println("The best actor is " + mdb.getBestActor());
        System.out.println("The best movie is " + mdb.getBestMovie());
    }
}
