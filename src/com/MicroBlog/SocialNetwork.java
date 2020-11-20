package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.Interfaces.*;
import java.util.*;
public class SocialNetwork implements SocialInterface {


//    private HashMap<String, Set<Post>> network = new HashMap<>();
    private HashMap<String, Set<String>> linkedPeople = new HashMap<>(); // HashMap che contiene per ogni utente i propri seguaci
    private HashMap<String, Set<String >> followed = new HashMap<>(); // HashMap che contiene per ogni utente i propri seguiti
    private Set<Post> postSet = new HashSet<>();
    private final String separator = "---------------------";

    public void addPost(Post newPost) throws IllegalLengthException, NullPointerException{
//        String author = newPost.getAuthor();
        if (newPost.getText().length() >= 140) // Check della lunghezza del testo
            throw new IllegalLengthException("Il testo puo' contenere al massimo 140 caratteri.\n");
        if (newPost.getText().length() == 0)
            throw new IllegalLengthException("Il testo non puo' essere vuoto.\n");
        if (newPost == null)
            throw new NullPointerException("Il post non e' valido");
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

    public void follow(int ID, String user) throws AutoFollowException, NullPointerException{
        if (user == null)
            throw new NullPointerException("Ciao ");

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

    private void addFollowed (String User, String seguito)  {
        Set<String> toAdd = followed.get(User);
        if (toAdd == null){
            toAdd = new HashSet<>();
            toAdd.add(seguito);
            followed.put(User, toAdd);
        }
        else {
            followed.get(User).add(seguito);
        }
    }

    public void printAllPosts(){
        for (Post toPrint: postSet){
            System.out.println(separator + "\n");
            toPrint.printPost();
            System.out.println("\n" + separator);

        }
    }

    public void printSocialNetwork(){
        for (Map.Entry<String, Set<String>> entry: this.linkedPeople.entrySet()){
            Set<String> toPrint = entry.getValue();
            System.out.println("L'utente " + entry.getKey() + " è seguito da:");
            for (String follower: toPrint)
                System.out.println(" - " + follower);
        }

    }

    public Map<String,  Set<String>>  guessFollowers(List<Post> ps) {
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
        List<String> influencers = new ArrayList<>();
        for (Map.Entry<String,Set<String>> entry: linkedPeople.entrySet()){
            if ((followed.get(entry.getKey()) == null && !entry.getValue().isEmpty())
                    || entry.getValue().size() > followed.get(entry.getKey()).size())
                influencers.add(entry.getKey());
            }
        Collections.sort(influencers);
        return influencers;
    }

    public List<String> influencers(Map<String, Set<String>> followers){
        List<String> influencers = new ArrayList<>();
        for (Map.Entry<String,Set<String>> entry: followers.entrySet()){
            if ((followed.get(entry.getKey()) == null && !entry.getValue().isEmpty())
                    || entry.getValue().size() > followed.get(entry.getKey()).size())
                influencers.add(entry.getKey());
        }
        Collections.sort(influencers);
        return influencers;
    }


    public Set<String> getMentionedUser() {
        Set<String> mentionedUsers = new TreeSet<>();

        for (Post post: postSet)
            mentionedUsers.add(post.getAuthor());
        return mentionedUsers;
    }
    public Set<String> getMentionedUser(List<Post> ps) {
        Set<String> mentionedUsers = new TreeSet<>();
        for (Post post: ps)
            mentionedUsers.add(post.getAuthor());
        return mentionedUsers;
    }

    public List<Post> writtenBy(String username){
        List<Post> wroteBy = writtenBy(new ArrayList<>(this.postSet), username);
        return wroteBy;
    }

    public List<Post> writtenBy(List<Post> ps, String username){

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

    public List<Post> containing(List<String> words){
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
