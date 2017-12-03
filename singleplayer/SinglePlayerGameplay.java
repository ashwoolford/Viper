/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleplayer;

import db.Sqlite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import sound.PlaySound;

/**
 *
 * @author josh
 */
public class SinglePlayerGameplay extends JPanel implements KeyListener,ActionListener{
    
    private ImageIcon titleIc;

    private final int snakeXlen[] = new int[750];
    private final int snakeYlen[] = new int[750];

    private final int [] enemyXpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450
    ,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,825};

    private final int [] enemyYpos= {100,125,150,175,200,225,250,275,300,325,350,375,400,425,450
            ,475,500,525,550,575,600,625};

    private final Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(22);

    private int scorex = 0;

    private ImageIcon enemyImage;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon leftMouth;
    private ImageIcon rightMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;

    private int snakeLen = 3;
    private int moves = 0;

    private final Timer timer;
    private final int delay = 100;

    private ImageIcon snakeImage;
    
    
    public SinglePlayerGameplay(){
        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(delay, this);
        timer.start();
        
    }
    
    @Override
    public void paint(Graphics g){
        if (moves == 0) {
            snakeXlen[2] = 50;
            snakeXlen[1] = 75;
            snakeXlen[0] = 100;

            snakeYlen[2] = 100;
            snakeYlen[1] = 100;
            snakeYlen[0] = 100;
            

        }
        
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);
        //draw the title image
        titleIc = new ImageIcon("snaketitle.png");
        titleIc.paintIcon(this, g, 25, 11);
        //border for gameplay
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);

        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Player 2 Score : "+scorex,750,30);

        
        
        //drawing the only right mouth in 100,100 position from array

        rightMouth = new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this, g, snakeXlen[0], snakeYlen[0]);
        
        
         //for drawing body of snake

        for (int i = 0; i < snakeLen; ++i) {
            if (i == 0 && right) {
                rightMouth = new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(this, g, snakeXlen[i], snakeYlen[i]);

            }


            if (i == 0 && left) {

                leftMouth = new ImageIcon("leftmouth.png");
                leftMouth.paintIcon(this, g, snakeXlen[i], snakeYlen[i]);
            }


            if (i == 0 && down) {
                downMouth = new ImageIcon("downmouth.png");
                downMouth.paintIcon(this, g, snakeXlen[i], snakeYlen[i]);
            }



            if (i == 0 && up) {
                upMouth = new ImageIcon("upmouth.png");
                upMouth.paintIcon(this, g, snakeXlen[i], snakeYlen[i]);
            }



            if (i != 0) {
                snakeImage = new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this, g, snakeXlen[i], snakeYlen[i]);

            }


        }
        
        
        
       //drawing the image of enemy 
        enemyImage = new ImageIcon("hamburger.png");
        
        
        //detecting the action of enemy if successful then draw another enemy in random position 
        
        if(enemyXpos[xpos] == snakeXlen[0] && enemyYpos[ypos] == snakeYlen[0]){
            
            snakeLen++;
            scorex +=5;
            xpos = random.nextInt(34);
            ypos = random.nextInt(21);
            PlaySound sound = new PlaySound(new File("enemy_1.wav"));
            Thread thread = new Thread(sound);
            thread.start();
        }
        
        
         enemyImage.paintIcon(this,g,enemyXpos[xpos],enemyYpos[ypos]);


        //detecting the collision between two snake
        for(int s1 = 1;s1 < snakeLen ; ++s1){
            if(snakeXlen[s1] == snakeXlen[0] && snakeYlen[s1] == snakeYlen[0]){
                Sqlite sqliteDB = new Sqlite();
                String query = "INSERT INTO scores (scores) VALUES("+scorex+");";
                sqliteDB.insetIntoDB(query);
                try {
                    sqliteDB.getList();
                } catch (SQLException ex) {
                    Logger.getLogger(SinglePlayerGameplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    sqliteDB.closeDatabase();
                } catch (SQLException ex) {
                    Logger.getLogger(SinglePlayerGameplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                reset();
                showClashMsgX(g);
               


            }
        }
        
        g.dispose();
        
        
    }
    
    
    


    @Override
    public void keyTyped(KeyEvent e) {
         //if pressed space then every will reset 

      
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            moves = 0;
            scorex = 0;
            snakeLen = 3;
            repaint();
        }
        
     

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                moves++;
                right = true;
                if (left) {
                    right = false;
                    left = true;
                }
                up = false;
                down = false;
                break;

            
            case KeyEvent.VK_LEFT:
                moves++;
                left = true;
                if (right) {
                    right = true;
                    left = false;
                }
                up = false;
                down = false;
                break;

            case KeyEvent.VK_UP:
                moves++;
                up = true;
                if (down) {
                    up = false;
                    down = true;
                }
                left = false;
                right = false;
                break;

            case KeyEvent.VK_DOWN:

                moves++;
                down = true;
                if (up) {
                    up = true;
                    down = false;
                }
                left = false;
                right = false;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if press right key snake will go towards right side
        
     
        if (right) {
            for (int r = snakeLen - 1; r >= 0; r--) {
                snakeYlen[r + 1] = snakeYlen[r];

            }

            for (int r = snakeLen; r >= 0; r--) {
                if (r == 0) {
                    snakeXlen[r] = snakeXlen[r] + 25;

                } else {
                    snakeXlen[r] = snakeXlen[r - 1];

                }
                if (snakeXlen[r] > 850) {
                    snakeXlen[r] = 25;

                }

            }
            repaint();


        }
        
       
        //if press left key snake will go towards left side
        
        if (left) {

            for (int r = snakeLen - 1; r >= 0; r--) {
                snakeYlen[r + 1] = snakeYlen[r];

            }

            for (int r = snakeLen; r >= 0; r--) {
                if (r == 0) {
                    snakeXlen[r] = snakeXlen[r] - 25;

                } else {
                    snakeXlen[r] = snakeXlen[r - 1];

                }
                if (snakeXlen[r] < 25) {
                    snakeXlen[r] = 850;

                }

            }
            repaint();


        }

        ////if press up key snake will go towards up side
        if (up) {

            for (int r = snakeLen - 1; r >= 0; r--) {
                snakeXlen[r + 1] = snakeXlen[r];

            }

            for (int r = snakeLen; r >= 0; r--) {
                if (r == 0) {
                    snakeYlen[r] = snakeYlen[r] - 25;

                } else {
                    snakeYlen[r] = snakeYlen[r - 1];

                }
                if (snakeYlen[r] < 75) {
                    snakeYlen[r] = 625;

                }

            }
            repaint();


        }
        
         if (down) {

            for (int r = snakeLen - 1; r >= 0; r--) {
                snakeXlen[r + 1] = snakeXlen[r];

            }

            for (int r = snakeLen; r >= 0; r--) {
                if (r == 0) {
                    snakeYlen[r] = snakeYlen[r] + 25;

                } else {
                    snakeYlen[r] = snakeYlen[r - 1];

                }
                if (snakeYlen[r] > 625) {
                    snakeYlen[r] = 75;

                }

            }
            repaint();


        }


       
    }
    
    public void showClashMsgX(Graphics g){


        g.setColor(Color.RED);
        g.setFont(new Font("arial",Font.BOLD,30));
        g.drawString("Game over!!!",330,400);



        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.BOLD,20));
        g.drawString("Space to restart",330,500);

    }



    public void reset(){
        right = false;
        left = false;
        up = false;
        down = false;


        moves = 0;

    } 
    
    
}
