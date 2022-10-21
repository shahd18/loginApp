
package jeddahseason;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class JeddahSeason extends JFrame{
    private JFrame frame;
    private JPanel panel;
    private JPanel userPanel;
    private JPanel passPanel;
    private JPanel logInPanel;
    private JPanel signUpPanel;
    private JLabel label;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel label1;
    private JTextField userText;
    private JTextField passText;
    private JButton logInButton;
    private JButton signUpButton;
    private JLabel welcomLabel;
    private JPanel continare;
    private ImageIcon image;
    private JLabel imageLabel;
    private File file;

    public JeddahSeason(){
        frame = new JFrame();
        frame.setTitle(" ");
        frame.setLocationRelativeTo(null);
        frame.setSize(450,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        buildPanel();
        frame.add(panel, BorderLayout.CENTER);
        sidePanel();
        frame.add(continare, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);
   }
    
    public void buildPanel(){
        userPanel = new JPanel();
        userPanel.setBackground(Color.WHITE);
        userLabel = new JLabel("User Name");
        userLabel.setForeground(new Color(60,110,150));
        userText = new JTextField(15);
        userText.setBackground(Color.LIGHT_GRAY);
        userPanel.add(userLabel);
        userPanel.add(userText);
        
        passPanel = new JPanel();
        passLabel = new JLabel("Password");
        passLabel.setForeground(new Color(60,110,150));
        passText = new JTextField(15);
        passText.setBackground(Color.LIGHT_GRAY);
        passPanel.setBackground(Color.WHITE);
        passPanel.add(passLabel);
        passPanel.add(passText);
        
        logInPanel = new JPanel();
        logInPanel.setBackground(Color.WHITE);
        logInButton = new JButton("Log in");
        logInButton.setForeground(Color.WHITE);
        logInButton.setBackground(new Color(60,110,150));
        logInButton.addActionListener(new signInButtonAction() );
        logInPanel.add(logInButton);
        
        signUpPanel = new JPanel();
        signUpPanel.setBackground(Color.WHITE);
        signUpButton = new JButton("Sign up");
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBackground(new Color(60,110,150));
        signUpButton.addActionListener(new signUpButtonAction());
        signUpPanel.add(signUpButton);
        
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        label = new JLabel("LOG IN");
        label.setFont(new Font("DialogInput",Font.BOLD, 17));
        label.setForeground(new Color(60,110,150));     
        panel.setLayout(new GridLayout(5,1));
        panel.add(label);
        panel.add(userPanel);
        panel.add(passPanel);
        panel.add(logInPanel);
        panel.add(signUpPanel);
        
        
    }
    
    private void sidePanel(){
        welcomLabel = new JLabel("   Welcom to Jeddah Season" );
        welcomLabel.setFont(new Font("DialogInput",Font.BOLD, 17));
        welcomLabel.setForeground(Color.WHITE);
        
        image = new ImageIcon("fun-icon-png-2.png");
        Image dabImage = image.getImage();
        Image modifyImage = dabImage.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(modifyImage);
        imageLabel = new JLabel(image);
        
        
        
        label1 = new JLabel(" Here you will find the joy you look for " );
        label1.setFont(new Font("DialogInput",Font.BOLD, 13));
        label1.setForeground(Color.WHITE);
        
        continare=new JPanel();
        continare.setLayout(new GridLayout(3,1));
        continare.add(welcomLabel);
        continare.add(imageLabel);
        continare.add(label1);
        continare.setBackground(new Color(60,110,150));
    }
    
    
    private class signInButtonAction implements ActionListener{
       public void actionPerformed(ActionEvent ea) {
           String userInput = userText.getText();
           String passInput = passText.getText();
           File file;
           Scanner inputFile;
           String line = null;
           
           
           if(ea.getActionCommand() == logInButton.getActionCommand()){
               try{
                   file = new File("user.txt");
                   inputFile = new Scanner(file);
                   
                    
                   if(checkValidPUserName(userInput)){
                       if(checkValidPassword(passInput)){
                           while(inputFile.hasNext()){
                               line = inputFile.next();
                               if(line.matches(userInput)){ 
                                   line = inputFile.next();  
                                   if(line.matches(passInput)){
                                   JOptionPane.showMessageDialog(null, "Welcom "+userText.getText());
                                    homePanel home = new homePanel();
                                    frame.dispose();
                                    break;
                                   }
                                   break;
                               }

                           }               
                       }
                       JOptionPane.showMessageDialog(null, "User not Found");
                    }
                   inputFile.close();
               }
               catch(Exception e){
                 JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
               }   
           }
       }
    
        
    }
    
     private class signUpButtonAction implements ActionListener{
       public void actionPerformed(ActionEvent ea) {
           frame.dispose();
           signUpPanel su = new signUpPanel();
       }
    }
     
     
     public static boolean checkValidPUserName(String userName)
    {
        boolean isValid = true;
        if(userName.isEmpty()){
            JOptionPane.showMessageDialog(null,"User Name must be entered.");
            isValid = false;

        }
        if (userName.length() > 10 || userName.length() < 6 )
        {
            JOptionPane.showMessageDialog(null,"User Name must be less than 10 and more than 6 characters in length.");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!userName.matches(upperCaseChars ))
        {
            JOptionPane.showMessageDialog(null,"User Name must have atleast one uppercase character");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!userName.matches(lowerCaseChars ))
        {
            JOptionPane.showMessageDialog(null,"User Name must have atleast one lowercase character");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!userName.matches(numbers ))
        {
            JOptionPane.showMessageDialog(null,"User Name must have atleast one number");
            isValid = false;
        }

        return isValid;
    }
    
    
    public static boolean checkValidPassword(String password)
    {
        boolean isValid = true;
        if(password.isEmpty()){
         JOptionPane.showMessageDialog(null,"Password must be entered.");
         isValid = false;
        }
        if (password.length() > 15 || password.length() < 8)
        {
            JOptionPane.showMessageDialog(null,"Password must be less than 20 and more than 8 characters in length.");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
            JOptionPane.showMessageDialog(null,"Password must have atleast one uppercase character");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
            JOptionPane.showMessageDialog(null,"Password must have atleast one lowercase character");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
            JOptionPane.showMessageDialog(null,"Password must have atleast one number");
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%,-,_].*$)";
        if (!password.matches(specialChars ))
        {
            JOptionPane.showMessageDialog(null,"Password must have atleast one special character among @#$%");
            isValid = false;
        }


        return isValid;
    }

    
    
}
