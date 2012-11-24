package org.bobstuff.bobball.core;

import playn.core.Font;
import playn.core.PlayN;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.bgs.BlankBackground;
import tripleplay.ui.layout.AbsoluteLayout;

public class GameDetails extends Group {

	private Label timeLeft;
	private Label livesLeft;
	private Label score;
	private Label area;
	
	private int timeLeftCache;
	private int areaCache;
	private int scoreCache;
	private int livesLeftCache;
	
	public GameDetails() {
		super(new AbsoluteLayout());
		
		this.addStyles(Style.BACKGROUND.is(new BlankBackground()));
		Styles labelStyle = Styles.make(
                Style.FONT.is(PlayN.graphics().createFont("Times New Roman", 
                								Font.Style.BOLD, 16)),
                Style.COLOR.is(0xffffffff));
        Label timeLeftLabel = new Label("Time Left: ");
        timeLeftLabel.addStyles(Style.COLOR.is(0xffffffff), 
        						      Style.HALIGN.left);
        timeLeftLabel.addStyles(labelStyle);
        
        timeLeft = new Label("");
        timeLeft.addStyles(Style.COLOR.is(0xffffffff), 
        					      Style.HALIGN.left);
        timeLeft.addStyles(labelStyle);
        
        this.add(AbsoluteLayout.at(timeLeftLabel, 20, 5, 120, 20));
        this.add(AbsoluteLayout.at(timeLeft, 120, 5, 100, 20));
        
        Label livesLeftLabel = new Label("Lives: ");
        livesLeftLabel.addStyles(Style.COLOR.is(0xffffffff), 
        						      Style.HALIGN.right);
        livesLeftLabel.addStyles(labelStyle);
        
        livesLeft = new Label("");
        livesLeft.addStyles(Style.COLOR.is(0xffffffff), 
        					      Style.HALIGN.left);
        livesLeft.addStyles(labelStyle);
        
        this.add(AbsoluteLayout.at(livesLeftLabel, 440, 5, 130, 20));
        this.add(AbsoluteLayout.at(livesLeft, 590, 5, 30, 20));
        
        Label scoreLabel = new Label("Score: ");
        scoreLabel.addStyles(Style.COLOR.is(0xffffffff), 
        						      Style.HALIGN.left);
        scoreLabel.addStyles(labelStyle);
        
        score = new Label("0");
        score.addStyles(Style.COLOR.is(0xffffffff), 
        					      Style.HALIGN.left);
        score.addStyles(labelStyle);
        
        this.add(AbsoluteLayout.at(scoreLabel, 20, 435, 60, 20));
        this.add(AbsoluteLayout.at(score, 80, 435, 70, 20));
        
        Label areaLabel = new Label("Area Complete: ");
        areaLabel.addStyles(Style.COLOR.is(0xffffffff), 
        						      Style.HALIGN.right);
        areaLabel.addStyles(labelStyle);
        
        area = new Label("0");
        area.addStyles(Style.COLOR.is(0xffffffff), 
        					      Style.HALIGN.left);
        area.addStyles(labelStyle);
        
        this.add(AbsoluteLayout.at(areaLabel, 440, 435, 130, 20));
        this.add(AbsoluteLayout.at(area, 590, 435, 40, 20));
	}
	
	public void setTimeLeft(int timeLeftAmount) {
		if (timeLeftAmount != timeLeftCache) {
			timeLeft.text.update(String.valueOf(timeLeftAmount));
			timeLeftCache = timeLeftAmount;
		}
	}
	
	public void setLivesLeft(int livesLeftAmount) {
		if (livesLeftAmount != livesLeftCache) {
			livesLeft.text.update(String.valueOf(livesLeftAmount));
			livesLeftCache = livesLeftAmount;
		}
	}
	
	public void setScore(int scoreAmount) {
		if (scoreAmount != scoreCache) {
			score.text.update(String.valueOf(scoreAmount));
			scoreCache = scoreAmount;
		}
	}
	
	public void setAreaComplete(int areaAmount) {
		if (areaAmount != areaCache) {
			area.text.update(String.valueOf(areaAmount) + "%");
			areaCache = areaAmount;
		}
	}
}
