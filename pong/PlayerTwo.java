package pong;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerTwo {

    public boolean right,left;
    public int x,y;
    public int width,height;


    public PlayerTwo(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 60;
        this.height = 10;
    }

    public void tick(){
        if(right){
            x = x + 10;
            
        }
        else if(left){
            x = x - 10; 
            
        }
        if(x + width > Game.WIDTH*Game.SCALE){
            x = (Game.WIDTH*Game.SCALE) - (width);
            
        }
        else if(x<0)
        x=0;
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);

    }
    
}
