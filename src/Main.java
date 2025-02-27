import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        for (SimpleMovie movie : movies) {
            System.out.println(movie);
        }
        System.out.println("Number of movies: " + movies.size());

        ActorNetworkImp an = new ActorNetworkImp();
        for (SimpleMovie movie : movies){
            for(int i = 0; i < movie.actorsListLength(); i++) {
                an.addActor(movie.getActors().get(i));
            }
        }


        ActorFinding af = new ActorFinding(an);

        an.addWorkedWith("Kevin Bacon", "Tom Cruise");

        System.out.println(af.findShortestPath("Kevin Bacon", "Guy Pearce", 5));
        System.out.println(af);
    }
}
