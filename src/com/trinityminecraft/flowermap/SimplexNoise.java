package com.trinityminecraft.flowermap;

import java.util.Random;

public class SimplexNoise {
	private static final double SQRT_3 = Math.sqrt(3.0D);
	private static final double F2 = 0.5D * (SQRT_3 - 1.0D);
	private static final double G2 = (3.0D - SQRT_3) / 6.0D;
	private static final int[][] GRADIENT = new int[][] {
			{ 1, 1, 0 },
			{ -1, 1, 0 },
			{ 1, -1, 0 },
			{ -1, -1, 0 },
			{ 1, 0, 1 },
			{ -1, 0, 1 },
			{ 1, 0, -1 },
			{ -1, 0, -1 },
			{ 0, 1, 1 },
			{ 0, -1, 1 },
			{ 0, 1, -1 },
			{ 0, -1, -1 },
			{ 1, 1, 0 },
			{ 0, -1, 1 },
			{ -1, 1, 0 },
			{ 0, -1, -1 } };
			
	private final int[] p = new int[512];
	
	public final double xo;
	public final double yo;
	public final double zo;
	
	public SimplexNoise(Random random) {
		this.xo = random.nextDouble() * 256.0D;
		this.yo = random.nextDouble() * 256.0D;
		this.zo = random.nextDouble() * 256.0D;
		
		for (int i = 0; i < 256; i++) {
			this.p[i] = i;
		}
		
		for (int i = 0; i < 256; i++) {
			int debug3 = random.nextInt(256 - i);
			int debug4 = this.p[i];
			this.p[i] = this.p[debug3 + i];
			this.p[debug3 + i] = debug4;
		} 
	}

	private int p(int debug1) {
		return this.p[debug1 & 0xFF];
	}

	private static double dot(int[] debug0, double debug1, double debug3, double debug5) {
		return debug0[0] * debug1 + debug0[1] * debug3 + debug0[2] * debug5;
	}
  
	private double getCornerNoise3D(int debug1, double debug2, double debug4, double debug6, double debug8) {
		double debug10, debug12 = debug8 - debug2 * debug2 - debug4 * debug4 - debug6 * debug6;
		if (debug12 < 0.0D) {
			debug10 = 0.0D;
		} else {
			debug12 *= debug12;
			debug10 = debug12 * debug12 * dot(GRADIENT[debug1], debug2, debug4, debug6);
		} 
		return debug10;
	}


	public double getValue(double debug1, double debug3) {
		int debug19, debug20;
		double debug5 = (debug1 + debug3) * F2;
		int debug7 = floor(debug1 + debug5);
		int debug8 = floor(debug3 + debug5);
		
		double debug9 = (debug7 + debug8) * G2;
		double debug11 = debug7 - debug9;
		double debug13 = debug8 - debug9;
		
		double debug15 = debug1 - debug11;
		double debug17 = debug3 - debug13;
		
		if (debug15 > debug17) {
			debug19 = 1;
			debug20 = 0;
		} else {
			debug19 = 0;
			debug20 = 1;
		}
		
		double debug21 = debug15 - debug19 + G2;
		double debug23 = debug17 - debug20 + G2;
		double debug25 = debug15 - 1.0D + 2.0D * G2;
		double debug27 = debug17 - 1.0D + 2.0D * G2;
		
		int debug29 = debug7 & 0xFF;
		int debug30 = debug8 & 0xFF;
		int debug31 = p(debug29 + p(debug30)) % 12;
		int debug32 = p(debug29 + debug19 + p(debug30 + debug20)) % 12;
		int debug33 = p(debug29 + 1 + p(debug30 + 1)) % 12;
		
		double debug34 = getCornerNoise3D(debug31, debug15, debug17, 0.0D, 0.5D);
		double debug36 = getCornerNoise3D(debug32, debug21, debug23, 0.0D, 0.5D);
		double debug38 = getCornerNoise3D(debug33, debug25, debug27, 0.0D, 0.5D);
		
		return 70.0D * (debug34 + debug36 + debug38);
	}

	public double getValue(double debug1, double debug3, double debug5) {
		int debug30, debug31, debug32, debug33, debug34, debug35;
		double debug7 = 0.3333333333333333D;
		double debug9 = (debug1 + debug3 + debug5) * debug7;
		
		int debug11 = floor(debug1 + debug9);
		int debug12 = floor(debug3 + debug9);
		int debug13 = floor(debug5 + debug9);
		double debug14 = 0.16666666666666666D;
		double debug16 = (debug11 + debug12 + debug13) * debug14;
		
		double debug18 = debug11 - debug16;
		double debug20 = debug12 - debug16;
		double debug22 = debug13 - debug16;
		
		double debug24 = debug1 - debug18;
		double debug26 = debug3 - debug20;
		double debug28 = debug5 - debug22;
		
		if (debug24 >= debug26) {
			if (debug26 >= debug28) {
				debug30 = 1;
				debug31 = 0;
				debug32 = 0;
				debug33 = 1;
				debug34 = 1;
				debug35 = 0;
			} else if (debug24 >= debug28) {
				debug30 = 1;
				debug31 = 0;
				debug32 = 0;
				debug33 = 1;
				debug34 = 0;
				debug35 = 1;
			} else {
				debug30 = 0;
				debug31 = 0;
				debug32 = 1;
				debug33 = 1;
				debug34 = 0;
				debug35 = 1;
			}
		}
		else if (debug26 < debug28) {
			debug30 = 0;
			debug31 = 0;
			debug32 = 1;
			debug33 = 0;
			debug34 = 1;
			debug35 = 1;
		} else if (debug24 < debug28) {
			debug30 = 0;
			debug31 = 1;
			debug32 = 0;
			debug33 = 0;
			debug34 = 1;
			debug35 = 1;
		} else {
			debug30 = 0;
			debug31 = 1;
			debug32 = 0;
			debug33 = 1;
			debug34 = 1;
			debug35 = 0;
		} 
		
		double debug36 = debug24 - debug30 + debug14;
		double debug38 = debug26 - debug31 + debug14;
		double debug40 = debug28 - debug32 + debug14;
		
		double debug42 = debug24 - debug33 + debug7;
		double debug44 = debug26 - debug34 + debug7;
		double debug46 = debug28 - debug35 + debug7;
		
		double debug48 = debug24 - 1.0D + 0.5D;
		double debug50 = debug26 - 1.0D + 0.5D;
		double debug52 = debug28 - 1.0D + 0.5D;
		
		int debug54 = debug11 & 0xFF;
		int debug55 = debug12 & 0xFF;
		int debug56 = debug13 & 0xFF;
		
		int debug57 = p(debug54 + p(debug55 + p(debug56))) % 12;
		int debug58 = p(debug54 + debug30 + p(debug55 + debug31 + p(debug56 + debug32))) % 12;
		int debug59 = p(debug54 + debug33 + p(debug55 + debug34 + p(debug56 + debug35))) % 12;
		int debug60 = p(debug54 + 1 + p(debug55 + 1 + p(debug56 + 1))) % 12;
		
		double debug61 = getCornerNoise3D(debug57, debug24, debug26, debug28, 0.6D);
		double debug63 = getCornerNoise3D(debug58, debug36, debug38, debug40, 0.6D);
		double debug65 = getCornerNoise3D(debug59, debug42, debug44, debug46, 0.6D);
		double debug67 = getCornerNoise3D(debug60, debug48, debug50, debug52, 0.6D);
		
		return 32.0D * (debug61 + debug63 + debug65 + debug67);
	}

	private static int floor(double d) {
		int i = (int)d;
		return (d < i) ? (i - 1) : i;
	}
}