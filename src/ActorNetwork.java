import java.util.Collection;
import java.util.List;

public interface ActorNetwork {

    Collection<String> findActor(String actorName);
    void addActor(String actorName);
    void addWorkedWith(String actorName, String workedWithName);
}
