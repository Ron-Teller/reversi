/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.game_request;

import view.*;

/**
 *
 * @author Others
 */
public class GameRequestIconFactory {
    
    public javax.swing.ImageIcon getConnectedIcon() {
        String imgDirPath = "/view/images/game_request/";
        String imgPath = imgDirPath+"p2p.png";
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imgPath));
        return icon;
    }
    
    public javax.swing.ImageIcon getNotConnectedIcon() {
        String imgDirPath = "/view/images/game_request/";
        String imgPath = imgDirPath+"greyscale_p2p.png";
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imgPath));
        return icon;
    }
    
    public javax.swing.ImageIcon getAwaitingGameRequestReplyIcon() {
        String imgDirPath = "/view/images/game_request/";
        String imgPath = imgDirPath+"greyscale_handshake.png";
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imgPath));
        return icon;
    }   
    
    public javax.swing.ImageIcon getGameRequestAcceptedIcon() {
        String imgDirPath = "/view/images/game_request/";
        String imgPath = imgDirPath+"handshake.png";
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imgPath));
        return icon;
    }    
    
    public javax.swing.ImageIcon getGameNotStartedIcon() {
        String imgDirPath = "/view/images/game_request/";
        String imgPath = imgDirPath+"greyscale_joystick.png";
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imgPath));
        return icon;
    }
    
    public javax.swing.ImageIcon getGameStartedIcon() {
        String imgDirPath = "/view/images/game_request/";
        String imgPath = imgDirPath+"joystick.png";
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imgPath));
        return icon;
    }    
}
