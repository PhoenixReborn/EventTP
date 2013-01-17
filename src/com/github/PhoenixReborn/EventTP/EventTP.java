package com.github.PhoenixReborn.EventTP;

import org.bukkit.plugin.java.JavaPlugin;

public class EventTP extends JavaPlugin
{
	public void onEnable()
	{
		EventTPCommandExecutor command = new EventTPCommandExecutor();
		getCommand("openevent").setExecutor(command);
		getCommand("closeevent").setExecutor(command);
		getCommand("joinevent").setExecutor(command);
	}
	
}
