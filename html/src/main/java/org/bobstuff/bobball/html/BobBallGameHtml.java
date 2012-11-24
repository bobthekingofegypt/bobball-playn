package org.bobstuff.bobball.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import org.bobstuff.bobball.core.BobBallGame;

public class BobBallGameHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("bobball/");
    platform.graphics().setSize(620, 460);
    PlayN.run(new BobBallGame());
  }
}
