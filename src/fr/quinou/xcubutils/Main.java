package fr.quinou.xcubutils;

import fr.quinou.xcubutils.sql.SqlPlayerListener;
import fr.quinou.xcubutils.utils.ItemUtil;
import fr.quinou.xcubutils.utils.SetupUtil;
import org.bukkit.plugin.java.JavaPlugin;

import fr.quinou.xcubutils.sql.SqlManager;

public class Main extends JavaPlugin
{
    public SetupUtil setupUtil = new SetupUtil(this);
    public SqlManager sqlManager = new SqlManager(this, "jdbc:mysql://", "127.0.0.1", "xcub", "root", "");
    public SqlPlayerListener sqlPlayerListener = new SqlPlayerListener(this);
    public ItemUtil itemUtil = new ItemUtil();


    public void onEnable()
    {
        setupUtil.mainEnablePluginFunction();
        sqlPlayerListener.createTable();

        getLogger().info("Plugin activé !");
    }
    @Override
    public void onLoad()
    {
        getLogger().info( "Plugin en cours de chargement...");
    }
    @Override
    public void onDisable()
    {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
        setupUtil.mainDisablePluginFunction();
        getLogger().info( "Plugin désactivé !");
    }
    public SetupUtil getSetupUtil()
    {
        return setupUtil;
    }
    public SqlPlayerListener getSqlPlayerListener() {
        return sqlPlayerListener;
    }
    public SqlManager getSqlManager()
    {
        return sqlManager;
    }
    public ItemUtil getItemUtil()
    {
        return itemUtil;
    }

}
