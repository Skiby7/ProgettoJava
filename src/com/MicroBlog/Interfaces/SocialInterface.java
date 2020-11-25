package com.MicroBlog.Interfaces;


import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SocialInterface {
    // Overview:


    void addPost(String user, String text) throws IllegalLengthException, EmptyTextException, IllegalArgumentException;
    // REQUIRES: user ≠ null ∧ text ≠ null ∧ text.length() ≤ 140 ∧ text.length() > 0.
    // EFFECTS: un nuovo post con post.getAuthor().equals(user) ∧ post.getText().equals(text) viene aggiunto al Set dei post (this.postSet).
    // MODIFIES: this
    // THROWS: EmptyTextException se text.isBlank() ∨ IllegalLengthException se text.length() > 140 ∨ IllegalArgumentException se user.isBlank().

    void follow(int id, String user) throws AutoFollowException, IllegalArgumentException;
    // REQUIRES: id ≤ postSet.size() && id > 0 && !user.isBlank()
    // EFFECTS:  user ∈ this.followers.get(post.getAuthor) con post t.c. post.geId() = id.
    //           Inoltre post.getAuthor() ∈ this.followed.get(user).
    // THROWS: AutoFollowException se (id = post.getId ∧ user.equals(post.getAuthor)) ∨ IllegalArgumentException se user.isBlank().
    // MODIFIES: this

    void printAllPosts();
    // EFFECTS: stampa tutti i post scritti dagli utenti.

    void printSocialNetwork();
    // EFFECTS: stampa la lista degli utenti in ordine lessicografico e per ogni utente la lista dei followers in ordine lessicografico

    Map<String, Set<String>> guessFollowers(List<Post> ps) throws NullPointerException;
    // REQUIRES: ps ≠ null ∧ post ≠ null ∀post ∈ ps
    // EFFECTS: networkByFollowers è una mappa t.c. networkByFollowers.get(user) è il Set di chi segue user
    // THROWS: NullPointerException se ps = null ∨ NullPointerException se ps ∋ post = null
    // RETURNS: HashMap<String, Set<String>> networkByFollowers

    Map<String, Set<String>> guessFollowers();
    // EFFECTS: restituisce this.followers, ovvero una mappa che ha per chiave gli autori dei post e come valore gli utenti che seguono gli autori
    // RETURNS: HashMap<String, Set<String>> this.followers

    List<String> influencers();
    // EFFECTS: la lista influencers continene tutti e solo gli utenti che sono seguite da più persone di quante seguano loro
    // RETURNS: ArrayList<String> influencers

    List<String> influencers(Map<String, Set<String>> followers) throws NullPointerException;
    // REQUIRES: followers ≠ null
    // EFFECTS: la lista influencers contiene tutti e solo gli user tali che followers.get(user).size() > followed.get(user).size(),
    //          cioè gli utenti con più follower di quante persone seguano loro.
    // THROWS: NullPointerException se followers = null
    // RETURNS: ArrayList<String> influencers

    Set<String> getMentionedUser();
    // EFFECTS: restituisce un set di tutti gli utenti iscritti a MicroBlog
    // RETURNS: TreeSet<String> mentionedUser

    Set<String> getMentionedUser(List<Post> ps);
    // REQUIRES: ps ≠ null ∧ ∀post ∈ ps, post ≠ null
    // EFFECTS: il Set mentionedUser contiene tutti gli utenti che hanno scritto almeno un post
    // THROWS: NullPointerException se (ps = null ∨ ps ∋ post = null)
    // RETURNS: TreeSet<String> mentionedUser

    List<Post> writtenBy(String username) throws IllegalArgumentException;
    // REQUIRES: !username.isBlank()
    // EFFECTS: la lista wroteBy contiene tutti e solo i post ∈ this.postList t.c. post.getAuthor.equals(username).
    //
    // THROWS: IllegalArgumentException se username.isBlank()
    // RETURNS: ArrayList<Post> wroteBy

    List<Post> writtenBy(List<Post> ps, String username) throws IllegalArgumentException;
    // REQUIRES: !username.isBlank() ∧  ps ≠ null
    // EFFECTS: la lista wroteBy contiene tutti e solo i post ∈ ps t.c. post.getAuthor.equals(username).
    //
    // THROWS: IllegalArgumentException se username.isBlank()
    // RETURNS: ArrayList<Post> wroteBy

    List<Post> containing(List<String> words) throws NullPointerException;
    // REQUIRES: words ≠ null ∧ ∀word ∈ words, word ≠ null
    // EFFECTS: la lista contains contiene tutti e solo i post t.c. post.getText().contains(word) ∀word ∈ words
    // THROWS: NullPointerException se words = null ∧ NullPointerException se words ∋ word = null
    // RETURNS: List<Post> contains

}
