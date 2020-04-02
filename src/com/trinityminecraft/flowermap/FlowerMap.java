package com.trinityminecraft.flowermap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

public class FlowerMap extends JavaPlugin implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("flowers")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				World world = player.getWorld();
				if(world.getEnvironment() != Environment.NORMAL) {
					sender.sendMessage(ChatColor.RED + "This command must be run in the overworld");
					return true;
				}
				
				int y = player.getLocation().getBlockY();
				int centerX = player.getLocation().getBlockX();
				int centerZ = player.getLocation().getBlockZ();
				int x1 = centerX - 15;
				int z1 = centerZ - 4;
				int x2 = centerX + 15;
				int z2 = centerZ + 4;
				
				StringBuilder sb = new StringBuilder(1200);
				boolean found = false;
				sb.append(ChatColor.GREEN).append("                                ^ North ^\n");
				
				for(int z = z1; z <= z2; z++) {
					for(int x = x1; x <= x2; x++) {
						if(x == centerX && z == centerZ)
							sb.append(ChatColor.GREEN).append("X ");
						else if(world.getBiome(x, y, z) == Biome.FLOWER_FOREST) {
							sb.append(Flower.getDisplayString(x, z)).append(' ');
							found = true;
						}
						else
							sb.append(ChatColor.DARK_GRAY).append("X ");
					}
					sb.append("\n");
				}
				
				if(found)
					sender.sendMessage(sb.toString());
				else
					sender.sendMessage(ChatColor.RED + "No flower forest biome found in the target area");
			}
			else
				sender.sendMessage(ChatColor.RED + "This command cannot be run from the console");
			
			return true;
		}
		return false;
	}
	
	@Override
	public void onEnable() {
		getCommand("flowers").setExecutor(this);
	}
}