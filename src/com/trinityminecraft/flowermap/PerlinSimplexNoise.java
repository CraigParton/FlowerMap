package com.trinityminecraft.flowermap;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PerlinSimplexNoise {
	private final SimplexNoise[] noiseLevels;
	private final double highestFreqValueFactor;
	private final double highestFreqInputFactor;
	
	public PerlinSimplexNoise(WorldgenRandom debug1, int debugx, int debugy) {
		SortedSet<Integer> debug2 = new TreeSet<Integer>();
		debug2.addAll(IntStream.rangeClosed(-debugx, debugy).boxed().collect(Collectors.toSet()));	  
		if (debug2.isEmpty())
			throw new IllegalArgumentException("Need some octaves!");
		
		int debug3 = -debug2.first();
		int debug4 = debug2.last();

		int debug5 = debug3 + debug4 + 1;
		if (debug5 < 1)
			throw new IllegalArgumentException("Total number of octaves needs to be >= 1");

		SimplexNoise debug6 = new SimplexNoise((Random)debug1);
		int debug7 = debug4;

		this.noiseLevels = new SimplexNoise[debug5];
		if (debug7 >= 0 && debug7 < debug5 && debug2.contains(0))
			this.noiseLevels[debug7] = debug6;

		for (int debug8 = debug7 + 1; debug8 < debug5; debug8++) {
			if (debug8 >= 0 && debug2.contains(debug7 - debug8))
				this.noiseLevels[debug8] = new SimplexNoise((Random)debug1);
			else
				debug1.consumeCount(262);
		} 

		if (debug4 > 0) {
			long l = (long)(debug6.getValue(debug6.xo, debug6.yo, debug6.zo) * 9.223372036854776E18D);
			WorldgenRandom debug10 = new WorldgenRandom(l);
			for (int debug11 = debug7 - 1; debug11 >= 0; debug11--) {
				if (debug11 < debug5 && debug2.contains(debug7 - debug11))
					this.noiseLevels[debug11] = new SimplexNoise((Random)debug10);
				else
					debug10.consumeCount(262);
			} 
		} 
		
		this.highestFreqInputFactor = Math.pow(2.0D, debug4);
		this.highestFreqValueFactor = 1.0D / (Math.pow(2.0D, debug5) - 1.0D);
	}
	
	public double getValue(double debug1, double debug3, boolean debug5) {
		double debug6 = 0.0D;
		double debug8 = this.highestFreqInputFactor;
		double debug10 = this.highestFreqValueFactor;

		for (SimplexNoise debug15 : this.noiseLevels) {
			if (debug15 != null)
				debug6 += debug15.getValue(debug1 * debug8 + (debug5 ? debug15.xo : 0.0D), debug3 * debug8 + (debug5 ? debug15.yo : 0.0D)) * debug10;
			debug8 /= 2.0D;
			debug10 *= 2.0D;
		} 

		return debug6;
	}

	public double getSurfaceNoiseValue(double debug1, double debug3, double debug5, double debug7) {
		return getValue(debug1, debug3, true) * 0.55D;
	}
}