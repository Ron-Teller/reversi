/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Others
 */
public abstract class FrameAnimation {
    
    private List<JLabel> animationFrames;
    private int animationLengthMilisec;
    private Timer animaitonTimer;
    private int currentFrame;

    public FrameAnimation(List<JLabel> animationFrames, int animationLengthMilisec) {
        this.animationFrames = animationFrames;
        this.animationLengthMilisec = animationLengthMilisec;
        int frameLengthMilisec = animationLengthMilisec/animationFrames.size();
        animaitonTimer = new Timer(frameLengthMilisec, null);
        currentFrame = 0;
        ActionListener actionListener = createAnimaitonActionListener();
        animaitonTimer.addActionListener(actionListener);
    }
    
    private ActionListener createAnimaitonActionListener() {
        ActionListener actionListener = new ActionListener() {
            private int currentFrame = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                drawFrame(animationFrames.get(currentFrame));
                currentFrame ++;
                if (animationHasEnded()) {
                    animaitonTimer.stop();
                    onAnimationEnd();
                }
            }
            
            private boolean animationHasEnded() {
                return (currentFrame >= animationFrames.size());
            }
        };  
        return actionListener;
    }
    
    public void start() {
        animaitonTimer.start();
    }

    protected abstract void drawFrame(JLabel currentFrame);
    protected abstract void onAnimationEnd();
}
