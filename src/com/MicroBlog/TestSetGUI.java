package com.MicroBlog;

import com.MicroBlog.CustomExceptions.AutoFollowException;
import com.MicroBlog.CustomExceptions.EmptyTextException;
import com.MicroBlog.CustomExceptions.IllegalLengthException;

import java.io.Console;
import java.io.PrintStream;
import java.util.*;

public class TestSetGUI {
    public TestSetGUI() {
        GUI gui = new GUI();
        System.setOut(new PrintStream(new RedirectingOutputStream(gui), true));
        gui.start();
    }
    public void run(){
        FamilyFriendlySocialNetwork MicroBlog = new FamilyFriendlySocialNetwork();
        Scanner scan = new Scanner(System.in);
        String tooLong = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc id justo in ante consequat consectetur. Vestibulum venenatis rhoncus consequat. Nulla ante nisl, scelerisque ac commodo ut, efficitur id lorem. Cras varius fermentum ipsum, at pretium orci pharetra eget. Vestibulum et leo quis tortor facilisis consequat. Pellentesque aliquam ac elit ut posuere. Duis facilisis lectus sit amet mi hendrerit pharetra. Maecenas eget consequat metus. Integer feugiat fringilla auctor. Maecenas eleifend rutrum tristique.\n" +
                "\n" +
                "Maecenas vel mattis ex. Integer tincidunt iaculis ex, nec viverra enim maximus a. Fusce eu placerat justo, ac auctor risus. Donec sem nunc, posuere eu nisl nec, suscipit elementum risus. Morbi id quam at lacus finibus bibendum ac pellentesque arcu. Aenean venenatis egestas tortor in sollicitudin. Fusce eget euismod metus. Donec venenatis maximus orci ac tincidunt. Proin molestie sem orci, sed suscipit augue pulvinar ut. Etiam velit augue, eleifend et. ";

        String[] usernames = {"Alessia",  "Enrico",  "Alessandra",  "Rossana", "Carlo", "Simone", "Anna", "Fabiano", "Arianna", "Lorenzo"};

        String[] post = {"\"Computer viruses are an urban legend.\"\n- Peter Norton, 1988", "\"Life is too short to run proprietary software.\"\n- Bdale Garbee", "\"I don’t know what the language of the year 2000 will look like, but I know it will be called Fortran.\"\n– CA Hoare, 1982", "\"Any fool can use a computer.  Many do.\"\n- Ted Nelson", "\"Men are from Mars. Women are from Venus. Computers are from hell.\"", "\"Where is the ‘any’ key?\" \n– Homer Simpson, in response to the message, \"Press any key\"", "\"Looking at code you wrote more than two weeks ago is like looking at code you are seeing for the first time.\"\n– Dan Hurvitz", "\"I don’t care if it works on your machine!  We are not shipping your machine!\"\n- Vidiu Platon", "\"If people never did silly things, nothing intelligent would ever get done.\" \n– Ludwig Wittgenstein", "The world we have created is a product of our thinking; it cannot be changed without changing our thinking.\"\n- Albert Einstein", "\"The central enemy of reliability is complexity.\"\n- Geer et al.", "\"Most of you are familiar with the virtues of a programmer.  There are three, of course: laziness, impatience, and hubris.\"\n- Larry Wall", "\"It does not happen all at once. There is no instant pudding.\"\n- W. Edwards Deming", "\"Good specifications will always improve programmer productivity far better than any programming tool or technique.\"\n- Milt Bryce", "\"In C++ it’s harder to shoot yourself in the foot, but when you do, you blow off your whole leg.\"\n- Bjarne Stroustrup", "He who rejects change is the architect of decay.  The only human institution which rejects progress is the cemetery.\"\n- Harold Wilson", "\"If you think your users are idiots, only idiots will use it.\"\n– Linus Torvalds", "\"Dogs have Owners, Cats have Staff.\"", "\"Good programmers use their brains, but good guidelines save us having to think out every case.\"\n- Francis Glassborow", "\"In the future, computers may weigh no more than 1.5 tonnes.\"\n– Popular mechanics, 1949"};

        String benvenuto = "Benvenuto nel test di Microblog!";
        char[] testBenvenuto = benvenuto.toCharArray();
        for (char char_: testBenvenuto){
            System.out.print(char_);
            try {
                Thread.sleep(20);
            } catch (InterruptedException ignored) {

            }
        }
        System.out.println();
        String explain = "Il seguente test prenderà 10 username a cui associerà 10 post (uno per autore), per poi testare tutti i metodi di cui è richiesta l'implementazione.";
        char[] testExplain = explain.toCharArray();
        for (char char_: testExplain){
            System.out.print(char_);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {

            }
        }
        System.out.println("\nPremere invio per iniziare...");
        scan.nextLine();


        // Popolo il social network
        int i = 0;
        System.out.println("Inserisco i seguenti utenti nel socialnetwork:\n");
        for (String user: usernames){
            try {
                if(i == 20)
                    break;
                MicroBlog.addPost(user, post[i]);
                i++;
                if(i%2!=0)
                    System.out.format("%-15s\t", user);
                else
                    System.out.format("%-15s\n", user);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {

                }
            } catch (IllegalLengthException | EmptyTextException e) {
                System.out.println(e);
            }
        }
        System.out.println("Vediamo col metodo getMentionedUser, se sono stati inseriti tutti. Premere invio per continuare...");
        scan.nextLine();
        System.out.println("MicroBlog.getMentionedUser():\n" + MicroBlog.getMentionedUser());

        // Interazioni
        System.out.println("\n\nAdesso alcuni utenti ne seguiranno ( -> ) altri, premere invio per continuare...");
        scan.nextLine();
        System.out.format("%-21s %-21s %-21s\n%-21s %-21s %-21s\n%-21s %-21s %-21s\n",
                usernames[0], usernames[4], usernames[8],
                usernames[1] + " -> " + usernames[3], usernames[5] + " -> " + usernames[7], usernames[9] + " -> " + usernames[5],
                usernames[2], usernames[6], usernames[0]);
        System.out.println();
        System.out.println(usernames[0] + " -> " + usernames[9]);
        for (int j = 0; j < usernames.length - 1; j++){
            if (j == 2 || j == 6)
                continue;
            System.out.println(usernames[j+1] + " -> " + usernames[j]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {

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


        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {

        }
        System.out.println("\n\nControllando col metodo getFollowed, che restituise la mappa (utenti, propri seguaci), si ottiene:\n" + MicroBlog.getFollowed());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {

        }
        System.out.println("\n\nMentre la mappa derivata da MicroBlog.guessFollowers() è:\n" + MicroBlog.guessFollowers());
        System.out.println("\n\nPer vedere la lista degli influencers, premere invio..." );
        scan.nextLine();
        System.out.println("MicroBlog.influencers() -> " + MicroBlog.influencers());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        System.out.println("\nInfatti, la ripartizione di followers e followed degli utenti che hanno avuto interazioni è la seguente:\n");
        System.out.format("%-10s%8s\t\t%8s\n", "Nome", "Followers", "Followed");
        int followedSize;
        for (Map.Entry<String, Set<String>> entry: MicroBlog.guessFollowers().entrySet()){
            if (MicroBlog.getFollowed().get(entry.getKey()) == null)
                followedSize = 0;
            else
                followedSize = MicroBlog.getFollowed().get(entry.getKey()).size();
            System.out.format("%-10s%8d\t\t%8d\n", entry.getKey(), MicroBlog.guessFollowers().get(entry.getKey()).size(), followedSize);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {

            }
        }
        System.out.println("\n\033[3mTestiamo infine i metodi di ricerca: \033[0m");
        System.out.println("Ricerca per nome utente -> MicroBlog.writtenBy(\"Alessia\")\nPremere invio per continuare...");
        scan.nextLine();
        for(Post result: MicroBlog.writtenBy("Alessia")){
            result.printPost();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
            System.out.println();
        }

        List<String> search = new LinkedList<>();
        search.add("software");
        search.add("language");
        search.add("cat");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}
        System.out.println("Ricerca per contenuto -> MicroBlog.contains(\"" + search + "\")");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        for (Post result: MicroBlog.containing(search)){
            result.printPost();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
            System.out.println();
        }


    }

    public static void main(String[] args){
        TestSetGUI tsg = new TestSetGUI();
        tsg.run();
    }
}
