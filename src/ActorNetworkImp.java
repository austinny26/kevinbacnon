import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ActorNetworkImp implements ActorNetwork {

    ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
    HashMap<String, Collection<String>> relationships = new HashMap<>();

    @Override
    public Collection<String> findActor(String movie) {
        return relationships.get(actorName);
    }

    public Collection<String> findMovie(String actorName) {
        return relationships.get(actorName);
    }

    @Override
    public void addActor(String actorName) {

        List<String> actorMovies = new ArrayList<>();
        for(SimpleMovie movie : movies){
            if(movie.getActors().contains(actorName)){
                actorMovies.add(movie.getMovie());
            }
        }
        relationships.put(actorName,actorMovies);

    }

    @Override
    public void addWorkedWith(String actorName, String workedWithName) {

        if (!relationships.containsKey(actorName)) {
            addActor(actorName);
        }

        if (!relationships.containsKey(workedWithName)) {
            addActor(workedWithName);
        }

        relationships.get(actorName).add(workedWithName);
        relationships.get(workedWithName).add(actorName);
    }

    @Override
    public void addWorkedWithMovie(String workedWithName, List<String> movie) {
        relationships.get(workedWithName);
    }


}
