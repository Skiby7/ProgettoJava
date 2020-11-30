package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;

import java.util.*;
import java.io.Console;

public class MicroBlog {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void fillNetwork(FamilyFriendlySocialNetwork MicroBlog){
        String[] usernames = {"Alessia",  "Enrico",  "Alessandra",  "Rossana", "Carlo", "Simone", "Anna", "Fabiano", "Arianna", "Lorenzo"};
        String[] post = {"\"Computer viruses are an urban legend.\"\n- Peter Norton, 1988", "\"Life is too short to run proprietary software.\"\n- Bdale Garbee", "\"I don’t know what the language of the year 2000 will look like, but I know it will be called Fortran.\"\n– CA Hoare, 1982", "\"Any fool can use a computer.  Many do.\"\n- Ted Nelson", "\"Men are from Mars. Women are from Venus. Computers are from hell.\"", "\"Where is the ‘any’ key?\" \n– Homer Simpson, in response to the message, \"Press any key\"", "\"Looking at code you wrote more than two weeks ago is like looking at code you are seeing for the first time.\"\n– Dan Hurvitz", "\"I don’t care if it works on your machine!  We are not shipping your machine!\"\n- Vidiu Platon", "\"If people never did silly things, nothing intelligent would ever get done.\" \n– Ludwig Wittgenstein", "The world we have created is a product of our thinking; it cannot be changed without changing our thinking.\"\n- Albert Einstein", "\"The central enemy of reliability is complexity.\"\n- Geer et al.", "\"Most of you are familiar with the virtues of a programmer.  There are three, of course: laziness, impatience, and hubris.\"\n- Larry Wall", "\"It does not happen all at once. There is no instant pudding.\"\n- W. Edwards Deming", "\"Good specifications will always improve programmer productivity far better than any programming tool or technique.\"\n- Milt Bryce", "\"In C++ it’s harder to shoot yourself in the foot, but when you do, you blow off your whole leg.\"\n- Bjarne Stroustrup", "He who rejects change is the architect of decay.  The only human institution which rejects progress is the cemetery.\"\n- Harold Wilson", "\"If you think your users are idiots, only idiots will use it.\"\n– Linus Torvalds", "\"Dogs have Owners, Cats have Staff.\"", "\"Good programmers use their brains, but good guidelines save us having to think out every case.\"\n- Francis Glassborow", "\"In the future, computers may weigh no more than 1.5 tonnes.\"\n– Popular mechanics, 1949"};
        int i = 0;
        for (String user: usernames){
            try {
                if(i == 20)
                    break;
                MicroBlog.addPost(user, post[i]);
                i++;
            } catch (IllegalLengthException | EmptyTextException e) {
                System.out.println(e);
            }
        }
        try {
            MicroBlog.follow(10,usernames[0]);
        } catch (AutoFollowException ignored) {
        }

        for (int j = 0;j<usernames.length-1;j++){
            try {
                MicroBlog.follow(j+1,usernames[j+1]);
            } catch (AutoFollowException ignored) {
            }
        }

        for (int j = 0; j < 3; j++){
            try {
                MicroBlog.follow(4, usernames[j]);
            } catch (AutoFollowException ignored) {

            }
        }
        for (int j = 4; j < 7; j++){
            try {
                MicroBlog.follow(8, usernames[j]);
            } catch (AutoFollowException ignored) {

            }
        }
        for (int j = 8; j < 10; j++){
            try {
                MicroBlog.follow(6, usernames[j]);
            } catch (AutoFollowException ignored) {

            }
        }
        try {
            MicroBlog.follow(6, usernames[0]);
        } catch (AutoFollowException ignored) {

        }
    }
    public static void main(String[] args) {
        clearScreen();
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
        fillNetwork(network);
        Console console = System.console();
        //        ======================== Inizio programma ========================
        if (console == null){
            System.out.println("Non è possibile effettuare il login come Admin");
            disableAdmin = true;
        }
        //        ======================== Login ========================
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
                            Thread.sleep(700);
                        }catch (InterruptedException ignored){}
                        System.out.println();
                        //        ======================== Pannello di controllo Admin ========================
                        do {
                            clearScreen();
                            int id; // Per ripristinare la segnalazione
                            String badWords;
                            System.out.println("\033[1mCi sono " + network.getReportedIds().size() + " post segnalati\033[0m" );
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
                                    System.out.print("\033[3mPremi invio per continuare\033[0m");
                                    scan.nextLine();
                                    break;

                                case 2:
                                    network.printReportedPost();
                                    System.out.println("\033[3mLista degli ID bannati -> " + network.getReportedIds() + "\033[0m");
                                    System.out.println();
                                    System.out.print("\033[3mPremi invio per continuare\033[0m");
                                    scan.nextLine();
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
                                    System.out.print("\033[3mPremi invio per continuare\033[0m");
                                    scan.nextLine();
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
                                    System.out.print("\033[3mPremi invio per continuare\033[0m");
                                    scan.nextLine();
                                    break;

                                case 5:
                                    System.out.println("Le parole segnalate sono:");
                                    for (String bad: network.getBadWords())
                                        System.out.println("\033[3m- " + bad + "\033[0m");
                                    System.out.println();
                                    System.out.print("Inserisci le parole da ripristinare separate da uno spazio: ");
                                    badWords = scan.nextLine();
                                    try {
                                        network.restoreWords(badWords);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Stringa non valida");
                                    }
                                    System.out.print("\033[3mPremi invio per continuare\033[0m");
                                    scan.nextLine();
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
                    String benvenutoUser = "Benvenuto " + user + "!";
                    char[] bA = benvenutoUser.toCharArray();
                    for (char char_: bA){
                        try {
                            System.out.print(char_);
                            Thread.sleep(20);
                        }catch (InterruptedException ignored){}
                    }
                    try{
                        Thread.sleep(700);
                    }catch (InterruptedException ignored){}
                    System.out.println();
                    //        ======================== Menu utente ========================
                    do {
                        clearScreen();
                        System.out.println("Utente loggato: \033[1m " + user + "\033[0m");
                        System.out.println("1. Crea un post 2. Stampa tutti i post 3. Stampa tutti gli utenti iscritti\n" +
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
                                System.out.print("Inserisci il testo del post (massimo 140 caratteri): ");
                                text = scan.nextLine();
                                try {
                                    network.addPost(user, text);
                                } catch (IllegalLengthException | EmptyTextException | IllegalArgumentException e) {
                                    System.out.println(e);
                                    System.out.print("\033[3mPremi invio per continuare\033[0m");
                                    scan.nextLine();

                                }
                                break;

                            case 2:
                                network.printAllPosts();
                                System.out.print("\033[3mPremi invio per continuare\033[0m");
                                scan.nextLine();
                                break;

                            case 3:
                                System.out.println("Gli utenti iscritti sono:");
                                for (String toPrint : network.getMentionedUser()) {
                                    System.out.println("\033[3m- " + toPrint + "\033[0m");
                                }
                                System.out.println();
                                System.out.print("\033[3mPremi invio per continuare\033[0m");
                                scan.nextLine();
                                break;

                            case 4:
                                System.out.print("Inserisci l'utente da cercare (attenzione alle maiuscole): ");
                                String query = scan.nextLine();
                                try {
                                    for (Post postToPrint : network.writtenBy(query)){
                                        System.out.println("\n");
                                        postToPrint.printPost();
                                        System.out.println("\n");
                                    }
                                }catch (IllegalArgumentException e){
                                    System.out.println("Nome utente non valido");
                                }
                                System.out.print("\033[3mPremi invio per continuare\033[0m");
                                scan.nextLine();
                                break;

                            case 5:
                                System.out.print("Inserisci le parole da cercare: ");
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
                                System.out.print("\033[3mPremi invio per continuare\033[0m");
                                scan.nextLine();
                                break;

                            case 6:
                                System.out.print("Inserisci l'ID del post: ");
                                int id = scan.nextInt();
                                try {
                                    network.follow(id, user);
                                } catch (AutoFollowException | IllegalArgumentException Error) {
                                    System.out.println(Error);
                                    System.out.print("\033[3mPremi invio per continuare\033[0m");
                                    scan.nextLine();
                                }
                                System.out.print("\033[3mPremi invio per continuare\033[0m");
                                scan.nextLine();
                                break;

                            case 7:
                                network.printSocialNetwork();
                                System.out.println();
                                System.out.print("\033[3mPremi invio per continuare\033[0m");
                                scan.nextLine();
                                break;

                            case 8:
                                System.out.println("Gli Influencers sono: ");
                                for (String influencers : network.influencers())
                                    System.out.println("\033[3m- " + influencers + "\033[0m");
                                System.out.println();
                                System.out.print("\033[3mPremi invio per continuare\033[0m");
                                scan.nextLine();
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
                                System.out.print("\033[3mPremi invio per continuare\033[0m");
                                scan.nextLine();
                                break;


                            default:
                                if (choice == 0) {
                                    //scan.nextLine(); // I clean the buffer here
                                    break;
                                }
                                System.out.println("Inserisci un numero valido");
                        }
                    } while (choice != 0);
                    clearScreen();
                }
            }
            else
                break;
       }while(true);

        System.out.println("Arrivederci!");
        clearScreen();
    }
}
