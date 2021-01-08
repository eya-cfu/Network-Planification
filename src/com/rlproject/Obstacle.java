package com.rlproject;
import java.awt.* ;
public class Obstacle {

     int xc, yc;
     String material;
     double factAttenuation;
     Color C;
     Shape rect;

        public Obstacle (int xc, int yc, String m) {
            this.xc=xc;
            this.yc=yc;
            material=m;
            switch (material){
                case "bois" :
                case "plastique" :
                    factAttenuation = 0.15;
                    break;
                case "verre" :
                    factAttenuation = 0.3;
                    break;
                case "verre teinté" :
                case "eau" :
                case "être vivant" :
                case "briques" :
                case "plâtre" :
                    factAttenuation = 0.5;
                    break;
                case "céramique" :
                case "papier" :
                    factAttenuation = 0.75;
                    break;
                case "béton" :
                case "verre blindé" :
                    factAttenuation = 0.85;
                    break;
                case "métal":
                    factAttenuation = 0.9;
                    break;
                default:
                    factAttenuation = 0;
                    break;
            }
        }
        void drawObstacle(Graphics2D g) {
            C = new Color(40, 40, 40,128);
            rect = new Rectangle(xc, yc, 5, 25);
            g.setPaint(C);
            g.fill(rect);
        }

        // @return 1 if right -1 if left to the AP(x,y)
        int leftOrRight(int x){
            return x<this.xc ? 1 : -1;
        }
        // @return 1 if above -1 if below the AP(x,y)
        int aboveOrBelow(int y) {
            return y<this.yc ? -1 : 1;
        }


}
