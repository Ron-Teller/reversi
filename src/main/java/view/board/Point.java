/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.board;

/**
 *
 * @author Others
 */
public class Point {
    private final int row;
    private final int colomn;
    
	public int getRow() {
		return row;
	}
	
	public int getColomn() {
		return colomn;
	}
	
	public Point(int row, int colomn) {
		super();
		this.row = row;
		this.colomn = colomn;
	}

	@Override
	public String toString() {
		return "Point [row=" + row + ", colomn=" + colomn + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colomn;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (colomn != other.colomn)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

}
