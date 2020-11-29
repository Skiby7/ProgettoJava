package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;

import java.util.*;
import java.io.Console;

public class MicroBlog {

    public static void main(String args[]) throws SocialNetworkError{
        String benvenuto = "Benvenuto su MicroBlog!";
        System.out.println(benvenuto);
        Scanner scan = new Scanner(System.in);
        int choice;
        String user;
        String text;
        String login;
        char[] password;
        boolean disableAdmin = false;
        FamilyFriendlySocialNetwork network = new FamilyFriendlySocialNetwork();
        Console console = System.console();
        if (console == null){
            System.out.println("Non è possibile effettuare il login come Admin");
            disableAdmin = true;
        }
        do {

            System.out.println("Vuoi fare il Login [s/n]? ");
            login = scan.nextLine();
            while(!login.toLowerCase().equals("s") && !login.toLowerCase().equals("n")){
                System.out.println("S/n?");
                login = scan.nextLine();
            }
            choice = -1;
            if (login.toLowerCase().equals("s")) {
                System.out.print("Inserire nome Utente: ");
                user = scan.nextLine();
                if (user.equals("admin") && !disableAdmin) {
                    System.out.print("Inserire Password: ");
                    password = console.readPassword();
                    String pass = Arrays.toString(password);
                    if (pass.hashCode() == -1204903607) {
                        String benvenutoAdmin = "Benvenuto nel pannello di controllo, procedere con cautela.";
                        char[] bA = benvenutoAdmin.toCharArray();
                        for (char char_: bA){
                            try {
                                System.out.print(char_);
                                Thread.sleep(20);
                            }catch (InterruptedException ignored){}
                        }
                        try{
                            Thread.sleep(80);
                        }catch (InterruptedException ignored){}
                        System.out.println();
                        do {
                            int id; // Per ripristinare la segnalazione
                            String badWords;
                            System.out.println("1. Visualizza i post attivi 2. Visualizza i post segnalati\n\t3. Segnala un contenuto 4. Ripristina un post 5. Ripristina contenuti 0. Esci");
                            while (!scan.hasNextInt()) {
                                System.out.println("Inserisci un numero valido");
                                scan.next();
                            }
                            choice = scan.nextInt();
                            scan.nextLine();
                            switch (choice) {
                                case 1:
                                    network.printAllPosts();
                                    break;

                                case 2:
                                    network.printReportedPost();
                                    System.out.println("Lista degli ID bannati -> " + network.getReportedIds());
                                    break;

                                case 3:
                                    System.out.print("Inserisci l'ID del post (inserisci 0 per segnalare solo in base al contenuto): ");
                                    while (!scan.hasNextInt()) {
                                        System.out.println("Inserisci un numero valido");
                                        scan.next();
                                    }
                                    id = scan.nextInt();
                                    scan.nextLine();
                                    System.out.print("Inserisci le parole che vuoi segnalare separate da uno spazio (lasciare vuoto se non si vuole segnalare nessuna parola): ");
                                    badWords = scan.nextLine();
                                    if (!badWords.isBlank() && id != 0)
                                        try {
                                            network.reportPost(id, badWords);
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Id non valido");
                                        }


                                    else if (id == 0 && !badWords.isBlank())
                                        try {
                                            network.reportPostsByWord(badWords);
                                        } catch (IllegalArgumentException ignored) {
                                        }

                                    else if (id != 0 && badWords.isBlank())
                                        try {
                                            network.reportPost(id);
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Id non valido");
                                        }

                                    else
                                        System.out.println("Input non valido.");
                                    break;

                                case 4:
                                    System.out.print("Inserisci l'ID del post: ");
                                    while (!scan.hasNextInt()) {
                                        System.out.println("Inserisci un numero valido");
                                        scan.next();
                                    }
                                    id = scan.nextInt();
                                    scan.nextLine();
                                    try {
                                        network.removeFlag(id);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Id non valido");
                                    }
                                    break;

                                case 5:
                                    System.out.println("Le parole segnalate sono:");
                                    for (String bad: network.getBadWords())
                                        System.out.println("\033[3m" + bad + "\033[0m");
                                    System.out.println();
                                    System.out.print("Inserisci le parole da ripristinare separate da uno spazio: ");
                                    badWords = scan.nextLine();
                                    try {
                                        network.restoreWords(badWords);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Stringa non valida");
                                    }
                                    break;


                                default:
                                    if (choice == 0) {
                                        //scan.nextLine(); // I clean the buffer here
                                        break;
                                    }
                                    System.out.println("Inserisci un numero valido");
                            }

                        } while (choice != 0);

                    } else {
                        System.out.println("Password Errata");
                    }

                } else {
                    String benvenutoUser = "Ciao " + user + ", cosa vuoi fare?";
                    char[] bA = benvenutoUser.toCharArray();
                    for (char char_: bA){
                        try {
                            System.out.print(char_);
                            Thread.sleep(20);
                        }catch (InterruptedException ignored){}
                    }
                    try{
                        Thread.sleep(80);
                    }catch (InterruptedException ignored){}
                    System.out.println();
                    do {
                        System.out.println("1. Posta 2. Stampa tutti i post 3. Stampa tutti gli utenti iscritti\n" +
                                "\t4. Cerca post per utente 5. Cerca post per contenuto 6. Segui un post\n" +
                                "\t\t7. Stampa la rete sociale 8. Scopri gli Influencers 9. Segnala un contenuto inappropriato 0. Esci");
                        while (!scan.hasNextInt()) {
                            System.out.println("Inserisci un numero valido");
                            scan.next();
                        }
                        choice = scan.nextInt();
                        scan.nextLine();
                        switch (choice) {
                            case 1:
                                text = scan.nextLine();
                                try {
                                    network.addPost(user, text);
                                } catch (IllegalLengthException | EmptyTextException | IllegalArgumentException e) {
                                    System.out.println(e);
                                }

                                break;

                            case 2:
                                network.printAllPosts();

                                break;

                            case 3:
                                System.out.println("GLi utenti iscritti sono:");
                                for (String toPrint : network.getMentionedUser()) {
                                    System.out.println("\033[3m" + toPrint + "\033[0m");
                                }
                                System.out.println();
                                break;

                            case 4:
                                System.out.println("Inserisci l'utente da cercare (attenzione alle maiuscole): ");
                                String query = scan.nextLine();
                                try {
                                    for (Post postToPrint : network.writtenBy(query)){
                                        System.out.println("\n");
                                        postToPrint.printPost();
                                        System.out.println("\n");
                                    }
                                }catch (IllegalArgumentException e){System.out.println("Nome utente non valido");}
                                break;

                            case 5:
                                System.out.println("Inserisci le parole da cercare: ");
                                String tmp;
                                tmp = scan.nextLine();
                                String[] tokens = tmp.split(" ");
                                List<String> keywords = new LinkedList<>(Arrays.asList(tokens));
                                System.out.println();
                                for (Post postContaining : network.containing(keywords)) {
                                    System.out.println("\n");
                                    postContaining.printPost();
                                    System.out.println("\n");
                                }
                                break;

                            case 6:
                                System.out.print("Inserisci l'ID del post: ");
                                int id = scan.nextInt();
                                try {
                                    network.follow(id, user);
                                } catch (AutoFollowException | IllegalArgumentException Error) {
                                    System.out.println(Error);
                                }
                                break;

                            case 7:
                                network.printSocialNetwork();
                                break;

                            case 8:
                                for (String influencers : network.influencers())
                                    System.out.println(influencers);
                                break;

                            case 9:
                                String badWords;
                                System.out.print("Inserisci l'ID del post (inserisci 0 per segnalare solo in base al contenuto): ");
                                while (!scan.hasNextInt()) {
                                    System.out.println("Inserisci un numero valido");
                                    scan.next();
                                }
                                id = scan.nextInt();
                                scan.nextLine();
                                System.out.println("Inserisci le parole che vuoi segnalare separate da uno spazio (lasciare vuoto se non si vuole segnalare nessuna parola): ");
                                badWords = scan.nextLine();
                                if (!badWords.isBlank() && id != 0)
                                    try {
                                        network.reportPost(id, badWords);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Id non valido");
                                    }


                                else if (id == 0 && !badWords.isBlank())
                                    try {
                                        network.reportPostsByWord(badWords);
                                    } catch (IllegalArgumentException ignored) {
                                    }

                                else if (id != 0 && badWords.isBlank())
                                    try {
                                        network.reportPost(id);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Id non valido");
                                    }

                                else
                                    System.out.println("Input non valido.");
                                break;


                            default:
                                if (choice == 0) {
                                    //scan.nextLine(); // I clean the buffer here
                                    break;
                                }
                                System.out.println("Inserisci un numero valido");
                        }
                    } while (choice != 0);
                }
            }
            else
                break;
//            scan.nextLine(); // Cleans the buffer

       }while(true);

        System.out.println("Arrivederci!");
    }
}
