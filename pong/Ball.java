package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
   public double x,y;
   public double speed = 8;
   public int width, height; 
   public int goalPlayerOne = 0; // jogador azul
   public int goalPlayerTwo = 0; // jogador vermelho

   public double dx, dy;

   public Ball(int x, int y){
    this.x = x;
    this.y = y;
    this.width = 10;
    this.height = 10;

    int angle = new Random().nextInt(120 - 45) + 45;
    dx = Math.cos(Math.toRadians(angle));
    dy = Math.sin(Math.toRadians(angle));
    

   }

   public void reset(){
    x = Game.WIDTH * Game.SCALE / 2;
    y = Game.HEIGHT * Game.SCALE / 2;

    int angle = new Random().nextInt(359 - 50) + 50;
    dx = Math.cos(Math.toRadians(angle));
    dy = Math.sin(Math.toRadians(angle));
   }

   public void tick(){

    // tratando colisoes com as paredes laterais
    if(x+(dx*speed)+width>=Game.WIDTH*Game.SCALE){
        dx*=-1; // multiplica o eixo x por menos 1 e inverte o sentido
     }else if(x+(dx*speed)+width < 0){
         dx*=-1; // multiplica o eixo x por menos 1 e inverte o sentido    
}
    if(y>= Game.HEIGHT*Game.SCALE){
        //ponto do jogador de cima/ jogador azul / player one ...
        System.out.println("ponto do jogador de cima");
        goalPlayerOne++;
        System.out.println(goalPlayerOne);
        reset();
    }else if( y < 0){
        //pontto do jogador baixo / jogador vermelho / player two...
        System.out.println("ponto do jogador de baixo");
        goalPlayerTwo++;
        System.out.println(goalPlayerTwo);
        reset();
    }

    Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);

    Rectangle boundsPlayerOne = new Rectangle(Game.playerOne.x, Game.playerOne.y, Game.playerOne.width, Game.playerOne.height);
    Rectangle boundsPlayertwo = new Rectangle(Game.playerTwo.x, Game.playerTwo.y, Game.playerTwo.width, Game.playerTwo.height);

    if(bounds.intersects(boundsPlayerOne)){
        dy *= -1;
    }else if(bounds.intersects(boundsPlayertwo)){
        dy *= -1;
    }

    x += dx * speed;
    y += dy * speed;
    
   }

   public void render(Graphics g){
    g.setColor(Color.BLACK);
    g.fillRect((int)x,(int) y, width, height);

    
    
   }
}
