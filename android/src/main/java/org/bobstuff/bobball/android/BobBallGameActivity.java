package org.bobstuff.bobball.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import org.bobstuff.bobball.core.BobBallGame;

public class BobBallGameActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("org/bobstuff/bobball/resources");
    PlayN.run(new BobBallGame());
  }
}
