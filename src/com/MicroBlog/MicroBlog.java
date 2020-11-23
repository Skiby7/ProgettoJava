package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.Interfaces.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MicroBlog {
    public static void main(String args[]) throws SocialNetworkError{
        String benvenuto = "Benvenuto su MicroBlog!";
        System.out.println(benvenuto);
        Scanner scan = new Scanner(System.in);
        int choice = -1;
        String user;
        String text;
        String login;
        SocialNetwork network = new SocialNetwork();
        do {
            System.out.println("Vuoi fare il Login [s/n]? ");
            login = scan.nextLine();
            while(!login.toLowerCase().equals("s") && !login.toLowerCase().equals("n")){
                System.out.println("S/n?");
                login = scan.nextLine();
            }

            if (login.equals("s")) {
                System.out.print("Inserire nome Utente: ");
                user = scan.nextLine();


                do {
                    System.out.println("Ciao " + user + ", cosa vuoi fare?");
                    System.out.println("1. Posta 2. Stampa tutti i post 3. Stampa tutti gli utenti iscritti\n" +
                            "\t4. Cerca post per utente 5. Cerca post per contenuto 6. Segui un post \n" +
                            "\t\t7. Stampa la rete sociale 8. Scopri gli Influencers 0. Esci");
                    while (!scan.hasNextInt()){
                        System.out.println("Inserisci un numero valido");
                        scan.next();
                    }
                    choice = scan.nextInt();
                    switch (choice) {
                        case 1:
                            scan.nextLine();
                            text = scan.nextLine();


                            try {
                                network.addPost(user, text);
                            }catch (IllegalLengthException | EmptyTextException e){
                                System.out.println(e);
                            }

                            break;
                        case 2:
                            network.printAllPosts();

                            break;

                        case 3:
                            for (String toPrint : network.getMentionedUser()) {
                                System.out.println(toPrint);
                            }
                            break;

                        case 4:
                            scan.nextLine();
                            for (Post postToPrint : network.writtenBy(scan.nextLine()))
                                postToPrint.printPost();
                            break;

                        case 5:
                            scan.nextLine();
                            System.out.println("Inserisci le parole da cercare:");
                            String tmp;
                            tmp = scan.nextLine();
                            String[] tokens = tmp.split(" ");
                            List<String> keywords = new LinkedList<>(Arrays.asList(tokens));
                            System.out.println();
                            for (Post postContaining : network.containing(keywords)) {
                                postContaining.printPost();
                                System.out.println();
                            }
                            System.out.println("_________________________");
                            break;


                        case 6:
                            scan.nextLine();
                            System.out.print("Inserisci l'ID del post: ");
                            int id = scan.nextInt();
                            try {
                                network.follow(id, user);
                            } catch (AutoFollowException Error){
                                System.out.println(Error);
                            }
                            break;

                        case 7:
                            network.printSocialNetwork();
                            break;

                        case 8:
                            List<String> influencers = new LinkedList<>(network.influencers());
                            for (String infl : influencers)
                                System.out.println(infl);
                            break;

                        default:
                            if (choice == 0)
                                break;
                            System.out.println("Inserisci un numero valido");


                    }
                } while (choice != 0);


            }

            else
                break;
            scan.nextLine();

       }while(true);
        FamilyFriendlySocialNetwork ffnetwork = new FamilyFriendlySocialNetwork();
        try {
            ffnetwork.addPost("autore", "testo");

        } catch (IllegalLengthException | EmptyTextException e){
            System.out.println(e);
        }
        ffnetwork.printAllPosts();

        System.out.println("Arrivederci!");
    }
}
