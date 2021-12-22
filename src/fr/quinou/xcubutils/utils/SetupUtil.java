package fr.quinou.xcubutils.utils;

import fr.quinou.xcubutils.Main;
import fr.quinou.xcubutils.commands.MoneySystem.GetMoney;
import fr.quinou.xcubutils.commands.MoneySystem.GiveMoney;
import fr.quinou.xcubutils.commands.MoneySystem.SetMoney;
import fr.quinou.xcubutils.events.onPlayerJoin;
import org.bukkit.plugin.PluginManager;

public class SetupUtil
{
    private final Main main;
    public SetupUtil(Main main)
    {
        this.main = main;
    }
    public void mainEnablePluginFunction()
    {
        main.getSqlManager().connection();
        enableListener();
    }
    public void mainDisablePluginFunction()
    {
        main.getSqlManager().disconnect();
    }
    public void enableListener()
    {
        PluginManager pm = getPluginManager();
        pm.registerEvents(new onPlayerJoin(main), main);
        main.getCommand("getcoin").setExecutor(new GetMoney(main));
        main.getCommand("givemoney").setExecutor(new GiveMoney(main));
        main.getCommand("setmoney").setExecutor(new SetMoney(main));

    }
    public PluginManager getPluginManager()
    {
        PluginManager pm = main.getServer().getPluginManager();
        return pm;
    }
}
