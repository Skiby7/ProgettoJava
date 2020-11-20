package com.MicroBlog.Interfaces;
import java.sql.Timestamp;
import java.util.HashSet;


public interface PostInterface {
    // Overview: tipo modificabile che implementa il dato Post

    String getAuthor();
    // EFFECTS:restituisce l'autore del post
    int getId();
    // EFFECTS: restituisce l'ID del post
    Timestamp getTime();
    // EFFECTS: restituisce il timestamp del post

    void addFollow(String follower);
    // REQUIRES: follower != null
    // EFFECTS: aggiunge una stringa al Set di followers
    // MODIFIES: this
    HashSet<String> getFollowers();
    // EFFECTS: restituisce un Set contenente le persone che seguono il post, se nessuno segue il post restuisce un Set vuoto

    void printPost();
    // EFFECTS: stampa il post formattato



}
