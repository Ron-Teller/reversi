package model;

public enum Color {
	WHITE, BLACK;

	public Color opponent() {
		return (this.equals(WHITE)) ? BLACK : WHITE;
	}
}
