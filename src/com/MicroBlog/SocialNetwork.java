package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.Interfaces.*;
import java.util.*;

public class SocialNetwork implements SocialInterface {



//    private HashMap<String, Set<Post>> network = new HashMap<>();
    private final HashMap<String, Set<String>> followers; // followers[a] => utenti che seguono a
    private final HashMap<String, Set<String>> followed; // followed[a] => utenti seguiti da a
    protected final Set<Post> postSet;
    private int idCounter;

    public SocialNetwork(){
        this.followers = new HashMap<>();
        this.followed = new HashMap<>();
        this.postSet = new TreeSet<>();
        this.idCounter = 0;
    }


    public void addPost(String user, String text) throws IllegalLengthException, EmptyTextException, IllegalArgumentException, NullPointerException {
        if (user.isBlank())
            throw new IllegalArgumentException("L'username non e' valido.");
        if (text.length() >= 140) // Check della lunghezza del testo
            throw new IllegalLengthException("Il testo puo' contenere al massimo 140 caratteri.");
        if (text.isBlank()) // Check che il testo non sia vuoto
            throw new EmptyTextException("Il testo non puo' essere vuoto.");
        Post newPost = new Post(user, text, ++idCounter); // Istanzio un nuovo post con user e text
        postSet.add(newPost); // Aggiungo il post nel postSet
    }

    public void follow(int id, String user) throws AutoFollowException, IllegalArgumentException, NullPointerException {
        if (user.isBlank())
            throw new IllegalArgumentException("L'username non e' valido.");

        Set<String> toAdd; // Dichiaro un nuovo Set nel caso l'utente del post target non abbia ancora followers
        for (Post post: this.postSet){
            if (post.getId() == id){
                if (post.getAuthor().equals(user)) // Se l'utente si vuole seguire da solo lancio un'eccezione
                    throw new AutoFollowException("Non ci si puo' seguire da soli!");
                toAdd = this.followers.get(post.getAuthor()); // toAdd si riferise al set di followers dell'utente autore del post
                if (toAdd == null){ // Se post.getAuthor() non ha ancora seguaci, istanzio un nuovo HashSet e lo aggiungo alla mappa
                    toAdd = new HashSet<>();
                    toAdd.add(user);
                    this.followers.put(post.getAuthor(), toAdd);
                }
                else {
                    this.followers.get(post.getAuthor()).add(user); // Se l'utente ha gia' dei followers, aggiungo user al Set
                }
                post.addFollow(user); // Aggiungo il follower anche al post
                addFollowed(user, post.getAuthor()); // Popolo la mappa followed
                return;
            }
        }
        System.out.println("Post non trovato");
    }

    private void addFollowed (String user, String followedUser) throws NullPointerException  {
        Set<String> toAdd = this.followed.get(user); // Stesso procedimento di prima, dove followedUser e' l'autore del post
        if (toAdd == null){
            toAdd = new HashSet<>();
            toAdd.add(followedUser);
            this.followed.put(user, toAdd);
        }
        else
            this.followed.get(user).add(followedUser);

    }
    // REQUIRES: User != null && followedUser != null
    // EFFECTS: aggiunge gli utenti User e seguito alla mappa followed, che ha come chiave gli utenti della rete sociale (User) e come valore gli utenti seguiti dal User
    // MODIFIES: this

    public void printAllPosts(){
        for (Post toPrint: postSet){
            System.out.println("\n");
            toPrint.printPost();
            System.out.println("\n");
        }
    }

    public Set<Post> getPostSet(){
        return this.postSet;
    }

    public void printSocialNetwork(){
        for (Map.Entry<String, Set<String>> entry: this.followers.entrySet()){
            Set<String> toPrint = entry.getValue();
            System.out.println("L'utente " + entry.getKey() + " è seguito da:");
            for (String follower: toPrint)
                System.out.println(" - " + follower);
        }

    }

    public Map<String,  Set<String>>  guessFollowers(List<Post> ps) throws NullPointerException{
        Map<String, Set<String>> networkByFollowers = new HashMap<>();
        for (Post post: ps){ // Per ogni post in ps controllo se post.getAuthor() e' gia' stato inserito nella mappa
            if (networkByFollowers.get(post.getAuthor()) == null) // Se l'autore del post non e' ancora presente lo inserisco insieme a tutti i follower di quel post
                networkByFollowers.put(post.getAuthor(), post.getFollowers());

            else // Altrimenti aggiungo tutti i follower del post dato che, usando un Set, sono sicuro di non avere duplicati
                networkByFollowers.get(post.getAuthor()).addAll(post.getFollowers());
        }
        return networkByFollowers;
    }

    public Map<String,  Set<String>>  guessFollowers(){
        return this.followers; // Restituisco la mappa this.followrs che contiene una mappa <utenti, suoi followers> creata di paripasso all'evoluzione della rete sociale
    }

    public List<String> influencers(){
      List<String> influencers = new ArrayList<>();
        for (Map.Entry<String,Set<String>> entry: followers.entrySet()){
            // Per ogni entry controllo se l'utente ha piu' followers che followed
            if ((followed.get(entry.getKey()) == null && !entry.getValue().isEmpty())
                    || entry.getValue().size() > followed.get(entry.getKey()).size())
                influencers.add(entry.getKey());
            }
        Collections.sort(influencers); // Ordino lessicograficamente l'arraylist
        return influencers;
    }

//    public List<String> influencers(Map<String, Set<String>> followers) throws NullPointerException{
//        List<String> influencers = new ArrayList<>();
//        for (Map.Entry<String,Set<String>> entry: followers.entrySet()){
//            if ((this.followed.get(entry.getKey()) == null && !entry.getValue().isEmpty())
//                    || entry.getValue().size() > this.followed.get(entry.getKey()).size())
//                influencers.add(entry.getKey());
//        }
//        Collections.sort(influencers);
//        return influencers;
//    }

    public List<String> influencers(Map<String, Set<String>> followers) throws NullPointerException{
        // Questo metodo accetta anche mappe followers di utenti che non sono dentro il social network
        List<String> influencers = new ArrayList<>();
        Map<String, Set<String>> followedMap = new HashMap<>();
        HashSet<String> toadd;
        // Mi ricostruisco la mappa followed a partire dai followers
        for (Map.Entry<String,Set<String>> entry: followers.entrySet()){
            for(String people: entry.getValue()){
                if (followedMap.get(people) == null) {
                    toadd = new HashSet<>();
                    toadd.add(entry.getKey());
                    followedMap.put(people, toadd);
                }
                else
                    followedMap.get(people).add(entry.getKey());
            }
        }
        // Eseguo lo stesso processo di prima ma sulla mappa followers e su followedMap che ho appena creato
        for (Map.Entry<String,Set<String>> entry: followers.entrySet()){
            if ((followedMap.get(entry.getKey()) == null && !entry.getValue().isEmpty())
                        || entry.getValue().size() > followedMap.get(entry.getKey()).size())
                influencers.add(entry.getKey());
        }
        Collections.sort(influencers);
        return influencers;
    }



    public Set<String> getMentionedUser() {
        return getMentionedUser(new ArrayList<>(this.postSet)); // Converto this.postSet in un ArrayList e chiamo getMentionedUser
    }

    public Set<String> getMentionedUser(List<Post> ps) throws NullPointerException{ // Questo metodo estrae la lista degli utenti che hanno postato da una generica lista di post
        Set<String> mentionedUsers = new TreeSet<>();
        for (Post post: ps)
            mentionedUsers.add(post.getAuthor());
        return mentionedUsers;
    }

    public List<Post> writtenBy(String username) throws IllegalArgumentException, NullPointerException{
        if (username.isBlank())
            throw new IllegalArgumentException("Username non valido");
        return writtenBy(new ArrayList<>(this.postSet), username); // Se non viene specificata una laista di post, cerco in tutti i post pubblicati
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
        Collections.sort(wroteBy); // Ordino in ordine cronologico inverso i post (dal piu' recente al piu' vecchio)
        return wroteBy;
    }

    public List<Post> containing(List<String> words) throws NullPointerException{
        List<Post> contains = new ArrayList<>();
        for (Post toScan: this.postSet){ // Per ogni post guardo se ogni parola fosse contenuta nella lista di post, anche se parzialmente
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
