package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.Interfaces.*;
import java.sql.Timestamp;
import java.util.HashSet;


public class Post implements PostInterface, Comparable<Post>{
    private final int id;
    private final String author;
    private final String text;
    private final Timestamp time;
    private final HashSet<String> followers;
    private boolean familyFriendly;

    public Post(String author, String text, int id){
        if (id < 0)
            throw new IllegalArgumentException("L'id deve essere maggiore di 0.");
        if (author == null || text == null)
            throw new NullPointerException();

        this.author = author; // Autore
        this.text = text; // Corpo del post
        this.id = id; // Id del post
        this.time = new Timestamp(System.currentTimeMillis()); // Data e ora del post
        this.followers = new HashSet<String>(); // Lista di follower a cui piace il post
        this.familyFriendly = true;
    }
    // REQUIRES: author !=  null && text != null && id >= 0
    // THROWS: IllegalArgumentException se id < 0 || NullPointerException se (author == null || text == null)
    public String getAuthor(){
        return this.author;
    }

    public String getText(){
        return this.text;
    }
    public int getId(){
        return this.id;
    }
    public Timestamp getTime(){
        return this.time;
    }
    public boolean getFlag(){
        return this.familyFriendly;
    }
    public void addFollow(String follower){
        this.followers.add(follower);
    }

    public HashSet<String> getFollowers(){ return this.followers; }

    public void switchFamilyFriendlyOff(){
        this.familyFriendly = false;
    }

    public void switchFamilyFriendlyOn(){
        this.familyFriendly = false;
    }


    public void printPost(){
        System.out.println("Autore: " + this.getAuthor() + "\tPost ID:" + this.getId() + "\n----------");
        System.out.println(this.getText());
        System.out.println("Data e ora del post: " + this.getTime().toString());
        if (this.followers.size()!=0) {
            System.out.print("----------\nQuesto post e' seguito da: ");
            for (String follower : followers) {
                System.out.print(follower + "   ");
            }
            System.out.println();
        }
    }

    @Override
    public int compareTo(Post post) {
        return post.getId()-this.getId();
    } // override della funzione compareTo per ordinare secondo l'id de post

}
