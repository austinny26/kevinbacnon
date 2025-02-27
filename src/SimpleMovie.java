import java.util.ArrayList;
import java.util.List;

public class SimpleMovie {
    private String title;
    private String actorsData;
    private ArrayList<String> actors;

    public ArrayList<String> getActors() {
        return actors;
    }
    public int actorsListLength(){
        return actors.size();
    }
    public String getMovie(){
        return title;
    }

    public SimpleMovie(String t, String a) {
        title = t;
        actorsData = a;
        actors = new ArrayList<String>();
        String[] tempActors = actorsData.split(":");
        for (int i = 0; i < tempActors.length; i++) {
            actors.add(tempActors[i]);
        }

    }


    public String toString() {
        return "Title: " + title + "\n" + "Actors: " + actors + "\n";
    }
}