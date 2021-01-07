package com.rlproject;

import java.awt.*;

public class User {
    int x, y;

    public User (int x,int y) {
        this.x=x;
        this.y=y;
    }
    public void drawUser (Graphics2D page)
    {
        int top = y - 50;  // top of head

        page.setColor (Color.gray);

        page.drawOval (x-10, top, 20, 20);  // head

        page.drawLine (x, top+20, x, top+30);  // trunk

        page.drawLine (x, top+30, x-10, top+25);  // arms
        page.drawLine (x, top+30, x+10, top+25);

        page.drawLine (x, top+30, x-5, top+40);  // arms
        page.drawLine (x, top+30, x+5, top+40);
    }
}
