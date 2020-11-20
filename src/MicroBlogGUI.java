import javax.swing.*;
import java.awt.*;

public class MicroBlogGUI {
    public static void main(String[] args) {

        JFrame microblog =new JFrame();//creating instance of JFrame

        JButton b = new JButton("click");//creating instance of JButton
        microblog.setVisible(true);//making the frame visible
        microblog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Kills the app when exit
        microblog.setTitle("MicroBlog");
        microblog.setResizable(true);
        ImageIcon image = new ImageIcon("logo.png"); // logo
        microblog.getContentPane().setBackground(new Color(48, 54, 64));
        microblog.setIconImage(image.getImage());
        b.setBounds(130,100,100, 40);

        microblog.add(b);//adding button in JFrame

        microblog.setSize(400,500);//400 width and 500 height
        microblog.setLayout(null);//using no layout managers

    }
    }

