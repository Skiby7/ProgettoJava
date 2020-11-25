package com.MicroBlog.Interfaces;


import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SocialInterface {
    // Overview:


    void addPost(String user, String text) throws IllegalLengthException, EmptyTextException, NullPointerException;
    // REQUIRES: user != null && text != null && text.length() <= 140 && text.length() > 0
    // EFFECTS: newPost viene aggiunto al set dei post (this.postSet)
    // MODIFIES: this
    // THROWS: EmptyTextException se newPost.getText().size() == 0 || IllegalLengthException newPost.getText().size() > 140 || NullPointerException se newPost == null (unchecked)

    void follow(int ID, String user) throws AutoFollowException, NullPointerException;
    // REQUIRES: ID <= postSet.size() && ID > 0 && user.length > 0 && user != null
    // EFFECTS:  dopo la chiamata -> user appartiene al set this.followers.get(post.getAuthor) dove "post" è un post tale che post.geId() == ID.
    //           Inoltre l' autore del post appartiene al set this.followed.get(user)
    // THROWS: AutoFollowException se (ID == post.getId && user.equals(post.getAuthor)) || NullPointerExcepiton se user == null
    // MODIFIES: this

    void printAllPosts();
    // EFFECTS: stampa tutti i post scritti dagli utenti. Se postSet.isEmpty() == true, non stampa nulla

    void printSocialNetwork();
    // EFFECTS: stampa la lista degli utenti in ordine lessicografico e per ogni utente la lista dei followers in ordine lessicografico

    Map<String, Set<String>> guessFollowers(List<Post> ps) throws NullPointerException;
    // REQUIRES: ps != null
    // EFFECTS: crea una mappa a partire dalla lista dei post che ha come chiave gli autori dei post
    //          e come valore un set contenente i followers degli autori.
    // THROWS: NullPointerException se ps == null (unchecked)
    // RETURNS: HashMap<String, Set<String>> networkByFollowers

    Map<String, Set<String>> guessFollowers();
    // EFFECTS: restituisce this.followers, ovvero una mappa che ha per chiave gli autori dei post e come valore gli utenti che seguono gli autori
    // RETURNS: HashMap<String, Set<String>> this.followers

    List<String> influencers(Map<String, Set<String>> followers) throws NullPointerException;
    // REQUIRES: followers != null
    // EFFECTS: restituisce la lista di utenti tale che followers.get(user).size() > followed.get(user).size(),
    //          cioe' gli utenti con piu' follower di quante persone seguano loro.
    // THROWS: NullPointerException se followers == null (unchecked)
    // RETURNS: ArrayList<String> influencers

    List<String> influencers();
    // EFFECTS: esegue la ricerca degli influencers su tutta la rete sociale
    // RETURNS: ArrayList<String> influencers

    Set<String> getMentionedUser();
    // EFFECTS: restituisce un set di tutti gli utenti iscritti a MicroBlog in ordine lessicografico
    // RETURNS: TreeSet<String> mentionedUser

    Set<String> getMentionedUser(List<Post> ps);
    // REQUIRES: ps != null
    // EFFECTS: restituisce un set di utenti che hanno scritto i post contenuti nella lista ps
    // THROWS: NullPointerException se ps == null
    // RETURNS: TreeSet<String> mentionedUser

    List<Post> writtenBy(String username) throws IllegalArgumentException, NullPointerException;
    // REQUIRES: username != null && username.length > 0
    // EFFECTS: restituisce la lista dei post appartenenti a this.postList tali che post.getAuthor.equals(username).
    //
    // THROWS: IllegalArgumentException se username.length == 0 || NullPointerException se username == null
    // RETURNS: ArrayList<Post> wroteBy

    List<Post> writtenBy(List<Post> ps, String username) throws IllegalArgumentException, NullPointerException;
    // REQUIRES: username != null && username.length > 0 && ps != null
    // EFFECTS: restituisce la lista dei post appartenenti a ps tali che post.getAuthor.equals(username).
    //          Se username == null || ps == null restituisco una lista vuota
    // THROWS: IllegalArgumentException se username.length == 0
    // RETURNS: ArrayList<Post> wroteBy

    List<Post> containing(List<String> words) throws NullPointerException;
    // REQUIRES: words != null
    // EFFECTS: restituisce una lista di post che contengono le parole cercate o parte di esse in ordine temporale
    // THROWS: NullPointerException se words == null
    // RETURNS: List<Post> contains

}
