package org.bobstuff.bobball.core;

import java.util.HashMap;
import java.util.Map;

import playn.core.Image;
import playn.core.PlayN;

public class ImageCache {

	private static ImageCache cache = new ImageCache();
	
	private Map<String, Image> images = new HashMap<String, Image>();
	
	public static ImageCache getInstance() {
		return cache;
	}
	
	public void addImage(String key, String name, ProgressAssetWatcher assetWatcher) {
		Image image = PlayN.assets().getImage(name);
		assetWatcher.add(image);
		images.put(key, image);
	}
	
	public Image image(String key) {
		return images.get(key);
	}
}
