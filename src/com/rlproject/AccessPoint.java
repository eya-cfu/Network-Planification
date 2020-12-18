package com.rlproject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class AccessPoint {
    int xc, yc;
    int r = 50;
    int num; // numero de l'instance AP
    float puissance;

    public AccessPoint(int x, int y, int n, float puissance) {
        this.xc = x;
        this.yc = y;
        this.num = n;
        this.puissance = puissance;
        r= (int) puissance*250/300;
    }

    int i,j,G;
    double f;
    Color C;
    Shape square;
    void drawCellule(Graphics2D ga) {

        for ( i = 0; i < 2 * r; i++)
            for ( j = 0; j < 2 * r; j++) {
                f = Facteur_Attenuation( ((xc - r) + i), ((yc - r) + j));
                G = (int) (f * 255.0);
                if (f != 0) {
                    C = new Color(255-G, 255, 0, 128);
                    square = new Rectangle2D.Double( ((xc - r) + i), ((yc - r) + j), 1, 1);
                    ga.setPaint(C);
                    ga.fill(square);
                }
            }
    }

    double distance( int x, int y) {
        return Math.sqrt((xc - x) * (xc - x) + (yc - y) * (yc - y));
    }

    // facteur d'attenuation du signal, si égal à 1 puissance maximale, si 0
    //pas de signal
    double Facteur_Attenuation( int x, int y)
    { // estimation à corriger selon la formule d’attinuation !!!
        double f = 1.0 - distance( x, y) / ((double) r);
        if (f < 0) return (0);
        return (f);
    }

    public static int calculateAP(int a, int b, ArrayList<AccessPoint> APs){
        int i;
        double distance;
        int closestAP=0;
        double minDistance;

        minDistance = Math.sqrt((APs.get(0).xc - a) * (APs.get(0).xc - a) + (APs.get(0).yc - b) * (APs.get(0).yc - b));

        for (i=0; i<APs.size();i++) {
            distance = Math.sqrt((APs.get(i).xc - a) * (APs.get(i).xc - a) + (APs.get(i).yc - b) * (APs.get(i).yc - b));
            if(minDistance>distance) {
                minDistance = distance;
                closestAP = i;
            }
        }
        return closestAP;
    }



}
