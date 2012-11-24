package org.bobstuff.bobball.core;

import static playn.core.PlayN.graphics;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ImmediateLayer;
import playn.core.ImmediateLayer.Renderer;
import playn.core.Pointer.Event;
import playn.core.Pointer.Listener;
import playn.core.Surface;
import pythagoras.i.Point;
import tripleplay.game.UIScreen;
import tripleplay.ui.Root;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.Style;
import tripleplay.ui.bgs.BlankBackground;
import tripleplay.ui.layout.AbsoluteLayout;

public class MainScreen extends UIScreen implements Listener {
	private static int SCREEN_WIDTH = 620;
	private static int SCREEN_HEIGHT = 460;

	private static int GAME_WIDTH = 600;
	private static int GAME_HEIGHT = 440;
	
	private static int BACKGROUND_WIDTH = 560;
	private static int BACKGROUND_HEIGHT = 400;
	
	private ControlPanel controlPanel;
	private GameDetails gameDetails;
	private MessageView messageView;
	private Player player = new Player();
	private GameManager gameManager;
	
	private Point initialTouchPoint = null;
	private TouchDirection touchDirection = null;
	
	private GameCanvas gameCanvas;
	
	@Override
	public void wasAdded() {
		super.wasAdded();
		Root root = iface.createRoot(new AbsoluteLayout(), SimpleStyles.newSheet());
		root.addStyles(Style.BACKGROUND.is(new BlankBackground()));
		root.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        
		
        controlPanel = new ControlPanel();
        gameDetails = new GameDetails();
        root.add(AbsoluteLayout.at(gameDetails, 0, 0));

        ImageCache imageCache = ImageCache.getInstance();
        final Image backgroundImage = imageCache.image("background");
        
        ImageLayer bgLayer = graphics().createImageLayer(backgroundImage);
		graphics().rootLayer().addAt(bgLayer,
				((SCREEN_WIDTH - BACKGROUND_WIDTH) / 2.0f),
				((SCREEN_HEIGHT - BACKGROUND_HEIGHT) / 2.0f));
        
		ImmediateLayer canvas = 
        		graphics().createImmediateLayer(GAME_WIDTH, GAME_HEIGHT, 
        				new Renderer() {
        	@Override
        	public void render(Surface surface) {
        		gameCanvas.draw(surface, gameManager, player.getScore());
        		
        	}
        });
		canvas.addListener(this);
		graphics().rootLayer().addAt(canvas, 
								((SCREEN_WIDTH - GAME_WIDTH) / 2.0f),
								((SCREEN_HEIGHT - GAME_HEIGHT) / 2.0f) );

		messageView = new MessageView(this);
		messageView.setVisible(false);
		messageView.layer.setInteractive(false);
		root.add(AbsoluteLayout.at(messageView, (SCREEN_WIDTH-300)/2.0f, (SCREEN_HEIGHT-100)/2.0f));
		
        layer.add(root.layer);
		
		resetGame();
	}
	
	private void resetGame() {
		player.reset();
		gameManager = new GameManager(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		gameManager.init(player.getLevel());
		
		gameCanvas = new GameCanvas();
		
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		elapsedTime = elapsedTime + (int)delta;
		calculFps();
		
		float d = delta / 16.0f;
		int steps = Math.round(d * 3);
		
		Point correctedPoint = null;
		if (initialTouchPoint != null) {
			correctedPoint = new Point();
			correctedPoint.x = (int)(initialTouchPoint.x - ((SCREEN_WIDTH - GAME_WIDTH)/2.0));
			correctedPoint.y = (int)(initialTouchPoint.y - ((SCREEN_HEIGHT - GAME_HEIGHT)/2.0));
		} 
		
		for (int x=0; x < steps && gameManager.hasLivesLeft() && !gameManager.isLevelComplete() && gameManager.hasTimeLeft(); ++x) {
			gameManager.runGameLoop(correctedPoint, touchDirection);
		}
		
		gameDetails.setTimeLeft(gameManager.timeLeft()/1000);
		gameDetails.setLivesLeft(gameManager.getLives());
		gameDetails.setScore(player.getScore());
		gameDetails.setAreaComplete(gameManager.getPercentageComplete());
		
		if (!gameManager.hasLivesLeft() || gameManager.isLevelComplete() || !gameManager.hasTimeLeft()) {
			messageView.setVisible(true);
			messageView.layer.setInteractive(true);
			if (!gameManager.hasLivesLeft() || !gameManager.hasTimeLeft()) {
				messageView.setMessage("You are dead", "Retry");
			} else {
				messageView.setMessage("Well done, you have completed Level " + player.getLevel(), "Next level");
			}
			
		}

	}
	
	public void messageButtonPressed() {
		messageView.setVisible(false);
		messageView.layer.setInteractive(false);
		
		if (gameManager.isLevelComplete()) {
			player.setScore(player.getScore() + ((gameManager.getPercentageComplete() * (gameManager.timeLeft()/10000)) * player.getLevel()));
			player.setLevel(player.getLevel() + 1);
			gameManager.init(player.getLevel());
		} else {
			resetGame();
		}

	}
	
	private int elapsedTime;
	private int lastFpsCount;
	private int fps = 0;
	
	private void calculFps() {
		long duration = elapsedTime - lastFpsCount;
		if(duration < 1000) {
			fps++;
		} else {
			controlPanel.setFps(fps);
			fps = 0;
			lastFpsCount = elapsedTime;
		}
	}
	
	@Override
	public void onPointerEnd(Event event) {
		initialTouchPoint = null;
		touchDirection = null;
	}
	
	@Override
    public void onPointerDrag(Event event) { 
		if (initialTouchPoint != null && touchDirection == null) {
			if (event.x() > (initialTouchPoint.x + 20) || event.x() < initialTouchPoint.x - 20) {
				touchDirection = TouchDirection.HORIZONTAL;
			}
			if (event.y() > (initialTouchPoint.y + 20) || event.y() < initialTouchPoint.y - 20) {
				touchDirection = TouchDirection.VERTICAL;
			}
		}
	}
	
	@Override
	public void onPointerStart(Event event) {
		initialTouchPoint = new Point((int)event.x(), (int)event.y());
	}

	@Override
	public void onPointerCancel(Event event) {
	}

}
