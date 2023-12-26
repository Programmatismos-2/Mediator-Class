package com.freddo;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Home_Page extends JFrame {
    static ImageIcon logo = new ImageIcon("C:/Users/first/Downloads/Sky Logo.png");    //Αυτο το path θα πρεπει να αλλάξει ώστε η εικόνα να βρίσκεται στον κοινό φάκελο με το project

    public Home_Page() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //Κλεινει το παραθυρο οταν καποιος παταει το x
        setSize(800, 530);    //Καθορίζει το μέγεθος του παραθυρου 
        setLayout(null);      //Προσδιορίζει το είδος του layout που εχει το frame
        
        setIconImage(logo.getImage());    //Θέτει την εικονα με το παραπανω path σαν logo του παραθύρου
        ImageIcon homePic = new ImageIcon("C:/Users/first/Downloads/HomePlane1.jpg");    //Αυτο το path θα πρεπει να αλλάξει ώστε η εικόνα να βρίσκεται στον κοινό φάκελο με το project
        JLabel lab1 = new JLabel(homePic);       //Εισάγω την φωτογραφία background σαν JLabel
        lab1.setSize(800, 530);     //Ορίζω το μέγεθος του label ίδιο με του frame
        add(lab1);    //Προσθέτω το label στο frame
        
        JButton signinButton;
        JButton checkButton;
        signinButton = new JButton("Sign In");     //Φτιάχνει κουμπί με όνομα "Sign in"
        checkButton = new JButton("Check In");     //Φτιάχνει κουμπί με όνομα "Check In"
        signinButton.setBounds(150, 265, 200, 50);    //Προσδιορίζει το μέγεθος του κουμπιου
        checkButton.setBounds(450, 265, 200, 50);  //Προσδιορίζει το μέγεθος του κουμπιου
        signinButton.setBorder(BorderFactory.createEtchedBorder());
        checkButton.setBorder(BorderFactory.createEtchedBorder());
        
    
        signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                this.setVisible(false);    //Κλείνει το home page
                SignInPage sign = new SignInPage();
                sign.openSignInWindow();
            }
        });
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                this.setVisible(false);       //Κλείνει το home page και όταν τελείωσει η διαδικασία θα το ξανανοιξουμε 
                CheckInPage chpage = new CheckInPage();
                chpage.opencheckWindow(1);    //Ανοίγει το παράθυρο που ευθύνεται για το check in
            }
        });
        
        add(signinButton);       //Προσθέτει το signinButton στο frame
        add(checkButton);     //Προσθέτει το checkButton στο frame
        setVisible(true);     //Εμφανίζει τα componets στο παραθυρο
    }
}
