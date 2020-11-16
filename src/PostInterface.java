import java.sql.Timestamp;
import java.util.HashSet;

public interface PostInterface {

    String getAuthor();
    int getId();
    Timestamp getTime();
    void addFollow(String follower);
    HashSet<String> getFollowers();
    void printPost();


}
