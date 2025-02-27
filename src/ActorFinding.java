import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActorFinding {
    private final ActorNetwork an;

    ActorFinding(ActorNetwork actornetwork) {
        an = actornetwork;
    }

    public List<String> findShortestPath(String actor1, String actor2, int degofsep) {
        List<String> list = new ArrayList<>();
        list.add(actor1);
        dfs(actor1, actor2, 0, degofsep, list);
        return list;
    }

    public void dfs(String personA, String personZ, int depth, int degofsep, List<String> list) {
        if (depth > degofsep) {
            return;
        }
        if (personA.equals(personZ)) {
            list.add(personZ);
            return;
        } else {
            Collection<String> friends = an.findActor(personA);
            for (String friend : friends) {
                if (!list.contains(friend)) {
                    list.add(friend);
                    if (friend.equals(personZ)) return;
                    depth += 1;
                    dfs(friend, personZ, depth, degofsep, list);
                    if (list.contains(personZ)) return;
                }

            }

        }
    }
}