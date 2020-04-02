package com.trinityminecraft.flowermap;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class Flower {
	private static final PerlinSimplexNoise NOISE = new PerlinSimplexNoise(new WorldgenRandom(2345L), 0, 0);
	
	private static String[] displayString = new String[11];
	private static Material[] flowerTypes = new Material[11];
	
	static {
		displayString[0] = ChatColor.YELLOW + "D";
		displayString[1] = ChatColor.DARK_RED + "P";
		displayString[2] = ChatColor.LIGHT_PURPLE + "A";
		displayString[3] = ChatColor.GRAY + "A";
		displayString[4] = ChatColor.DARK_RED + "T";
		displayString[5] = ChatColor.GOLD + "T";
		displayString[6] = ChatColor.GRAY + "T";
		displayString[7] = ChatColor.RED + "T";
		displayString[8] = ChatColor.GRAY + "O";
		displayString[9] = ChatColor.BLUE + "C";
		displayString[10] = ChatColor.WHITE + "L";
		
		flowerTypes[0] = Material.DANDELION;
		flowerTypes[1] = Material.POPPY;
		flowerTypes[2] = Material.ALLIUM;
		flowerTypes[3] = Material.AZURE_BLUET;
		flowerTypes[4] = Material.RED_TULIP;
		flowerTypes[5] = Material.ORANGE_TULIP;
		flowerTypes[6] = Material.WHITE_TULIP;
		flowerTypes[7] = Material.PINK_TULIP;
		flowerTypes[8] = Material.OXEYE_DAISY;
		flowerTypes[9] = Material.CORNFLOWER;
		flowerTypes[10] = Material.LILY_OF_THE_VALLEY;
	}
	
	public static String getDisplayString(int x, int z) {
		return displayString[getIndex(x, z)];
	}
	
	public static Material getFlowerType(int x, int z) {
		return flowerTypes[getIndex(x, z)];
	}
	
    private static int getIndex(int x, int z) {
    	double value = (1.0D + NOISE.getValue(x / 48.0D, z / 48.0D, false)) / 2.0D;
    	
    	if (value < 0.0D)
            value = 0.0D;
          
          if (value > 0.9999D)
            value = 0.9999D;
    	
    	return (int)(value * 11);
    }
}