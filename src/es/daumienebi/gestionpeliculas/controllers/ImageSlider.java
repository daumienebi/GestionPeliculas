package es.daumienebi.gestionpeliculas.controllers;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ImageSlider extends JFrame{
    JLabel pic;
    Timer tm;
    int x = 0;
    //Images Path In Array
    String[] list = {
                      "/resources/spiderman.jpg",//0
                      "/resources/increibles.jpg",//1
                      "/resources/spiderman.jpg",
                      "/resources/4k.jpg",
                      "/resources/damian.jpg",
                      "/resources/background.jpg",
                    };
    
    public ImageSlider(){
        super("Java SlideShow");
        pic = new JLabel();
        pic.setBounds(40, 30, 700, 300);

        //Call The Function SetImageSize
        SetImageSize(6);
               //set a timer
        tm = new Timer(500,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SetImageSize(x);
                x += 1;
                if(x >= list.length )
                    x = 0; 
            }
        });
        add(pic);
        tm.start();
        setLayout(null);
        setSize(800, 400);
        getContentPane().setBackground(Color.decode("#bdb67b"));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    //create a function to resize the image 
    public void SetImageSize(int i){
        ImageIcon icon = new ImageIcon(list[i]);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
    }
}