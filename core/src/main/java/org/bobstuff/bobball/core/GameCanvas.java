package org.bobstuff.bobball.core;

import java.util.List;

import org.bobstuff.bobball.android.Rect;

import playn.core.Image;
import playn.core.PlayN;
import playn.core.Surface;

public class GameCanvas {

	
	Image circle = PlayN.assets().getImage("images/circle.png"); 
	
	public GameCanvas() {
	}
	
	public void draw(final Surface canvas, GameManager gameManager, int score) {	
		List<Rect> collisionRects = gameManager.getGrid().getCollisionRects();
		for (int i=0; i<collisionRects.size(); ++i) {
			Rect rect = collisionRects.get(i);
			canvas.setFillColor(0xff000000);
			canvas.fillRect(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
		}

		List<Ball> balls = gameManager.getBalls();
		for (int i=0; i<balls.size(); ++i) {
			Ball ball = balls.get(i);
			canvas.drawImage(circle, ball.getX1(), ball.getY1());
		}

		Bar bar = gameManager.getBar();
		BarSection sectionOne = bar.getSectionOne();
		if (sectionOne != null) {
			canvas.setFillColor(0xbb0000ff);
			Rect sectionOneRect = sectionOne.getFrame();
			canvas.fillRect(sectionOneRect.left, 
					sectionOneRect.top, 
					sectionOneRect.right - (sectionOneRect.left), 
					sectionOneRect.bottom - (sectionOneRect.top)); 
		}

		BarSection sectionTwo = bar.getSectionTwo();
		if (sectionTwo != null) {
			canvas.setFillColor(0xdd8f1919);
			Rect sectionTwoRect = sectionTwo.getFrame();
			canvas.fillRect(sectionTwoRect.left, 
					sectionTwoRect.top, 
					sectionTwoRect.right - (sectionTwoRect.left), 
					sectionTwoRect.bottom - (sectionTwoRect.top)); 
		}
	}
}
