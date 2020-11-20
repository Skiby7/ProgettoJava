package com.MicroBlog;

import com.MicroBlog.CustomExceptions.*;
import com.MicroBlog.Interfaces.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    JLabel post;
    JButton posta;

    public GUI() {

        JFrame microblog = new JFrame();//creating instance of JFrame
        post = new JLabel();
        post.setText("Posts:");
        post.setIconTextGap(20);
        post.setForeground(Color.white);
        post.setOpaque(false);
        post.setFont(new Font("Fantasque Mono Sans", Font.PLAIN, 20));
        post.setBounds(0,0,250,250);

        posta = new JButton();
        posta.setBounds(30, 30, 100, 50);
        posta.addActionListener(this);
        posta.setText("Posta!");

        this.setVisible(true);//making the frame visible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Kills the app when exit
        this.setTitle("MicroBlog");
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(48, 54, 64));
        this.setSize(800,600);//400 width and 500 height

        this.add(posta);
        this.add(post);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==posta){
            System.out.println("Pressed");
        }
    }
}

