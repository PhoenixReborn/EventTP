package com.github.PhoenixReborn.EventTP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EventTPCommandExecutor implements CommandExecutor
{
	boolean event = false;
	Location loc = null;
	
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("openevent"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player) sender;
				if(player.hasPermission("eventtp.open") || player.isOp())
				{
					if(event == false)
					{
						loc = player.getLocation();
						event = true;
						if(args.length == 0)
						{
							Bukkit.broadcastMessage(ChatColor.GREEN + "An event has begun! Use /joinevent to participate!");
						}
						else if(args.length > 0)
						{
							String eventmsg = "";
							for(int i = 0; i < args.length; i++)
							{
								eventmsg = eventmsg + args[i] + " ";
							}
							Bukkit.broadcastMessage(ChatColor.GREEN + eventmsg);
							Bukkit.broadcastMessage(ChatColor.GREEN + "An event has begun! Use /joinevent to participate!");
						}
					}
					else if(event == true)
					{
						player.sendMessage(ChatColor.RED + "There is already an event going on. Please finish it before starting another!");
					}
				}
				else player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
			}
			else
			{
				sender.sendMessage("This command cannot be executed by the console!");
			}
			return true;
		}
		
		
		else if(cmd.getName().equalsIgnoreCase("closeevent"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player) sender;
				if(player.hasPermission("eventtp.close") || player.isOp())
				{
					if(event == true)
					{
						event = false;
						loc = null;
						Bukkit.broadcastMessage(ChatColor.GREEN + "The event is now finished. Thank you for participating!");
					}
					else if(event == false)
					{
						player.sendMessage(ChatColor.RED + "There are no events to close!");
					}
				}
				else player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
			}
			else
			{
				sender.sendMessage("This command cannot be executed by the console!");
			}
			return true;
		}
		
		
		else if(cmd.getName().equalsIgnoreCase("joinevent"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player) sender;
				if(player.hasPermission("eventtp.join") || player.isOp())
				{
					if(event == true)
					{
						player.teleport(loc);
					}
					else if(event == false)
					{
						player.sendMessage(ChatColor.RED + "There are no events going on at the moment.");
					}
				}
				else player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
			}
			else
			{
				sender.sendMessage("As much as the console would love to join an event, it cannot.");
			}
			return true;
		}
		return false;
	}
}
