import java.util.*;

public class KevinBaconSearch {
    private Map<String, Set<String>> actorGraph = new HashMap<>();
    private Map<String, String> parentMap = new HashMap<>(); //actor relationships
    private Map<String, List<String>> movieGraph = new HashMap<>();
    static int degreeOfBacon = 0;

    public void buildGraph(List<SimpleMovie> movies) {
        for (SimpleMovie movie : movies) {
            //list of actors per movie
            List<String> actors = movie.getActors();
            for (String actor : actors) {
                //new list of coacters with key of the actor
                actorGraph.putIfAbsent(actor, new HashSet<>());
                for (String coActor : actors) {
                    if (!actor.equals(coActor)) {
                        //make sure for both directions
                        actorGraph.get(actor).add(coActor);
                        String key = actor + "-" + coActor;
                        String reverseKey = coActor + "-" + actor;
                        movieGraph.putIfAbsent(key, new ArrayList<>());
                        movieGraph.putIfAbsent(reverseKey, new ArrayList<>());
                        movieGraph.get(key).add(movie.getMovie());
                        movieGraph.get(reverseKey).add(movie.getMovie());
                    }
                }
            }
        }
    }

    //breadth first search algorithm
    public List<String> findShortestPath(String startActor) {
        if (!actorGraph.containsKey(startActor)) {
            return Collections.singletonList("Actor not found in database.");
        }
        //queue and set used to have no dupe actors
        //set is also apparently the most efficient
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        parentMap.clear();

        queue.add(startActor);
        visited.add(startActor);

        while (!queue.isEmpty()) {
            String currentActor = queue.poll();

            //if we find kevin bacon work backwards
            if (currentActor.equals("Kevin Bacon")) {
                return reconstructPath(currentActor);
            }

            for (String neighbor : actorGraph.getOrDefault(currentActor, new HashSet<>())) {
                //ensure that bfs moves in new directions
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, currentActor);
                }
            }
        }
        return Collections.singletonList("No connection found to Kevin Bacon.");
    }

    //work bacwards from kevin bacon to actor
    private List<String> reconstructPath(String actor) {
        List<String> path = new ArrayList<>();
        String current = actor;

        while (parentMap.containsKey(current)) {
            String prevActor = parentMap.get(current);
            String key = prevActor + "-" + current;
            String keyReverse = current + "-" + prevActor;

            List<String> movies = movieGraph.getOrDefault(key, movieGraph.getOrDefault(keyReverse, new ArrayList<>()));

            path.add(prevActor + " → " + current + " (Movies: " + movies + ")");
            degreeOfBacon++;
            current = prevActor;
        }

        Collections.reverse(path); //goes from userinput to kevinbacon
        return path;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       // still takes ~30 seconds because the graph has to be created, way better than the other program
        List<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        KevinBaconSearch kbs = new KevinBaconSearch();
        kbs.buildGraph(movies);

        while(true) {
            System.out.println("Enter an actor's name or (q) to quit");
            String actorName = scanner.nextLine();
            if (actorName.equals("q")){
                break;
            }

            List<String> path = kbs.findShortestPath(actorName);
            System.out.println("\nActor chosen: " + actorName);
            for (String step : path) {
                System.out.println(step);
            }
            System.out.println("Bacon number of: " + degreeOfBacon);
        }
    }
}
