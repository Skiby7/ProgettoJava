package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.Interfaces.FamilyInterface;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FamilyFriendlySocialNetwork extends SocialNetwork implements FamilyInterface {

    HashSet<String> badWords = new HashSet<>();

    @Override
    public void addPost(String user, String text) throws IllegalLengthException, EmptyTextException, NullPointerException {

        String[] toFilter = text.split("[^A-Za-z]");
        for (String filter: toFilter){
            for (String word: this.badWords){
                if (filter.equals(word)) {
                    System.out.println("Il contenuto del post non è adatto a Microblog!");
                    return;
                }
            }
        }

        super.addPost(user, text);
    }
    public HashSet<String> getBadWords(){
        return this.badWords;
    }
    public void addBadWords(String badWord){
        this.badWords.add(badWord);
    }

    @Override
    public void printAllPosts() {
        for (Post post: super.postSet){
            if (post.getFlag())
                post.printPost();
        }
    }
    public void reportPost(int ID, String[] badWords){
        for (Post post: super.postSet){
            if (post.getId() == ID) {
                post.switchFamilyFriendlyOff();
                return;
            }
        }
        this.badWords.addAll(Arrays.asList(badWords));


    }
}
