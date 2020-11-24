package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.*;



public class TestSet {
    public static void main(String[] args){
        FamilyFriendlySocialNetwork MicroBlog = new FamilyFriendlySocialNetwork();
        String tooLong = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc id justo in ante consequat consectetur. Vestibulum venenatis rhoncus consequat. Nulla ante nisl, scelerisque ac commodo ut, efficitur id lorem. Cras varius fermentum ipsum, at pretium orci pharetra eget. Vestibulum et leo quis tortor facilisis consequat. Pellentesque aliquam ac elit ut posuere. Duis facilisis lectus sit amet mi hendrerit pharetra. Maecenas eget consequat metus. Integer feugiat fringilla auctor. Maecenas eleifend rutrum tristique.\n" +
                "\n" +
                "Maecenas vel mattis ex. Integer tincidunt iaculis ex, nec viverra enim maximus a. Fusce eu placerat justo, ac auctor risus. Donec sem nunc, posuere eu nisl nec, suscipit elementum risus. Morbi id quam at lacus finibus bibendum ac pellentesque arcu. Aenean venenatis egestas tortor in sollicitudin. Fusce eget euismod metus. Donec venenatis maximus orci ac tincidunt. Proin molestie sem orci, sed suscipit augue pulvinar ut. Etiam velit augue, eleifend et. ";

        String[] usernames = {"Alessia", "Matilda", "Carmela", "Rebecca", "Enrico", "Raffaela", "Vinicia", "Carmelo", "Rossana", "Erik", "Alice", "Lorella", "Simone", "Anna", "Fabiano", "Katia", "Arianna", "Oksana", "Angela", "Aicha"};

        String[] post = {"\"Computer viruses are an urban legend.\"\n- Peter Norton, 1988", "\"Le prix Nobel, c’est une bouée de sauvetage lancée à un nageur  qui a déjà atteint la rive.\"\n- George Bernard Shaw", "\"I don’t know what the language of the year 2000 will look like, but I know it will be called Fortran.\"\n– CA Hoare, 1982", "\"Any fool can use a computer.  Many do.\"\n- Ted Nelson", "\"Men are from Mars. Women are from Venus. Computers are from hell.\"", "\"Where is the ‘any’ key?\" \n– Homer Simpson, in response to the message, \"Press any key\"", "\"Looking at code you wrote more than two weeks ago is like looking at code you are seeing for the first time.\"\n– Dan Hurvitz", "\"I don’t care if it works on your machine!  We are not shipping your machine!\"\n- Vidiu Platon", "\"If people never did silly things, nothing intelligent would ever get done.\" \n– Ludwig Wittgenstein", "The world we have created is a product of our thinking; it cannot be changed without changing our thinking.\"\n- Albert Einstein", "\"I have not failed. I've just found 10,000 ways that won't work.\"\n- Thomas A. Edison ", "\"Most of you are familiar with the virtues of a programmer.  There are three, of course: laziness, impatience, and hubris.\"\n- Larry Wall", "\"It does not happen all at once. There is no instant pudding.\"\n- W. Edwards Deming", "Your lean process should be a lean process.\"\n- Author Unknown", "\"In C++ it’s harder to shoot yourself in the foot, but when you do, you blow off your whole leg.\"\n- Bjarne Stroustrup", "He who rejects change is the architect of decay.  The only human institution which rejects progress is the cemetery.\"\n- Harold Wilson", "\"If you think your users are idiots, only idiots will use it.\"\n– Linus Torvalds", "\"If you think your users are idiots, only idiots will use it.\"\n– Linus Torvalds", "\"The trouble with programmers is that you can never tell what a programmer is doing until it’s too late.\"\n- Seymour Cray", "\"In the future, computers may weigh no more than 1.5 tonnes.\"\n– Popular mechanics, 1949"};

        String[] post2 = {"\"There is nothing so useless as doing efficiently that which should not be done at all.\"\n- Peter F. Drucker", "\"From a programmer’s point of view, the user is a peripheral that types when you issue a read request.\"\n- P. Williams", "\"Software is a gas; it expands to fill its container.\"\n- Nathan Myhrvold", "\"I have not failed. I've just found 10,000 ways that won't work.\"\n- Thomas A. Edison ", "\"Life is too short to run proprietary software.\"\n- Bdale Garbee", "\"The central enemy of reliability is complexity.\"\n- Geer et al.", "\"The best way to predict the future is to implement it.\"\n- David Heinemeier Hansson", "\"The more you know, the more you realize you know nothing.\"\n- Socrates", "\"The question of whether computers can think is like the question of whether submarines can swim.\"\n- Edsger W. Dijkstra", "\"I think it’s a new feature.  Don’t tell anyone it was an accident.\"\n- Larry Wall", "\"On n'innove jamais seul dans son coin\"\n- Areva Innovation Motto", "\"Good specifications will always improve programmer productivity far better than any programming tool or technique.\"\n- Milt Bryce", "\"Optimism is an occupational hazard of programming; feedback is the treatment.\"\n- Kent Beck", "\"Looking at code you wrote more than two weeks ago is like looking at code you are seeing for the first time.\"\n- Dan Hurvitz", "\"Programming is like sex: one mistake and you’re providing support for a lifetime.\"\n- Michael Sinz", "\"The result of long-term relationships is better and better quality, and lower and lower costs.\"\n- W. Edwards Deming", "\"Saying that Java is nice because it works on all OSes is like saying that anal sex is nice because it works on all genders.\"\n- Alanna", "\"The proper use of comments is to compensate for our failure to express ourself in code.\"\n- Uncle Bob Martin", "\"Good programmers use their brains, but good guidelines save us having to think out every case.\"\n- Francis Glassborow", "\"Dogs have Owners, Cats have Staff.\""};
        // Test funzioni base
        try {
            MicroBlog.addPost("Leonardo", "Ciao a tutti, mi sono appena iscritto!");
            MicroBlog.addPost("Pacman", "echo \"ILoveCandy\" >> /etc/pacman.conf");
            MicroBlog.addPost("Rick", "Hey Morty, come here!");
            MicroBlog.addPost("Elena", "Sembra interessante questo Social Network");
            MicroBlog.addPost("null", "");
            MicroBlog.addPost(null, "null");
            MicroBlog.addPost("TOO LONG", "");
        } catch (IllegalLengthException | EmptyTextException error){
            System.out.println(error);
        }

        // Popolo il social network
        int i = 0;
        for (String user: usernames){
            try {
                if(i == 20)
                    break;
                MicroBlog.addPost(user, post2[i]);
                i++;
            } catch (IllegalLengthException | EmptyTextException e) {
                System.out.println(e);
            }
        }


        try {
            MicroBlog.addPost(null, "null");

        }catch (NullPointerException NULL){
            System.out.println("Entry non valida!");
        } catch (IllegalLengthException | EmptyTextException e){
            System.out.println(e);
        }
        try {
            MicroBlog.addPost("TOO LONG", tooLong);
        }catch (EmptyTextException | IllegalLengthException e){
            System.out.println(e);
        }
        try {
            MicroBlog.follow(1, "Elena");
        }catch (AutoFollowException e){
            System.out.println("Non ci si può seguire da soli!");
        }
        // MicroBlog.printAllPosts();




    }

}
