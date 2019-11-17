package sound;

public interface ISound {
	public void playPlayerMove();
	public void playHoverOnOccupiablePoint();
	public void playOptionToggle();
	public void setEnabled(boolean isEnabled);
	public void playMessageNotification();
}
