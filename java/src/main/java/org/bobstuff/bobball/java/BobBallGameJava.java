package org.bobstuff.bobball.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import org.bobstuff.bobball.core.BobBallGame;

public class BobBallGameJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("org/bobstuff/bobball/resources");
    platform.graphics().setSize(620, 460);
    PlayN.run(new BobBallGame());
  }
}
