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
    private HashSet<String> followers;

    public Post(String author, String text, int id){

        this.author = author; // Autore
        this.text = text; // Corpo del post
        this.id = id; // Id del post
        this.time = new Timestamp(System.currentTimeMillis()); // Data e ora del post
        this.followers = new HashSet<String>(); // Lista di follower a cui piace il post
    }

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
    public void addFollow(String follower){
        this.followers.add(follower);
    }

    public HashSet<String> getFollowers(){
        return this.followers;
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
    }
}
