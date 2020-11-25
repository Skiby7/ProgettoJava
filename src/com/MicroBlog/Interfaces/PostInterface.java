package com.MicroBlog.Interfaces;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public interface PostInterface {
    // Overview: tipo modificabile che implementa il dato Post

    String getAuthor();
    // RETURNS:restituisce l'autore del post
    String getText();
    // RETURNS: restituisce il testo del post
    int getId();
    // RETURNS: restituisce l'ID del post
    Timestamp getTime();
    // RETURNS: restituisce il timestamp del post
    boolean getFlag();
    // RETURNS: restituisce true se il contenuto non è stato segnaleto, altrimenti restituisce false
    HashSet<String> getFollowers();
    // RETURNS: restituisce un Set contenente le persone che seguono il post

    void addFollow(String follower)throws IllegalArgumentException;
    // REQUIRES: !follower.isBlank()
    // EFFECTS: aggiunge una stringa al Set di followers
    // MODIFIES: this
    // THROWS: IllegalArgumentException se follower.isBlank()


    void printPost();
    // EFFECTS: stampa il post formattato


    void switchFamilyFriendlyOff();
    // EFFECTS: segnala il contenuto
    // MODIFIES: this

    void switchFamilyFriendlyOn();
    // EFFECTS: ripristina il contenuto
    // MODIFIES: this
}
