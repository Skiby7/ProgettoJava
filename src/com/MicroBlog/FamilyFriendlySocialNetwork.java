package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.Interfaces.FamilyInterface;

import java.util.*;


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

    @Override
    public Set<Post> getPostSet() {
        Set<Post> allowedSet = new TreeSet<>();
        for (Post post: super.postSet)
            if (!reportedId.contains(post.getId()))
                allowedSet.add(post);

        return allowedSet;
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

    @Override
    public List<Post> writtenBy(String username) throws IllegalArgumentException {
        if (username.isBlank())
            throw new IllegalArgumentException();
        return writtenBy(new LinkedList<>(super.postSet), username);
    }

    @Override
    public List<Post> writtenBy(List<Post> ps, String username) throws IllegalArgumentException, NullPointerException {
        if (username.length() == 0)
            throw new IllegalArgumentException("Username non valido");

        List<Post> wroteBy = new ArrayList<>();
        for (Post post: ps)
            if (post.getAuthor().equals(username) && post.getFlag())
                wroteBy.add(post);

        if (wroteBy.isEmpty()){
            System.out.println("Nessun post trovato :(");
            return wroteBy;
        }
        Collections.sort(wroteBy); // Ordino in ordine cronologico inverso i post (dal più recente al più vecchio)
        return wroteBy;
    }

    @Override
    public List<Post> containing(List<String> words) throws NullPointerException, IllegalArgumentException {
        List<Post> contains = new LinkedList<>();
        String[] parsedText;
        boolean found = false;
        for (Post toScan : this.postSet) { // Per ogni post guardo se ogni parola fosse contenuta nella lista di post, anche se parzialmente
            found = false;
            for (String word : words) {
                if (word.isBlank())
                    throw new IllegalArgumentException("Stringa non valida");
                parsedText = toScan.getText().split("[^a-zA-Z]+");
                for (String parsedWord: parsedText) {
                    if (parsedWord.toLowerCase().matches(word.toLowerCase() + "[a-z]*")) {
                        if (toScan.getFlag())
                            contains.add(toScan);
                        found = true;
                        break;
                    }
                }
                if (found)
                    break;

            }
        }
        if (contains.isEmpty()) {
            System.out.println("Non ho trovato risultati :(");
            return contains;
        }
        return contains;
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
        reportPostsByWord(badWords);
        String[] toFilter = badWords.split("[^a-zA-Z]+");
        for (Post post: super.postSet){
            if (post.getId() == id && post.getFlag()) {
                post.setFamilyFriendlyOff();
                this.reportedId.add(post.getId());
                return;
            }
            else if (post.getId() == id && !post.getFlag())
                return;
        }
    }

    public void reportPost(int id) throws IllegalArgumentException{
        if (id <= 0 || id > super.postSet.size())
            throw new IllegalArgumentException("Input errato");
        for (Post post: super.postSet){
            if (post.getId() == id) {
                post.setFamilyFriendlyOff();
                this.reportedId.add(post.getId());
                return;
            }
        }
    }


    public void reportPostsByWord(String badWords) throws IllegalArgumentException{
        if (badWords.isBlank())
            throw new IllegalArgumentException("Stringa non valida");
        String[] toFilter = badWords.split("[^a-zA-Z]+");
        boolean found;
        this.badWords.addAll(Arrays.asList(toFilter));
        String[] parsedText;
        for (Post post: super.postSet){
            found = false;
            parsedText = post.getText().split("[^a-zA-Z]+");
            for (String word: toFilter){
                for (String textWord: parsedText){
                    if (word.toLowerCase().equals(textWord.toLowerCase())){
                        found = true;

                        post.setFamilyFriendlyOff();
                        this.reportedId.add(post.getId());
                    }

                }
                if (found)
                    break;
            }

        }
    }

    public void restoreWords(String goodWords){
        if (goodWords.isBlank())
            throw new IllegalArgumentException("Stringa non valida");
        String[] toRestore = goodWords.split("[^a-zA-Z]+");
        this.badWords.removeAll(Arrays.asList(toRestore));
        boolean notYet;
        String[] parsedText;
        for (Post post: super.postSet){
            notYet = false;
            parsedText = post.getText().split("[^a-zA-Z]+");
            for (String word: this.badWords){
                for (String textWord: parsedText){
                    if (textWord.toLowerCase().equals(word.toLowerCase())) {
                        notYet = true;
                        break;
                    }
                }
                if (!notYet){
                    post.setFamilyFriendlyOn();
                    this.reportedId.remove(post.getId());
                }
            }

        }



    }

    public void removeFlag(int id) throws IllegalArgumentException{
        if (id > super.postSet.size() || id <= 0)
            throw new IllegalArgumentException("ID non valido");
        for (Post post: super.postSet){
            if (post.getId() == id){
                if (post.getFlag()){
                    System.out.println("Il post non era stato segnalato");
                    return;
                }
                post.setFamilyFriendlyOn();
                this.reportedId.remove(post.getId());
                return;
            }
        }
    }

    public TreeSet<Integer> getReportedIds(){
        return this.reportedId;
    }
}
