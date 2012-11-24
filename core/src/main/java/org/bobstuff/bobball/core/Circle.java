package org.bobstuff.bobball.core;

public class Circle {

	public float x;
	public float y;
	public int v;
	
	public Circle(float x, float y, int v) {
		this.x = x;
		this.y = y;
		this.v = v;
	}
	
	public void reverse() {
		v = v * -1;
	}
	
	public void move() {
		
	}
}
