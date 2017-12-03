package multiplayer;
import sound.PlaySound;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;



public class Gameplay extends JPanel implements KeyListener,ActionListener {
    private ImageIcon titleIc;

    private final int snakeXlen[] = new int[750];
    private final int snakeYlen[] = new int[750];

    private final int snakeX1Len[] = new int[750];
    private final int snakeY1Len[] = new int[750];

    private final int [] enemyXpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450
    ,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,825};

    private final int [] enemyYpos= {100,125,150,175,200,225,250,275,300,325,350,375,400,425,450
            ,475,500,525,550,575,600,625};

    private final Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(22);

    private int scorex = 0;
    private int scorey = 0;

    private ImageIcon enemyImage;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private boolean left1 = false;
    private boolean right1 = false;
    private boolean up1 = false;
    private boolean down1 = false;


    private ImageIcon leftMouth;
    private ImageIcon rightMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;

    private ImageIcon leftMouth1;
    private ImageIcon rightMouth1;
    private ImageIcon upMouth1;
    private ImageIcon downMouth1;

    private int snakeLen = 3;
    private int moves = 0;

    private int snakeLen1 = 3;

    private final Timer timer;
    private final int delay = 100;

    private ImageIcon snakeImage;
    private ImageIcon snakeImage1;






    public Gameplay() throws IOException, ClassNotFoundException {
        addKeyListener(Gameplay.this);
        setFocusable(true);
        timer = new Timer(delay, this);
        timer.start();

    }

    @Override
    public void paint(Graphics g) {



        if (moves == 0) {
            snakeXlen[2] = 50;
            snakeXlen[1] = 75;
            snakeXlen[0] = 100;

            snakeYlen[2] = 100;
            snakeYlen[1] = 100;
            snakeYlen[0] = 100;

            snakeX1Len[2] = 500;
            snakeX1Len[1] = 475;
            snakeX1Len[0] = 450;

            snakeY1Len[2] = 200;
            snakeY1Len[1] = 200;
            snakeY1Len[0] = 200;




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

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Player 1 Score : "+scorey,50,30);
        
        //drawing the only right mouth in 100,100 position from array

        rightMouth = new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this, g, snakeXlen[0], snakeYlen[0]);

        leftMouth1 = new ImageIcon("leftmouthO.png");
        leftMouth1.paintIcon(this, g, snakeX1Len[0], snakeY1Len[0]);


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
        
        //for second snake



        for (int i = 0; i < snakeLen1; ++i) {


            if (i == 0 && right1) {
                rightMouth1 = new ImageIcon("rightmouthO.png");
                rightMouth1.paintIcon(this, g, snakeX1Len[i], snakeY1Len[i]);
            }



            if (i == 0 && left1) {

                leftMouth1 = new ImageIcon("leftmouthO.png");
                leftMouth1.paintIcon(this, g, snakeX1Len[i], snakeY1Len[i]);
            }



            if (i == 0 && down1) {

                downMouth1 = new ImageIcon("downmouthO.png");
                downMouth1.paintIcon(this, g, snakeX1Len[i], snakeY1Len[i]);
            }


            if (i == 0 && up1) {

                upMouth1 = new ImageIcon("upmouthO.png");
                upMouth1.paintIcon(this, g, snakeX1Len[i], snakeY1Len[i]);
            }

            if (i != 0) {
                snakeImage1 = new ImageIcon("snakeimageO.png");
                snakeImage1.paintIcon(this, g, snakeX1Len[i], snakeY1Len[i]);
            }


        }
        
        
        
    
       //drawing the image of enemy 
        enemyImage = new ImageIcon("hamburger.png");
        
        
        //detecting the action of enemy if successful then draw another enemy in random position 
        
        if(enemyXpos[xpos] == snakeXlen[0] && enemyYpos[ypos] == snakeYlen[0]){
            
            snakeLen++;
            scorey +=5;
            xpos = random.nextInt(34);
            ypos = random.nextInt(21);
            PlaySound sound = new PlaySound(new File("enemy_1.wav"));
            Thread thread = new Thread(sound);
            thread.start();
        }
        if(enemyXpos[xpos] == snakeX1Len[0] && enemyYpos[ypos] == snakeY1Len[0]){
            snakeLen1++;
            scorex +=5;
            xpos = random.nextInt(34);
            ypos = random.nextInt(22);
        }

        enemyImage.paintIcon(this,g,enemyXpos[xpos],enemyYpos[ypos]);


        //detecting the collision between two snake
        for(int s1 = 0;s1 < snakeLen1 ; ++s1){
            if(snakeX1Len[s1] == snakeXlen[0] && snakeY1Len[s1] == snakeYlen[0]){
                reset();
                showClashMsgX(g);
               


            }
        }
        
        //detecting the collision between two snake for second snake

        for(int s1 = 0;s1 < snakeLen ; ++s1){
            if(snakeXlen[s1] == snakeX1Len[0] && snakeYlen[s1] == snakeY1Len[0]){
                reset();
                showClashMsgY(g);

            }
        }
        
        //if second player got 200 point then game will reset and showing the winging msg

        if(scorex == 200){
            reset();
            showClashMsgX(g);

        }
        //if first player got 200 point then game will reset and showing the winging msg
        
        else if(scorey == 200){
            reset();
            showClashMsgY(g);
        }



       //clen the graphics 

        g.dispose();


    }

    @SuppressWarnings("Duplicates")


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
        
        //if press right key snake will go towards right side

        if (right1) {
            for (int r = snakeLen1 - 1; r >= 0; r--) {
                snakeY1Len[r + 1] = snakeY1Len[r];

            }

            for (int r = snakeLen1; r >= 0; r--) {
                if (r == 0) {
                    snakeX1Len[r] = snakeX1Len[r] + 25;

                } else {
                    snakeX1Len[r] = snakeX1Len[r - 1];

                }
                if (snakeX1Len[r] > 850) {
                    snakeX1Len[r] = 25;

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


        if (left1) {

            for (int r = snakeLen1 - 1; r >= 0; r--) {
                snakeY1Len[r + 1] = snakeY1Len[r];

            }

            for (int r = snakeLen1; r >= 0; r--) {
                if (r == 0) {
                    snakeX1Len[r] = snakeX1Len[r] - 25;

                } else {
                    snakeX1Len[r] = snakeX1Len[r - 1];

                }
                if (snakeX1Len[r] < 25) {
                    snakeX1Len[r] = 850;

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


        if (up1) {

            for (int r = snakeLen1 - 1; r >= 0; r--) {
                snakeX1Len[r + 1] = snakeX1Len[r];

            }

            for (int r = snakeLen1; r >= 0; r--) {
                if (r == 0) {
                    snakeY1Len[r] = snakeY1Len[r] - 25;

                } else {
                    snakeY1Len[r] = snakeY1Len[r - 1];

                }
                if (snakeY1Len[r] < 75) {
                    snakeY1Len[r] = 625;

                }

            }
            repaint();


        }

        ////if press down key snake will go towards down side
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

        if (down1) {

            for (int r = snakeLen1 - 1; r >= 0; r--) {
                snakeX1Len[r + 1] = snakeX1Len[r];

            }

            for (int r = snakeLen1; r >= 0; r--) {
                if (r == 0) {
                    snakeY1Len[r] = snakeY1Len[r] + 25;

                } else {
                    snakeY1Len[r] = snakeY1Len[r - 1];

                }
                if (snakeY1Len[r] > 625) {
                    snakeY1Len[r] = 75;

                }

            }
            repaint();


        }




    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        //if pressed space then every will reset 

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            moves = 0;
            scorex = 0;
            scorey = 0;
            snakeLen = 3;
            snakeLen1 = 3;
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

            case KeyEvent.VK_D:
                moves++;
                right1 = true;
                if (left1) {
                    right1 = false;
                    left1 = true;
                }
                up1 = false;
                down1 = false;
                break;
                //
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

            case KeyEvent.VK_A:
                moves++;
                left1 = true;
                if (right1) {
                    right1 = true;
                    left1 = false;
                }
                up1 = false;
                down1 = false;
                break;
                //
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

            case KeyEvent.VK_W:
                moves++;
                up1 = true;
                if (down1) {
                    up1 = false;
                    down1 = true;
                }
                left1 = false;
                right1 = false;
                break;
                //

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

            case KeyEvent.VK_S:

                moves++;
                down1 = true;
                if (up1) {
                    up1 = true;
                    down1 = false;
                }
                left1 = false;
                right1 = false;
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @SuppressWarnings("Duplicates")

    public void showClashMsgX(Graphics g){


        g.setColor(Color.RED);
        g.setFont(new Font("arial",Font.BOLD,30));
        g.drawString("Player 2 Won!!!",330,400);



        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.BOLD,20));
        g.drawString("Space to restart",330,500);

    }

    public void showClashMsgY(Graphics g){


        g.setColor(Color.RED);
        g.setFont(new Font("arial",Font.BOLD,30));
        g.drawString("Player 1 Won!!!",330,400);



        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.BOLD,20));
        g.drawString("Space to restart",350,500);

    }

    public void reset(){
        right = false;
        left = false;
        up = false;
        down = false;

        right1 = false;
        left1 = false;
        up1 = false;
        down1 = false;

        moves = 0;

    } 
}
