

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        for (SimpleMovie movie : movies) {
            System.out.println(movie);
        }
        System.out.println("Number of movies: " + movies.size());

        ActorNetworkImp an = new ActorNetworkImp();
        ArrayList<String> d = new ArrayList<>();
        try {
            for (SimpleMovie movie : movies) {
                for (int i = 0; i < movie.actorsListLength(); i++) {
                    String actor = movie.getActors().get(i);
                    System.out.println("Adding actor: " + actor);
                    if(!d.contains(actor)) {
                        d.add(actor);
                        an.addActor(actor);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred: " + e.getMessage());
        }

        ActorFinding af = new ActorFinding(an);

       // an.addWorkedWith("Kevin Bacon", "Tom Cruise");
       // System.out.println(af.findShortestPath("Kevin Bacon", "Guy Pearce", 5));

        System.out.println("Final Actor List: " + d);
        System.out.println("Size of d: " + d.size());
    }
}
