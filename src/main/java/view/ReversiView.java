/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import view.board.IBoard;
import view.board.IReversiPiecesFactory;
import view.board.ReversiPiecesFactory;

/**
 *
 * @author Others
 */
public class ReversiView extends ReversiFrame implements IView, IViewCommunication, IViewGameControls, IViewAI{
    
    private IReversiPiecesFactory reversiPiecesFactory = new ReversiPiecesFactory();
    private List<ShowMovesListener> showMovesListener = new ArrayList<>();
    private List<SoundEnabledListener> soundEnabledListener = new ArrayList<>();
    private List<ActionListener> minimaxDepthListeners = new ArrayList<>();
    private List<ActionListener> moveMeListeners = new ArrayList<>();
    private List<ActionListener> resetListeners = new ArrayList<>();
    private List<MessageSubmitListener> messageSubmitListeners = new ArrayList<>();
    private List<ActionListener> closedListeners = new ArrayList<>();
    

    
    @Override
    public IBoard getBoard() {
        return reversiBoard;
    }

    @Override
    public IViewCommunication getCommunication() {
        return this;
    }

    @Override
    public IViewGameControls getGameControls() {
        return this;
    }
    
    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void setStatusBar(String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setScore(Color player, int score) {
         JTextField scoreText = (player.equals(Color.WHITE)) ? whiteScoreText : blackScoreText;
         scoreText.setText(Integer.toString(score));
    }

    @Override
    public void setTurn(Color color) {
        turnLabel.setIcon(reversiPiecesFactory.createPiece(color).getIcon());
    }

    @Override
    public void addShowMovesListener(ShowMovesListener listener) {
        showMovesListener.add(listener);
    }

    @Override
    public void addSoundListener(SoundEnabledListener listener) {
        soundEnabledListener.add(listener);
    }
    
    @Override
    protected void onShowMovesToggled(boolean isEnabled) {
        showMovesListener.stream().forEach(listener -> listener.update(isEnabled));
    }
    
    @Override
    protected void onSoundEnabledToggled(boolean isEnabled) {
        soundEnabledListener.stream().forEach(listener -> listener.update(isEnabled));
    }

    @Override
    protected void onClosed() {
        closedListeners.forEach(listener -> listener.actionPerformed(null));
    }

    @Override
    public boolean isSoundEnabled() {
        return sound.isSelected();
    }

    @Override
    public boolean isShowMovesEnabled() {
        return showMoves.isSelected();
    }

    @Override
    public int getMinimaxDepth() {
        return minimaxDepthSlider.getValue();
    }

    @Override
    public void addMinimaxDepthChangedListener(ActionListener listener) {
        minimaxDepthListeners.add(listener);
    }

    @Override
    public void addMoveMeListener(ActionListener listener) {
        moveMeListeners.add(listener);
    }

    @Override
    public void setMoveMeEnabled(boolean isEnabled) {
        moveMeButton.setEnabled(isEnabled);
    }
    
    @Override
    protected void onMessageSubmitted(String message) {
        messageSubmitListeners.stream().forEach(listener -> listener.submit(message));
    }

    @Override
    protected void onMoveMeButtonPressed() {
        moveMeListeners.stream().forEach(listener -> listener.actionPerformed(null));
    }
    
    @Override
    protected void onResetButtonPressed() {
        resetListeners.stream().forEach(listener -> listener.actionPerformed(null));
    }
    
    @Override
    protected void onMinimaxDepthChanged(int depth) {
        minimaxDepthListeners.stream().forEach(listener -> listener.actionPerformed(null));
    }

    @Override
    public void addMessage(String message) {
        messageTextArea.append(message+System.lineSeparator());
    }

    @Override
    public void addMessageSubmitListener(MessageSubmitListener listener) {
        messageSubmitListeners.add(listener);
    }

    @Override
    public void addResetListener(ActionListener listener) {
         resetListeners.add(listener);
    }

    @Override
    public void setMinimaxDepthEnabled(boolean isEnabled) {
        minimaxDepthSlider.setVisible(isEnabled);
        minimaxLabel.setVisible(isEnabled);
    }

    @Override
    public IViewAI getAI() {
        return this;
    }

    @Override
    public void setResetEnabled(boolean isEnabled) {
        resetButton.setEnabled(isEnabled);
    }

    @Override
    public void addClosedListener(ActionListener listener) {
        closedListeners.add(listener);
    }

    @Override
    public void setHost(Color color) {
        hostColor.setVisible(true);
        hostColorLabel.setVisible(true);
        this.revalidate();
        this.repaint();
        hostColorLabel.setIcon(reversiPiecesFactory.createPiece(color).getIcon());
    }

    @Override
    public void setGameStatus(String status) {
        gameStatus.setText(status);
    }

    @Override
    public void setMinimaxDepth(int depth) {
        minimaxDepthSlider.setValue(depth);
    }
    
}
