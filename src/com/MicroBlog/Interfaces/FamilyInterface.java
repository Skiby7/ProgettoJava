package com.MicroBlog.Interfaces;

import com.MicroBlog.CustomExceptions.AutoFollowException;
import com.MicroBlog.CustomExceptions.EmptyTextException;
import com.MicroBlog.CustomExceptions.IllegalLengthException;

import java.util.HashSet;

public interface FamilyInterface extends SocialInterface {
    @Override
    void addPost(String user, String text) throws IllegalLengthException, EmptyTextException, NullPointerException;
    // REQUIRES: user ≠ null ∧ text ≠ null ∧ text.length() ≤ 140 ∧ text.length() > 0.
    // EFFECTS: un nuovo post con post.getAuthor().equals(user) ∧ post.getText().equals(text) ∧ !post.getText().contains(badwords) viene aggiunto al Set dei post (this.postSet).
    // THROWS: EmptyTextException se text.isBlank() ∨ IllegalLengthException se text.length() > 140 ∨ IllegalArgumentException se user.isBlank().

    @Override
    void printAllPosts();
    // EFFECTS: stampa tutti i post scritti dagli utenti che rispettano le policy del socialnetwork.

    @Override
    void follow(int id, String user) throws AutoFollowException, IllegalArgumentException;
    // REQUIRES: id ≤ postSet.size() ∧ id > 0 ∧ !user.isBlank()
    // EFFECTS: l'utente user segue post t.c. post.getId() = id e post.getFlag() = true
    // THROWS: AutoFollowException se (id = post.getId ∧ user.equals(post.getAuthor)) ∨ IllegalArgumentException se user.isBlank().

    HashSet<String> getBadWords();
    // RETURNS: il Set delle parole bannate


    void reportPost(int id, String badWords) throws IllegalArgumentException;
    // REQUIRES: id ≤ postSet.size() ∧ id > 0 ∧ !badWords.isBlank()
    // EFFECTS: segnala il post con post.getId() = id e aggiunge badWords (dopo lo split) al Set this.badWords
    // THROWS: IllegalArgumentException se (id ≤ 0 ∨ id > super.postSet.size() ∨ badWords.isBlank())
    // MODIFIES: this

    void reportPostsByWord(String badWords) throws IllegalArgumentException;
    // REQUIRES: !badWords.isBlank()
    // EFFECTS: segnala tutti i posti che contengono le parole in badWords (è una stringa, ma viene eseguito lo split), le quali vengono aggiunte a this.badWords
    // THROWS: IllegalArgumentException se badWords.isBlank()
    // MODIFIES: this

    void removeFlag(int id) throws IllegalArgumentException;
    // REQUIRES: id ≤ postSet.size() ∧ id > 0
    // EFFECTS: ripristina il post con post.getId() = id
    // THROWS: IllegalArgumentExcepiton se (id ≤ 0 ∨ id > super.postSet.size())

    void restoreWords(String goodWords) throws IllegalArgumentException;
    // REQUIRES: !goodWords.isBlank()
    // EFFECTS: ripristina tutti i posti che contengono le parole in goodWords (che vengono rimosse da this.badWords) e non contengono le parole contenute in this.badWords.
    // THROWS: IllegalArgumentException se goodWords.isBlank()
    // MODIFIES: this
}

