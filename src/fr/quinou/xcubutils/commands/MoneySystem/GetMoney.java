package fr.quinou.xcubutils.commands.MoneySystem;

import fr.quinou.xcubutils.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GetMoney implements CommandExecutor {

    public Main main;

    public GetMoney(Main main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            UUID uuid = p.getUniqueId();


            String res = main.sqlPlayerListener.getXCoins(uuid);
            p.sendMessage(res);
        }



        return true;
    }
}
