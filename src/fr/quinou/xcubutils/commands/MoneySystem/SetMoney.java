package fr.quinou.xcubutils.commands.MoneySystem;

import fr.quinou.xcubutils.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SetMoney implements CommandExecutor {
    public Main main;

    public SetMoney(Main main) {
        this.main = main;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        int amount;

        if(sender instanceof Player){
            Player p = (Player) sender;
            UUID uuid = p.getUniqueId();

            if(args.length>0){
                try{
                    amount = (Integer.parseInt(args[0]));
                    p.sendMessage("Vous aviez " + ChatColor.RED + main.sqlPlayerListener.getXCoins(uuid));
                    main.sqlPlayerListener.setXCoins(uuid, amount);
                    p.sendMessage("Vous avez maintennant" + ChatColor.RED + main.sqlPlayerListener.getXCoins(uuid));
                }catch (NumberFormatException  e){
                    e.printStackTrace();
                }
                // p.sendMessage("Vous aviez " + ChatColor.RED + msys.getXCoins(uuid));

                // p.sendMessage("Vous avez maintennant" + ChatColor.RED + msys.getXCoins(uuid));
            }
            else{
                p.sendMessage(ChatColor.RED + "Veuillez renseigner un argument valide");
            }

        }



        return true;
    }
}
