package org.bobstuff.bobball.core;

import playn.core.Font;
import playn.core.PlayN;
import tripleplay.ui.Background;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.layout.AbsoluteLayout;

public class ControlPanel  extends Group {
	
	private Label fpsLabel;
	
	public ControlPanel() {
		super(new AbsoluteLayout());
		
		this.setSize(100, 100);
		
        this.addStyles(Style.BACKGROUND.is(Background.roundRect(0xAA000000, 10)));
		
		Styles bigLabel = Styles.make(
                Style.FONT.is(PlayN.graphics().createFont("Times New Roman", 
                								Font.Style.PLAIN, 32)),
                Style.HALIGN.center,
                Style.COLOR.is(0xffffffff));
		fpsLabel = new Label("");
        fpsLabel.addStyles(bigLabel);
        
        Label fpsDescriptionLabel = new Label("fps");
        fpsDescriptionLabel.addStyles(Style.COLOR.is(0xffffffff), 
        						      Style.HALIGN.center);
        
        this.add(AbsoluteLayout.at(fpsLabel, 0, 10, 100, 40));
        this.add(AbsoluteLayout.at(fpsDescriptionLabel, 0, 45, 100, 20));
	}
	
	public void setFps(int fps) {
		fpsLabel.text.update(String.valueOf(fps));
	}
}
