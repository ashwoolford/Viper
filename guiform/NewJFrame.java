
package guiform;

import db.Sqlite;
import multiplayer.Gameplay;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import singleplayer.SinglePlayerGameplay;
import sound.PlaySound;


public class NewJFrame extends javax.swing.JFrame {

    public NewJFrame() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SinglePlayerButton = new javax.swing.JButton();
        multiPlayerButton = new javax.swing.JButton();
        highScoresButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        SinglePlayerButton.setText("SinglePlayer");
        SinglePlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SinglePlayerButtonActionPerformed(evt);
            }
        });
        jPanel1.add(SinglePlayerButton);
        SinglePlayerButton.setBounds(220, 210, 150, 30);

        multiPlayerButton.setText("Multi Player");
        multiPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiPlayerButtonActionPerformed(evt);
            }
        });
        jPanel1.add(multiPlayerButton);
        multiPlayerButton.setBounds(220, 260, 150, 30);

        highScoresButton.setText("High Scores");
        highScoresButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highScoresButtonActionPerformed(evt);
            }
        });
        jPanel1.add(highScoresButton);
        highScoresButton.setBounds(220, 310, 150, 30);

        aboutButton.setText("About");
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });
        jPanel1.add(aboutButton);
        aboutButton.setBounds(220, 360, 150, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\josh\\Desktop\\Untitled-1.jpg")); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, -10, 600, 620);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void multiPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiPlayerButtonActionPerformed
        playSound();
        JFrame frame = new JFrame();
        frame.setBounds(10,10,905,700);
        frame.setBackground(Color.DARK_GRAY);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            frame.add(new Gameplay());
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_multiPlayerButtonActionPerformed

    private void SinglePlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SinglePlayerButtonActionPerformed
       //JOptionPane.showMessageDialog(null,"Could not open file", "Error", JOptionPane.ERROR_MESSAGE);
        playSound();
        JFrame frame = new JFrame();
        frame.setBounds(10,10,905,700);
        frame.setBackground(Color.DARK_GRAY);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SinglePlayerGameplay());
    }//GEN-LAST:event_SinglePlayerButtonActionPerformed

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        playSound();
        JOptionPane.showMessageDialog(null,"Viper the snake game is created by Team Debuggers"
                + "\n Team members \n 1.Ashraf \n 2.Kaium \n 3.Rakib",
                "About",JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void highScoresButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highScoresButtonActionPerformed
       try {
            JOptionPane.showMessageDialog(null,new Sqlite().getList().toString(),"Scores",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_highScoresButtonActionPerformed
    public void playSound(){
        PlaySound sound = new PlaySound(new File("buttonclick.wav"));
                Thread th = new Thread(sound);
                th.start();
    }
    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PlaySound sound = new PlaySound(new File("background.wav"));
                Thread th = new Thread(sound);
                th.start();
                new NewJFrame().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SinglePlayerButton;
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton highScoresButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton multiPlayerButton;
    // End of variables declaration//GEN-END:variables
}
