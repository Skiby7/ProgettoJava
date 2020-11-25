package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.Interfaces.FamilyInterface;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;


public class FamilyFriendlySocialNetwork extends SocialNetwork implements FamilyInterface {

    private final HashSet<String> badWords;
    private final TreeSet<Integer> reportedId;
    public FamilyFriendlySocialNetwork(){
        this.badWords = new HashSet<>();
        this.reportedId = new TreeSet<>();
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

    @Override
    public void follow(int id, String user) throws AutoFollowException, IllegalArgumentException {
        if (user.isBlank() || id <= 0 || id > this.postSet.size())
            throw new IllegalArgumentException("L'username o ID non valido.");

        if (!this.reportedId.contains(id))
            super.follow(id, user);
        else
            System.out.println("Il post è stato segnalato, attualmente non è possibile interagirci.");
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

    public void reportPost(int id, String badWords) throws IllegalArgumentException{
        if (id <= 0 || id > super.postSet.size() || badWords.isBlank())
            throw new IllegalArgumentException("Input errato");
        String[] toFilter = badWords.split("[^a-zA-Z]+");
        this.badWords.addAll(Arrays.asList(toFilter));
        for (Post post: super.postSet){
            if (post.getId() == id) {
                post.switchFamilyFriendlyOff();
                this.reportedId.add(post.getId());
                return;
            }
        }
        System.out.println("Post segnalato con successo");
    }

    public void reportPostsByWord(String badWords) throws IllegalArgumentException{
        if (badWords.isBlank())
            throw new IllegalArgumentException();
        String[] toFilter = badWords.split("[^a-zA-Z]+");
        this.badWords.addAll(Arrays.asList(toFilter));
        String[] parsedText;
        for (Post post: super.postSet){
            parsedText = post.getText().split("[^a-zA-Z]+");
            for (String word: this.badWords){
                for (String textWord: parsedText){
                    if (word.toLowerCase().equals(textWord.toLowerCase())){
                        post.switchFamilyFriendlyOff();
                        this.reportedId.add(post.getId());
                    }

                }
            }

        }
        System.out.println("La lista dei post è stata aggiornata.");
    }

    public void removeFlag(int id){
        for (Post post: super.postSet){
            if (post.getId() == id){
                post.switchFamilyFriendlyOn();
                this.reportedId.remove(post.getId());
                return;
            }
        }
        System.out.println("Post non trovato.");
    }
}
