package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.Interfaces.*;

import javax.print.attribute.standard.NumberUp;
import java.util.*;
public class SocialNetwork implements SocialInterface {


//    private HashMap<String, Set<Post>> network = new HashMap<>();
    private HashMap<String, Set<String>> linkedPeople = new HashMap<>(); // HashMap che contiene per ogni utente i propri seguaci
    private HashMap<String, Set<String>> followed = new HashMap<>(); // HashMap che contiene per ogni utente i propri seguiti
    protected Set<Post> postSet = new TreeSet<>();
    private final String separator = "---------------------";
    private int idCounter = 0;

    public void addPost(String user, String text) throws IllegalLengthException, EmptyTextException, NullPointerException {
//        String author = newPost.getAuthor();

        if (text.length() >= 140) // Check della lunghezza del testo
            throw new IllegalLengthException("Il testo puo' contenere al massimo 140 caratteri.");
        if (text.length() == 0)
            throw new EmptyTextException("Il testo non puo' essere vuoto.");
        Post newPost = new Post(user, text, ++idCounter);

//        Set<Post> toAdd = this.network.get(author);
//        if(toAdd == null){
//            toAdd = new HashSet<>();
//            toAdd.add(newPost);
//            this.network.put(author, toAdd);
//        }
//        else {
//            toAdd.add(newPost);
//        }
        postSet.add(newPost);
    }

    public void follow(int ID, String user) throws AutoFollowException, NullPointerException {

        Set<String> toAdd;
        for (Post post: this.postSet){
            if (post.getId() == ID){
                if (post.getAuthor().equals(user))
                    throw new AutoFollowException("Non ci si puo' seguire da soli!");
                toAdd = this.linkedPeople.get(post.getAuthor());
                if (toAdd == null){
                    toAdd = new HashSet<>();
                    toAdd.add(user);
                    this.linkedPeople.put(post.getAuthor(), toAdd);
                    post.addFollow(user);
                    addFollowed(user, post.getAuthor());
                    return;
                }
                else {
                    this.linkedPeople.get(post.getAuthor()).add(user);
                    post.addFollow(user);
                    addFollowed(user, post.getAuthor());
                    return;
                }
            }
        }
        System.out.println("Post non trovato");
    }

    private void addFollowed (String User, String seguito) throws NullPointerException  {
        Set<String> toAdd = this.followed.get(User);
        if (toAdd == null){
            toAdd = new HashSet<>();
            toAdd.add(seguito);
            this.followed.put(User, toAdd);
        }
        else
            this.followed.get(User).add(seguito);

    }
    // REQUIRES: User != null && seguito != null
    // EFFECTS: aggiunge gli utenti User e seguito alla mappa followed, che ha come chiave gli utenti della rete sociale (User) e come valore gli utenti seguiti dal User
    // MODIFIES: this
    public void printAllPosts(){
        for (Post toPrint: postSet){
            System.out.println(separator + "\n");
            toPrint.printPost();
            System.out.println("\n" + separator);

        }
    }

    public Set<Post> getPostSet(){
        return this.postSet;
    }

    public void printSocialNetwork(){
        for (Map.Entry<String, Set<String>> entry: this.linkedPeople.entrySet()){
            Set<String> toPrint = entry.getValue();
            System.out.println("L'utente " + entry.getKey() + " è seguito da:");
            for (String follower: toPrint)
                System.out.println(" - " + follower);
        }

    }

    public Map<String,  Set<String>>  guessFollowers(List<Post> ps) throws NullPointerException{

        HashMap<String, Set<String>> networkByFollowers = new HashMap<>();
        for (Post post: ps){
            if (networkByFollowers.get(post.getAuthor()) == null && this.linkedPeople.get(post.getAuthor())!=null)
                networkByFollowers.put(post.getAuthor(), this.linkedPeople.get(post.getAuthor()));
        }
        return networkByFollowers;
    }

    public Map<String,  Set<String>>  guessFollowers(){
        return this.linkedPeople;
    }

    public List<String> influencers(){
        return influencers(this.linkedPeople);
//      List<String> influencers = new ArrayList<>();
//        for (Map.Entry<String,Set<String>> entry: linkedPeople.entrySet()){
//            if ((followed.get(entry.getKey()) == null && !entry.getValue().isEmpty())
//                    || entry.getValue().size() > followed.get(entry.getKey()).size())
//                influencers.add(entry.getKey());
//            }
//        Collections.sort(influencers);
//        return influencers;
    }

    public List<String> influencers(Map<String, Set<String>> followers) throws NullPointerException{
        List<String> influencers = new ArrayList<>();
        for (Map.Entry<String,Set<String>> entry: followers.entrySet()){
            if ((this.followed.get(entry.getKey()) == null && !entry.getValue().isEmpty())
                    || entry.getValue().size() > this.followed.get(entry.getKey()).size())
                influencers.add(entry.getKey());
        }
        Collections.sort(influencers);
        return influencers;
    }


    public Set<String> getMentionedUser() {
        return getMentionedUser(new ArrayList<>(this.postSet));
//        Set<String> mentionedUsers = new TreeSet<>();
//
//        for (Post post: this.postSet)
//            mentionedUsers.add(post.getAuthor());
//        return mentionedUsers;
    }
    public Set<String> getMentionedUser(List<Post> ps) throws NullPointerException{
        Set<String> mentionedUsers = new TreeSet<>();
        for (Post post: ps)
            mentionedUsers.add(post.getAuthor());
        return mentionedUsers;
    }

    public List<Post> writtenBy(String username) throws IllegalArgumentException, NullPointerException{
        if (username.length() == 0)
            throw new IllegalArgumentException("Username non valido");
        return writtenBy(new ArrayList<>(this.postSet), username);
    }

    public List<Post> writtenBy(List<Post> ps, String username) throws IllegalArgumentException, NullPointerException{

        if (username.length() == 0)
            throw new IllegalArgumentException("Username non valido");

        List<Post> wroteBy = new ArrayList<>();
        for (Post post: ps)
            if (post.getAuthor().equals(username))
                wroteBy.add(post);

        if (wroteBy.isEmpty()){
            System.out.println("Nessun post trovato :(");
            return wroteBy;
        }
        Collections.sort(wroteBy);
        return wroteBy;
    }

    public List<Post> containing(List<String> words) throws NullPointerException{

        List<Post> contains = new ArrayList<>();
        for (Post toScan: this.postSet){
            for (String word: words)
                if (toScan.getText().contains(word)){
                    contains.add(toScan);
                }
        }
        if (contains.isEmpty()) {
            System.out.println("Non ho trovato risultati :(");
            return contains;
        }
        System.out.println("Ecco i post che ho trovato:");
        Collections.sort(contains);
        return contains;
    }

}
