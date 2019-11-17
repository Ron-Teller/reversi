/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Others
 */
public enum Color {
	WHITE, BLACK;

	public Color opposite() {
		return (this.equals(WHITE)) ? BLACK : WHITE;
	}
}
