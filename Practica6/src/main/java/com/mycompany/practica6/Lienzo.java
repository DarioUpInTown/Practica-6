/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author juana
 */
public class Lienzo extends javax.swing.JPanel {

    private BufferedImage image = null;
    private BufferedImage displayed = null;
    private File file = null;
    
    public Lienzo() {
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(displayed,0,0,null);
    }
    
    void setFile(File f){
        file = f;
        try {
                URL url = new URL("file:///"+file.getAbsolutePath());
                System.out.println(""+url);
            image = ImageIO.read(url);      
            displayed = image;
            this.setPreferredSize(getPreferredSize());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
        }
        repaint();
    }
    @Override
    public Dimension getPreferredSize() {
        if (image == null) {
             return new Dimension(280, 308);
        } else {
           return new Dimension(image.getWidth(null), image.getHeight(null));
       }
    }
    public void Threshold(int requiredThresholdValue) {
	int height = image.getHeight();
	int width = image.getWidth();
	BufferedImage finalThresholdImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB) ;
	
	int red = 0;
	int green = 0;
	int blue = 0;
	
	for (int x = 0; x < width; x++) {
            try {
                for (int y = 0; y < height; y++) {
                    int color = image.getRGB(x, y);

                    red = (color & 0x00ff0000)  >> 16;
                    green = (color & 0x0000ff00)  >> 8;
                    blue = (color & 0x000000ff)  >> 0;

                    if((red+green+green)/3 < (int) (requiredThresholdValue)) {
                        finalThresholdImage.setRGB(x,y,0<<16|0<<8|0);
                    }
                    else {
                        finalThresholdImage.setRGB(x,y,255<<16|255<<8|255);
                    }			
                }
            } catch (Exception e) {
                     e.getMessage();
            }
	}	
	displayed = finalThresholdImage;
    }
    
    public void save(File outputFile) throws IOException{
        ImageIO.write(displayed, "png",outputFile);
    }
    
    public void exit(){
        Graphics g = this.getGraphics();
        g.clearRect(0, 0, displayed.getWidth(), displayed.getHeight());
        image=null;
        displayed = image;
    }
    
    public boolean imageNull(){
        return image == null; 
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(1024, 768));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
