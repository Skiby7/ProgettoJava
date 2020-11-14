import java.sql.PseudoColumnUsage;
import java.util.*;

public class SocialNetwork {


    private HashMap<String, Set<Post>> network = new HashMap<>();
    private HashMap<String, Set<String>> linkedPeople = new HashMap<>();
    private Set<Post> postSet = new HashSet<>();
    private final String separator = "---------------------";

    public void addPost(String author, Post newPost){
        Set<Post> toAdd = network.get(author);
        if(toAdd == null){
            toAdd = new HashSet<>();
            toAdd.add(newPost);
            network.put(author, toAdd);
        }
        else {
            toAdd.add(newPost);
        }
        postSet.add(newPost);
    }

    public void follow(int ID, String user) {
        System.out.print("Cerco il post");
        Set<String> toAdd;
        for (Post post: postSet){
            if (post.getId() == ID){
                toAdd = linkedPeople.get(post.getAuthor());
                if (toAdd == null){
                    toAdd = new HashSet<>();
                    toAdd.add(user);
                    linkedPeople.put(post.getAuthor(), toAdd);
                    System.out.println("\nUtente seguito con successo");
                    return;
                }
                else {
                    linkedPeople.get(post.getAuthor()).add(user);
                    System.out.println("\nUtente seguito con successo");
                    return;
                }
            }
            System.out.print(".");
        }
        System.out.println("Post non trovato");
    }

    public void printAllPosts(){
        for (Post toPrint: postSet){
            System.out.println(separator + "\n");
            toPrint.printPost();
            System.out.println("\n" + separator);

        }
    }

    public void printSocialNetwork(HashMap<String, Set<String>> microNet){
        for (Map.Entry<String, Set<String>> entry: microNet.entrySet()){
            Set<String> toPrint = entry.getValue();
            System.out.println("L'utente " + entry.getKey() + " è seguito da:");
            for (String follower: toPrint)
                System.out.println(" - " + follower);
        }

    }

    public void printSocialNetwork(){
        printSocialNetwork(linkedPeople);
    }

    public List<Post> getPostList(){
        List<Post> posts = new LinkedList<>();
        for (Map.Entry<String, Set<Post>> entry: network.entrySet()) {
            posts.addAll(entry.getValue());

        }
        return posts;
    }

    public Map<String,  Set<String>>  guessFollowers(List<Post> ps){
        HashMap<String, Set<String>> networkByFollowers = new HashMap<>();
        for(Post post : ps){
            if (networkByFollowers.get(post.getAuthor())==null){
                Set<String> toAdd = new HashSet<>(post.getFollowers());
                networkByFollowers.put(post.getAuthor(), toAdd);

            }
            else
                networkByFollowers.get(post.getAuthor()).addAll(post.getFollowers());          }

        return networkByFollowers;
    }
    public Map<String,  Set<String>>  guessFollowers(){
        List<Post> postList = new LinkedList<>(postSet);
        return guessFollowers(postList);
    }

    public List<String> influencers (Map<String, Set<String>> followers){
        List<String> influencers = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry: followers.entrySet()){
            if (influencers.isEmpty())
                influencers.add(entry.getKey());

            else {
                int i = 0;
                while (entry.getValue().size() < followers.get(influencers.get(i)).size())
                    i++;

                influencers.add(i, entry.getKey());
            }
        }
        return influencers;
    }

    public List<String> influencers (){
        List<String> influencers = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry: linkedPeople.entrySet()){
            if (influencers.isEmpty())
                influencers.add(entry.getKey());

            else {
                int i = 0;
                while (entry.getValue().size() < linkedPeople.get(influencers.get(i)).size())
                    i++;

                influencers.add(i, entry.getKey());
            }
        }
        return influencers;
    }


    public List<String> getMentionedUser() {
        List<String> mentionedUser = new LinkedList<>();

        for (Map.Entry<String, Set<Post>> entry : network.entrySet())
            mentionedUser.add(entry.getKey());

        return mentionedUser;
    }

    public List<Post> writtenBy(String username){

        return new LinkedList<Post>(network.get(username));
    }

    public List<Post> writtenBy(List<Post> ps, String username){

        List<Post> wroteBy = new LinkedList<Post>();
        for (Post post: ps)
            if (post.getAuthor().equals(username))
                wroteBy.add(post);

        return wroteBy;
    }

    public List<Post> containing(List<String> words){
        List<Post> contains = new LinkedList<>();
        for (Post toScan: postSet){
            for (String word: words)
                if (toScan.getText().contains(word)){
                    contains.add(toScan);
                }
        }
        if (contains.isEmpty())
            System.out.println("Non ho trovato risultati :(");
        return contains;
    }

}
