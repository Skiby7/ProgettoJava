package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.Interfaces.FamilyInterface;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;


public class FamilyFriendlySocialNetwork extends SocialNetwork implements FamilyInterface {

    private final HashSet<String> badWords;
    public FamilyFriendlySocialNetwork(){
        this.badWords = new HashSet<>();
    }

    @Override
    public void addPost(String user, String text) throws IllegalLengthException, EmptyTextException, NullPointerException {
        String[] toFilter = text.split("[^a-zA-Z]+"); // Divido il testo in parole
        for (String filter: toFilter){
            for (String word: this.badWords){
                if (filter.toLowerCase().equals(word.toLowerCase())) { // Per ogni parola controllo che non sia contenuta della lista di parole proibite
                    System.out.println("Il contenuto del post non è adatto a Microblog!");
                    return;
                }
            }
        }

        super.addPost(user, text); // Se tutto va bene aggiungo il post
    }
    public HashSet<String> getBadWords(){
        return this.badWords;
    }

    @Override
    public void printAllPosts() {
        for (Post post: super.postSet){
            if (post.getFlag()){ // Controllo che il post non sia stato flaggato come inappropriato
                System.out.println("\n");
                post.printPost();
                System.out.println("\n");
            }
        }
    }

    public void printReportedPost(){
        for (Post post: super.postSet){
            if (!post.getFlag()){
                System.out.println("\n");
                post.printPost();
                System.out.println("\n");
            }
        }
    }



    public void reportPost(int id, String badWords){
        String[] toFilter = badWords.split("[^a-zA-Z]+");
        this.badWords.addAll(Arrays.asList(toFilter));
        for (Post post: super.postSet){
            if (post.getId() == id) {
                post.switchFamilyFriendlyOff();
                return;
            }
        }
        this.badWords.addAll(Arrays.asList(badWords));
        System.out.println("Post segnalato con successo");
    }
    public void reportPostsByWord(String badWords){
        String[] toFilter = badWords.split("[^a-zA-Z]+");
        this.badWords.addAll(Arrays.asList(toFilter));
        String[] parsedText;
        for (Post post: super.postSet){
            parsedText = post.getText().split("[^a-zA-Z]+");
            for (String word: this.badWords){
                for (String textWord: parsedText){
                    if (word.toLowerCase().equals(textWord.toLowerCase())) post.switchFamilyFriendlyOff();

                }
            }

        }
        System.out.println("La lista dei post e' stata aggiornata.");
    }

    public void removeFlag(int id){
        for (Post post: super.postSet){
            if (post.getId() == id){
                post.switchFamilyFriendlyOn();
                return;
            }
        }
        System.out.println("Post non trovato.");
    }


}
