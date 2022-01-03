package week3.movieDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieDatabaseTest {
    MovieDatabase mdb;

    @BeforeEach
    void setUp() {
        mdb = new MovieDatabase();
    }

    @Test
    void addMovie() {
        String[] movieList = {"Test 1", "Test 2"};
        String[] actorList = {"act 1", "act 2", "act 3"};

        mdb.addMovie("Test 1", new String[]{"act 1", "act 2"});
        mdb.addMovie("Test 1", new String[]{"act 1", "act 2"});
        mdb.addMovie("Test 2", new String[]{"act 1", "act 3"});

        ArrayList<String> mdbMovie = new ArrayList<>();
        ArrayList<String> mdbActor = new ArrayList<>();

        for (Movie m : mdb.getMovieList()) {
            mdbMovie.add(m.getName());
        }
        for (Actor a : mdb.getActorList()) {
            mdbActor.add(a.getName());
        }

        Object[] mdbMovieArray = mdbMovie.toArray();
        Object[] mdbActorArray = mdbActor.toArray();

        assertArrayEquals(movieList, mdbMovieArray);
        assertArrayEquals(actorList, mdbActorArray);
    }

    @Test
    void addRating() {
        mdb.addMovie("Test 1", new String[]{"act 1", "act 2"});
        mdb.addRating("Test 1", 50);

        double rating = 0;

        for (Movie m : mdb.getMovieList()) {
            rating = m.getRating();
        }

        assertEquals(50, rating);
    }

    @Test
    void updateRating() {
        mdb.addMovie("Test 1", new String[]{"act 1", "act 2"});
        mdb.addRating("Test 1", 50);
        mdb.updateRating("Test 2", 80);

        double rating = 0;

        for (Movie m : mdb.getMovieList()) {
            rating = m.getRating();
        }
        assertEquals(50, rating);

        mdb.updateRating("Test 1", 80);
        for (Movie m : mdb.getMovieList()) {
            rating = m.getRating();
        }
        assertEquals(80, rating);
    }

    @Test
    void getBestActor() {
        mdb.addMovie("Test 1", new String[]{"act 1", "act 2"});
        mdb.addMovie("Test 2", new String[]{"act 1", "act 2"});
        mdb.addMovie("Test 3", new String[]{"act 1", "act 3"});
        mdb.addMovie("Test 4", new String[]{"act 4"});
        mdb.addMovie("Test 5", new String[]{"act 4"});
        mdb.addMovie("Test 6", new String[]{"act 4"});

        mdb.addRating("Test 1", 50);
        mdb.addRating("Test 2", 90);
        mdb.addRating("Test 3", 70);
        mdb.addRating("Test 4", 99);
        mdb.addRating("Test 5", 99);
        mdb.addRating("Test 6", 99);

        assertEquals("act 4", mdb.getBestActor());
    }

    @Test
    void getBestMovie() {
        mdb.addMovie("Test 1", new String[]{"act 1", "act 2"});
        mdb.addMovie("Test 2", new String[]{"act 1", "act 2"});
        mdb.addMovie("Test 3", new String[]{"act 1", "act 3"});

        mdb.addRating("Test 1", 50);
        mdb.addRating("Test 2", 90);
        mdb.addRating("Test 3", 70);

        assertEquals("Test 2", mdb.getBestMovie());
    }
}