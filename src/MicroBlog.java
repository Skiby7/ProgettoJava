import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MicroBlog {
    public static void main(String args[]){
        String benvenuto = "Benvenuto su MicroBlog!";
        System.out.println(benvenuto);
        int post_counter = 0;

        Scanner scan = new Scanner(System.in);
        int choice = -1;
        String user;
        String text;
        SocialNetwork network = new SocialNetwork();
        do {

            choice = -1;
            System.out.println("Vuoi fare il Login [s/n]? ");

            if (scan.nextLine().equals("s")){
                System.out.print("Inserire nome Utente: ");
                user = scan.nextLine();

                do {
                    System.out.println("Ciao " + user + ", cosa vuoi fare?");
                    System.out.println("1. Posta 2. Stampa tutti i post 3. Stampa tutti gli utenti iscritti\n 4. Cerca post per utente 5. Cerca post per contenuto 6. Segui un post 0. Esci");
                    choice = scan.nextInt();
                    switch (choice) {
                        case 1:
                            scan.nextLine();
                            text = scan.nextLine();
                            while (text.length() > 140) {
                                System.out.println("Il testo contiene piu' di 140 caratteri");
                                text = scan.nextLine();
                            }
                            Post newPost = new Post(user, text, ++post_counter);
                            network.addPost(user, newPost);
                            break;
                        case 2:
                            network.printAllPosts();

                            break;

                        case 3:
                            for (String toPrint: network.getMentionedUser()) {
                                System.out.println(toPrint);
                            }
                            break;

                        case 4:
                            scan.nextLine();
                            for (Post postToPrint: network.writtenBy(scan.nextLine()))
                                postToPrint.printPost();
                            break;

                        case 5:
//                            scan.nextLine();
//                            System.out.println("Inserisci le parole da cercare:");
//                            String tmp;
//                            List<String> keywords = new LinkedList<>();
//                            tmp = scan.nextLine();
//                            String[] tokens = tmp.split(" ");
//                            for(String token: tokens){
//                                keywords.add(token);
//                            }
//                            if (keywords.size() == 1){
//                                System.out.println();
//                                System.out.println("Ecco i post che ho trovato:");
//                                for(Post postContaining: network.containing(keywords.get(0))){
//                                    postContaining.printPost();
//                                    System.out.println();
//                                }
//                                System.out.println("_________________________");
//                                break;
//                            }
//                            System.out.println();
//                            System.out.println("Ecco i post che ho trovato:");
//                            for(Post postContaining: network.containing(keywords)){
//                                postContaining.printPost();
//                                System.out.println();
//                            }
//                            System.out.println("_________________________");
//                            break;

                        case 6:
                            scan.nextLine();
                            System.out.print("Inserisci l'ID del post: ");
                            int id = scan.nextInt();
                            network.follow(id, user);
                            break;

                        case 7:
                            network.printSocialNetwork();
                            break;

                        case 8:
                            List<String> inluencers = network.influencers();
                            for (String infl: inluencers)
                                System.out.println(infl);
                            break;



                    }
                }while (choice!=0);

            }

            else
                break;

            scan.nextLine();
       }while(true);
        System.out.println("Arrivederci!");
    }
}
