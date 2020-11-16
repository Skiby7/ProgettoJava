import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SocialInterface {
    void addPost(Post newPost) throws SocialNetworkError;
    void follow(int ID, String user) throws SocialNetworkError;
    void printAllPosts();
    void printSocialNetwork(HashMap<String, Set<String>> microNet);
    Map<String,  Set<String>> guessFollowers(List<Post> ps);
    List<String> influencers(Map<String, Set<String>> followers);
    Set<String> getMentionedUser();
    List<Post> writtenBy(String username);
    List<Post> writtenBy(List<Post> ps, String username);
    List<Post> containing(List<String> words);
}
