package pong;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 240; // largura da janela
    public static int HEIGHT = 120;// altura da janela
    public static int SCALE = 3; // escala para largura/altura

   

    public static PlayerOne playerOne;
    public static PlayerTwo playerTwo;
    public static Ball ball;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        this.addKeyListener(this);
        playerOne = new PlayerOne(340, HEIGHT - 120);//insatanciando o primeiro jogador
        playerTwo = new PlayerTwo(340, HEIGHT*SCALE - 10);//insatanciando o segundo jogador
        ball      = new Ball((WIDTH*SCALE)/2, (HEIGHT*SCALE)/2);//insatanciando a bola
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);// deixar no centro da tela
        frame.setVisible(true);
        frame.requestFocus();

        new Thread(game).start();
    }

    public void tick() {
        playerOne.tick();
        playerTwo.tick();
        ball.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
        playerOne.render(g);
        playerTwo.render(g);
        ball.render(g);
        bs.show(); // mostrar o jogador na tela
    }

    public void run() {
        while (true) {
            tick();
            render();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
       // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //System.out.println("Tecla RIGHT pressionada");
            playerOne.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
           // System.out.println("Tecla LEFT pressionada");
            playerOne.left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            //System.out.println("Tecla A pressionada");
            playerTwo.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
           // System.out.println("Tecla D pressionada");
            playerTwo.left = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
           // System.out.println("Tecla RIGHT solta");
            playerOne.right = false;
       }
       else if(e.getKeyCode() == KeyEvent.VK_LEFT){
        //System.out.println("Tecla LEFT solta");
            playerOne.left = false;
       }
       if (e.getKeyCode() == KeyEvent.VK_D) {
            //System.out.println("Tecla A pressionada");
            playerTwo.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            //System.out.println("Tecla D pressionada");
            playerTwo.left = false;
        }
    }

}