package pong;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerOne{

    public boolean right,left;
    public int x,y;
    public int width, height;

    public PlayerOne(int x, int y){
        this.x = x; //recebe por parametro
        this.y = y;// recebe por parametro
        this.width = 60; // largura do jogador
        this.height = 10; // altura do jogador
    }

    public void tick(){
        if(right){
            x = x +10;
            
        }
        else if(left){
            x = x -10;
           
        }

        if(x + width > Game.WIDTH*Game.SCALE){
            x = (Game.WIDTH*Game.SCALE) - (width);
            
        }
        else if(x < 0){
            x = 0;
        }
    }

    public void render(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}
