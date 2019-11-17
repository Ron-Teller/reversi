/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;
import view.Color;

/**
 *
 * @author Others
 */
public class ReversiPiecesFactory implements IReversiPiecesFactory{

    @Override
    public JLabel createPiece(Color color) {
        String imgDirPath = "/view/images/pieces/";
        String imgPath = imgDirPath+color.name().toLowerCase()+".png";
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imgPath));
        JLabel label = new JLabel("");
        label.setOpaque(false);
        label.setIcon(icon);
        return label;
    }

    @Override
    public JLabel createTransparentPiece(Color color) {
        String imgDirPath = "/view/images/pieces/";
        String imgPath = imgDirPath+color.name().toLowerCase()+"_transparent.png";
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imgPath));
        JLabel label = new JLabel("");
        label.setOpaque(false);
        label.setIcon(icon);
        return label;
    }

    @Override
    public List<JLabel> createFlipAnimationFrames(Color beforeFlip) {
        List<JLabel> whiteFlipFrames =  new ArrayList<>();
        String framesDirPath = "/view/images/pieces/";
        String framePath;
        javax.swing.ImageIcon frameIcon;
        JLabel frameLabel;
        for (int frame=1; frame<=7; frame++) {
            framePath = framesDirPath+"transition"+frame+".png";
            frameIcon = new javax.swing.ImageIcon(getClass().getResource(framePath));
            frameLabel = new JLabel("");
            frameLabel.setOpaque(false);
            frameLabel.setIcon(frameIcon); 
            whiteFlipFrames.add(frameLabel);
        }
        if (beforeFlip.equals(Color.WHITE)) {
            return whiteFlipFrames;
        } else {
            Collections.reverse(whiteFlipFrames);
            return whiteFlipFrames;
        }
    }

    @Override
    public JLabel createPointHighlight(java.awt.Color color) {
        String colorType = color.equals(java.awt.Color.GREEN) ? "green" : "blue";
        String imgDirPath = "/view/images/highlight/";
        String imgPath = imgDirPath+colorType+".png";
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imgPath));
        JLabel label = new JLabel("");
        label.setOpaque(false);
        label.setIcon(icon);
        return label;
    }
    
}
