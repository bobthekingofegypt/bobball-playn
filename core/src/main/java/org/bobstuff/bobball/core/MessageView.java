package org.bobstuff.bobball.core;

import playn.core.Font;
import playn.core.PlayN;
import react.UnitSlot;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.layout.AbsoluteLayout;

public class MessageView extends Group {
	
	private Label message;
	private Button button;
	
	public MessageView(final MainScreen delegate) {
		super(new AbsoluteLayout());
		
		this.setSize(300, 100);
		
        this.addStyles(Style.BACKGROUND.is(Background.roundRect(0xDD0000FF, 10).inset(10)));
		
		Styles bigLabel = Styles.make(
                Style.FONT.is(PlayN.graphics().createFont("Times New Roman", 
                								Font.Style.PLAIN, 15)),
                Style.HALIGN.center,
                Style.COLOR.is(0xffffffff));
		message = new Label("");
        message.addStyles(bigLabel);
        
        this.add(AbsoluteLayout.at(message, 0, 0, 300, 30));
        
        button = new Button("NEXT LEVEL");
        button.setEnabled(true);
        button.clicked().connect(new UnitSlot() { 
        	@Override
            public void onEmit () {
        		PlayN.log().debug("T");
        		delegate.messageButtonPressed();
            }
        });
        this.add(AbsoluteLayout.at(button, 80, 35, 140, 25));
	}
	
	public void setMessage(String messageText, String buttonText) {
		message.text.update(messageText);
		button.text.update(buttonText);
	}
}
