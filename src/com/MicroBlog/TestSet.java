//package com.MicroBlog;
//
//import com.MicroBlog.CustomExceptions.*;
//
//import java.util.*;
//
//public class TestSet {
//    public static void main(String args[]){
//        String benvenuto = "Benvenuto su MicroBlog!";
//        System.out.println(benvenuto);
//        int post_counter = 0;
//        SocialNetwork network = new SocialNetwork();
//        String[] users = {"Roberto", "Leonardo", "Fernando", "Giacomo","Lorenzo", "Giulia","Luisa","Elena","Erica", "Francesca"};
//        List<Post> postList = new LinkedList<>();
//        List<String> words = new LinkedList<>();
//        words.add("force");
//        words.add("back");
//        words.add("conf");
//        words.add("Leonardo");
//        words.add("It's");
//
//
//        for (String user: users){
//            for (int i = 0; i < 5; i++){
//                try {
//                    String toAdd = "Ciao, sono " + user + " e questo e' il post numero " + i;
//                    Post newPost = new Post(user, toAdd, ++post_counter);
//                    network.addPost(newPost);
//                    if (i > 1 && i < 4 && (user.equals("Luisa") || user.equals("Edoardo") || user.equals("Leonardo")))
//                        postList.add(newPost);
//                }catch (IllegalLengthException | EmptyTextException error){
//                    System.out.println(error);
//                }
//            }
//        }
//
//        try {
//            Post newPost = new Post("Rocky", "Adrianaaa!!!", ++post_counter);
//            network.addPost(newPost);
//        }catch (IllegalLengthException | EmptyTextException error){
//            System.out.println(error);
//        }
//
//
//        try {
//            Post newPost = new Post("Mario", "It's me, Mario!", ++post_counter);
//            network.addPost(newPost);
//        }catch (IllegalLengthException | EmptyTextException error){
//            System.out.println(error);
//        }
//
//        try {
//            Post newPost = new Post("Pacman", "echo \"ILoveCandy\" >> /etc/pacman.conf", ++post_counter);
//            network.addPost(newPost);
//            postList.add(newPost);
//        }catch (IllegalLengthException | EmptyTextException error){
//            System.out.println(error);
//        }
//        for (String user: users)
//            try {
//                network.follow(post_counter, user);
//            }catch (AutoFollowException error){
//                System.out.println(error);
//            }
//
//
//
//        try {
//            Post newPost = new Post("Yoda", "May the force be with you", ++post_counter);
//            network.addPost(newPost);
//            postList.add(newPost);
//        }catch (IllegalLengthException | EmptyTextException error){
//            System.out.println(error);
//        }
//
//        try {
//            Post newPost = new Post("Ratchet & Clank", "We're back!", ++post_counter);
//            network.addPost(newPost);
//        }catch (IllegalLengthException | EmptyTextException error){
//            System.out.println(error);
//        }
//
//        System.out.println("Creo una rete sociale contenente gli utenti: " + Arrays.toString(users));
//        System.out.println("Ogni utente fra questi posta la frase \"Ciao, sono 'user' e questo e' il post numero 'i' per 5 volte");
//        System.out.println("Aggiungo poi altri 5 utenti(Yoda, Pacman, Ratchet & Clank, Rocky e Mario)\n i quali creano un post ciascuno e interagiscono con gli altri utenti.");
//
//
//        for (int i = 1; i < 51; i++){
//            if(i >= 6 && i <= 10)
//                try {
//                    network.follow(i, "Roberto");
//                    network.follow(i, "Fernando");
//                    network.follow(i, "Giacomo");
//                    network.follow(i, "Elena");
//                    network.follow(i, "Luisa");
//                    network.follow(i,"Pacman");
//                    network.follow(i,"Ratchet & Clank");
//                    network.follow(i,"Yoda");
//                }catch (AutoFollowException Error){
//                    System.out.println(Error);
//                }
//            if (i>=36 && i<= 40)
//                try {
//                    network.follow(i, "Roberto");
//                    network.follow(i, "Fernando");
//                    network.follow(i, "Giacomo");
//                    network.follow(i, "Luisa");
//                    network.follow(i,"Rocky");
//                }catch (AutoFollowException Error){
//                    System.out.println(Error);
//                }
//            if (i>=11 && i<= 15)
//                try {
//                    network.follow(i, "Roberto");
//                    network.follow(i, "Giacomo");
//                    network.follow(i, "Luisa");
//                }catch (AutoFollowException Error){
//                    System.out.println(Error);
//                }
//            if (i<= 5)
//                try {
//                    network.follow(i, "Giacomo");
//                    network.follow(i, "Luisa");
//                }catch (AutoFollowException Error){
//                    System.out.println(Error);
//                }
//
//        }
//        for (String user: users)
//            try {
//                network.follow(53, user);
//            }catch (AutoFollowException error){
//                System.out.println(error);
//            }
//
//        System.out.println("Adesso alcuni utenti seguono i post di altri");
//        System.out.println("\nGli influencers sono:");
//        for (String toPrint: network.influencers(network.guessFollowers(postList))){
//            System.out.println(" - " + toPrint);
//
//        }
//
//        System.out.println("\n\n");
//        System.out.println("\nGli utenti iscritti sono:");
//        for (String toPrint: network.getMentionedUser())
//            System.out.println(" - " + toPrint);
//        System.out.println("\n\n");
//        System.out.println("\nGli utenti che hanno postato nella lista postList sono:");
//        for (String toPrint: network.getMentionedUser(postList))
//            System.out.println(" - " + toPrint);
//        System.out.println("\n\n");
//        System.out.println("\nI post scritti da Yoda sono:");
//        for (Post postToPrint : network.writtenBy("Yoda"))
//            postToPrint.printPost();
//        System.out.println("\n\n");
//        System.out.println("I post scritti da Luisa ed Leonardo dentro postList sono:");
//        for (Post postToPrint: network.writtenBy(postList,"Luisa")) {
//            System.out.println();
//            postToPrint.printPost();
//        }
//        for (Post postToPrint: network.writtenBy(postList, "Leonardo")) {
//            System.out.println();
//            postToPrint.printPost();
//        }
//        System.out.println("\n\n");
//        System.out.println("I post che contengono le parole " + words + " sono:");
//        for (Post postToPrint: network.containing(words)) {
//            System.out.println();
//            postToPrint.printPost();
//        }
//        String prova = null;
//        try {
//            network.addPost(null);
//            System.out.println("qui");
//        }catch (IllegalLengthException e){
//
//            System.out.println(e);
//        }catch (EmptyTextException e) {
//            e.printStackTrace();
//        }
//        Post provanull = null;
//        try{
//            network.addPost(provanull);
//        }catch (IllegalLengthException | EmptyTextException e){
//            System.out.println(e);
//        }
//    }
//
//}
