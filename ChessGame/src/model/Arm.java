package model;

import java.awt.*;

public class Arm {

    public int x, y;
    public int Width = 50, Height = 50;
    public Color armColor;

    public boolean alive = true;
    public int max_hp = 100;
    public int hp = max_hp;

    public int attack = 20;
    public int range = 1;
    public int max_att_time = 40;
    public int att_time = max_att_time;

    public String type = "Worrier";

    public Arm(int x, int y, Color armColor) {
        this.x = x;
        this.y = y;
        this.armColor = armColor;
    }

    public void paint(Graphics g) {
        Color originalColor = g.getColor();
        g.setColor(armColor);
        g.fillRect(this.x * 60 + 55, this.y * 60 + 105, this.Width, this.Height);

        g.setColor(originalColor);
    }


}
