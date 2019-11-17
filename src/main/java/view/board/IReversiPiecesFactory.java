/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.board;

import java.util.List;
import javax.swing.JLabel;
import view.Color;

/**
 *
 * @author Others
 */
public interface IReversiPiecesFactory {
    public JLabel createPiece(Color color);
    public JLabel createTransparentPiece(Color color);
    public List<JLabel> createFlipAnimationFrames(Color beforeFlip);
    public JLabel createPointHighlight(java.awt.Color color);
}
