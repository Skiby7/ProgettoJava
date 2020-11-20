import javax.swing.*;
import java.awt.*;

public class MicroBlogGUI {
    public static void main(String[] args) {

        ImageIcon image = new ImageIcon("logo.png"); // logo

        JLabel post = new JLabel();
        post.setText("Posts:");
        post.setIcon(image);
        post.setHorizontalTextPosition(JLabel.CENTER);
        post.setVerticalTextPosition(JLabel.TOP);
        post.setForeground(Color.white);
        post.setFont(new Font("Fantasque Mono Sans", Font.PLAIN, 20));

        JFrame microblog = new JFrame();//creating instance of JFrame
        microblog.setVisible(true);//making the frame visible
        microblog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Kills the app when exit
        microblog.setTitle("MicroBlog");
        microblog.setResizable(false);
        microblog.getContentPane().setBackground(new Color(48, 54, 64));
        microblog.setSize(800,600);//400 width and 500 height
        microblog.setLayout(null);//using no layout managers

        microblog.add(post);



    }
    }

