package com.trinityminecraft.flowermap;

import java.util.Random;

public class WorldgenRandom extends Random {
	private static final long serialVersionUID = 69420L;
	
	public WorldgenRandom() {}
	  
	  public WorldgenRandom(long seed) {
	    super(seed);
	  }
	  
	  public void consumeCount(int count) {
	    for (int i = 0; i < count; i++) {
	      next(1);
	    }
	  }
}