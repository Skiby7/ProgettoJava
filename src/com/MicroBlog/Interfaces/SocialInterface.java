package com.MicroBlog.Interfaces;


import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SocialInterface {
    void addPost(Post newPost) throws IllegalLengthException, NullPointerException;
    // REQUIRES: newPost != null && newPost.getText().length() <= 140
    // EFFECTS: newPost viene aggiunto al set dei post (postSet) e l'utente viene aggoiunto alla rete sociale
    // MODIFIES: this.postSet
    // THROWS: SocialNetworError

    void follow(int ID, String user) throws AutoFollowException, NullPointerException;
    // REQUIRES: ID <= postSet.size() && ID > 0 && user.length > 0 && (user contenuto nelle key della mappa linkedPeople)
    // EFFECTS:  user appartiene al set linkedPeople.get(autore del post con post.getID == ID) e
    //              l' autore del post appartiene al set followed.get(user)
    // THROWS: SocialNetworkError se (ID == post.getId && user.equals(post.getAuthor))
    // MODIFIES this.linkedPeople e this.followed
    // RETURNS: void

    void printAllPosts();
    // REQUIRES: postSet != null
    // EFFECTS: stampa tutti i post creati. Se postSet.isEmpty() == true, non stampa nulla

    void printSocialNetwork();
    // REQUIRES: this.linkedPeople != null
    // EFFECTS: stampa la lista degli utenti in ordine lessicografico e per ogni utente la lista dei loro followers in ordine lessicografico
    // THROWS: NullPointerException se linkedPeople == null

    Map<String,  Set<String>> guessFollowers(List<Post> ps);
    // REQUIRES: ps != null
    // EFFECTS: restituisce una Mappa contenente per ogni utente che e' chiave della mappa, un Set contenente i followers dell'utente,
    //          creata a partire da una lista di post data
    // THROWS: NullPointerException se ps == null (unchecked)
    // RETURNS: HashMap<String, Set<String>> networkByFollowers

    Map<String, Set<String>> guessFollowers();
    // REQUIRES: this.linkedPeople != null
    // EFFECTS: restituisce linkedPeople
    // THROWS: NullPointerException se this.linkePeople == null (unchecked)
    // RETURNS: this.linkedPeople

    List<String> influencers(Map<String, Set<String>> followers);
    // REQUIRES: followers != null
    // EFFECTS: restituisce la lista di utenti tale che followers.get(user).size() > this.followed.get(user).size(),
    //          cioe' gli utenti con piu' follower di quante persone seguano loro
    // THROWS: NullPointerException se followers == null (unchecked)
    // RETURNS: ArrayList<String> influencers

    Set<String> getMentionedUser();
    // REQUIRES: this.postSet != null && per ogni post in postSet post.getAuthor != null
    // EFFECTS: restituisce un set di tutti gli utenti iscritti a MicroBlog in ordine lessicografico
    // THROWS: NullPointerException se postSet == null || post.getAuthor == null
    // RETURNS: TreeSet<String> mentionedUser

    Set<String> getMentionedUser(List<Post> ps);
    // REQUIRES: ps != null && per ogni post in ps post.getAuthor != null
    // EFFECTS: restituisce un set degli utenti che hanno scritto i post nella lista ps in ordine lessicografico
    // THROWS: NullPointerException se ps == null || post.getAuthor == null
    // RETURNS: TreeSet<String> mentionedUser

    List<Post> writtenBy(String username);
    // REQUIRES: username != null && username.length > 0
    // EFFECTS: restituisce la lista dei post appartenenti a this.postList tali che post.getAuthor.equals(username) in ordine lessicografico
    // THROWS: IllegalArgumentException se username.length == 0 || NullPointerException se username == null
    // RETURNS: ArrayList<Post> wroteBy

    List<Post> writtenBy(List<Post> ps, String username);
    // REQUIRES: username != null && username.length > 0 && ps != null
    // EFFECTS: restituisce la lista dei post appartenenti a ps tali che post.getAuthor.equals(username) dal piu' recente al piu' datato
    // THROWS: IllegalArgumentException se username.length == 0 || (NullPointerException se username == null || ps == null)
    // RETURNS: ArrayList<Post> wroteBy

    List<Post> containing(List<String> words);
    // REQUIRES: words != null
    // EFFECTS: restituisce una lista di post che contengono le parole cercate o parte di esse in ordine temporale
    // THROWS: NullPointerException se words == null
    // RETURNS: List<Post> contains

}
