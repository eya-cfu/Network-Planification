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
    JLabel puissLabel;
    JTextArea userLabel;
    JTextField tfPuissance;
    Boolean APMode = false;
    Boolean obstacleMode = false ;
    Boolean userMode = false ;
    float puissance = 0 ;

    ArrayList<AccessPoint> APs = new ArrayList<>();
    ArrayList<Obstacle> obs_list = new ArrayList<Obstacle>();
    Object[] materials = {"bois","plastique","verre","verre teinté","eau","être vivant","briques","plâtre",
                            "céramique","papier","béton","verre blindé","métal"};




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
        puissLabel = new JLabel("Puissance de signal [10,100] (mW)");
        userLabel = new JTextArea("");
        userLabel.setLineWrap(true);
        userLabel.setVisible(false);


        addAPBtn.setBounds(650,10,220,30);
        addObstacleBtn.setBounds(650,50,220,30);
        addUserBtn.setBounds(650,90,220,30);
        drawPanel.setBounds(0,0,645,500);
        puissLabel.setBounds(650,130,220,30);
        tfPuissance.setBounds(650,160,220,30);
        userLabel.setBounds(650,200,220,50);


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
        addUserBtn.addActionListener(actionEvent -> {
            userMode = true;
            APMode = false;
            obstacleMode = false;
          /*  String s1 = JOptionPane.showInputDialog("Entree les coordonnees de l'utilisateur x-y\n" +
                    "0<x<645 et 0<y<500");
            x= Integer.parseInt(s1.substring(0, s1.indexOf('-')));
            y= Integer.parseInt(s1.substring(s1.indexOf('-')+1));
            AccessPoint ap = AccessPoint.calculateAP(x,y,APs);
            String zone = ap.distance(x,y) < ap.r ? " et dans sa zone de couverture" : " mais n'est pas dans sa zone de couverture" ;
            if(APs.size()!=0){
                userLabel.setText("l'utilisateur a ("+x+","+y+") est plus proche de l'AP "+ap.num
                + zone);
            } else {
                userLabel.setText("No APs available");
            }
            userLabel.setVisible(true); */

        });
        addObstacleBtn.addActionListener(actionEvent -> {
            obstacleMode = true ;
            APMode = false;
            userMode = false;
            Component component = (Component) actionEvent.getSource();
            JFrame frame = (JFrame) SwingUtilities.getRoot(component);
            String s1 = (String) JOptionPane.showInputDialog(component,"Choisir le matériau","Obstacle",
                    JOptionPane.PLAIN_MESSAGE,null,materials,materials[0]);

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
            try {
                puissance = Float.parseFloat(tfPuissance.getText());
                AccessPoint ap = new AccessPoint(x,y, APs.size(),puissance);
                ap.drawCellule(ga);
                ga.setPaint(Color.black);
                ga.drawString("AP"+ap.num,x,y);
                APs.add(ap);
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this,"Entrer la puissance de signal",
                        "Alerte",JOptionPane.WARNING_MESSAGE);
            }

        }else if(userMode){
            User user = new User(x, y);
            user.drawUser(ga);
            if(APs.size()!=0){
                AccessPoint ap = AccessPoint.calculateAP(x,y,APs);
                String zone = ap.distance(x,y) < ap.r ? " et dans sa zone de couverture" : " mais n'est pas dans sa zone de couverture" ;
                userLabel.setText("l'utilisateur a ("+x+","+y+") est plus proche de l'AP "+ap.num
                        + zone);
            } else {
                userLabel.setText("No APs available");
            }
            userLabel.setVisible(true);
        }else if(obstacleMode){
            Obstacle obs = new Obstacle(x, y);
            obs.drawObstacle(ga);
            obs_list.add(obs);
        }

    }



    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }

    @Override
    public void mouseExited(MouseEvent mouseEvent) { }

}
