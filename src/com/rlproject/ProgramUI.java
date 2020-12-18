package com.rlproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;


public class ProgramUI extends JFrame implements MouseListener {

    int x;
    int y;
    Graphics g;
    Graphics2D ga;
    JButton addAPBtn;
    JButton addObstacleBtn;
    JButton addUserBtn;
    JPanel drawPanel;
    JLabel puissLabel, userLabel;
    JTextField tfPuissance;
    Boolean APMode = false;
    Boolean obstacleMode = false ;
    Boolean userMode = false ;
    float puissance = 0 ;

    ArrayList<AccessPoint> APs = new ArrayList<>();
    AccessPoint ap;



    ProgramUI(String s){
        super(s);
        setLayout(null);
        setSize(900,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        addAPBtn = new JButton("Ajouter un AP");
        addObstacleBtn = new JButton("Ajouter un obstacle");
        addUserBtn = new JButton("Ajouter un utilisateur");
        drawPanel = new JPanel();
        tfPuissance = new JTextField();
        puissLabel = new JLabel("Puissance de signal (mW)");
        userLabel = new JLabel("");


        addAPBtn.setBounds(650,10,220,30);
        addObstacleBtn.setBounds(650,50,220,30);
        addUserBtn.setBounds(650,90,220,30);
        drawPanel.setBounds(0,0,645,500);
        puissLabel.setBounds(650,130,220,30);
        tfPuissance.setBounds(650,160,220,30);
        userLabel.setBounds(650,200,220,30);


        drawPanel.addMouseListener(this);
        drawPanel.setBackground(Color.white);

        addAPBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                APMode = true;
                userMode = false ;
                obstacleMode = false;
            }
        });
        addUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                userMode = true;
                APMode = false;
                obstacleMode = false;
                String s = JOptionPane.showInputDialog("Entree les coordonnees de l'utilisateur x-y");
                x= Integer.parseInt(s.substring(0,s.indexOf('-')));
                y= Integer.parseInt(s.substring(s.indexOf('-')+1));
                if(APs.size()!=0){
                    userLabel.setText("user at ("+x+","+y+") is closest to AP"+AccessPoint.calculateAP(x,y,APs));
                } else {
                    userLabel.setText("No APs available");
                }

            }
        });
        addObstacleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                obstacleMode = true ;
                APMode = false;
                userMode = false;
            }
        });

        add(addAPBtn);
        add(addObstacleBtn);
        add(addUserBtn);
        add(drawPanel);
        add(tfPuissance);
        add(puissLabel);
        add(userLabel);

        setVisible(true);

        g = drawPanel.getGraphics();
        ga = (Graphics2D) g;

    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) { }

    @Override
    public void mousePressed(MouseEvent mouseEvent) { }

    @Override
    public void mouseReleased(MouseEvent e) {
        //on clique sur le bouton gauche
        if(e.getButton() == MouseEvent.BUTTON1) {
            //récupérer les coordonnées(x,y) de la souris
            x = e.getX();
            y = e.getY();
        }

        if(APMode){
            puissance =  Float.parseFloat(tfPuissance.getText());
            ap = new AccessPoint(x,y, APs.size(),puissance);
            ap.drawCellule(ga);
            ga.setPaint(Color.black);
            ga.drawString("AP"+ap.num,x,y);
            APs.add(ap);
       // }else if(userMode){

        }else if(obstacleMode){

        }

    }



    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }

    @Override
    public void mouseExited(MouseEvent mouseEvent) { }

}
