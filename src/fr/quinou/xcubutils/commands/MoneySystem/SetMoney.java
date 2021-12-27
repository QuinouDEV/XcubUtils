package fr.quinou.xcubutils.commands.MoneySystem;

import fr.quinou.xcubutils.Main;
import org.bukkit.Bukkit;
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
            if(p.hasPermission("op")){
                UUID uuid = p.getUniqueId();

                if(args.length==0){
                    amount = (Integer.parseInt(args[0]));
                    p.sendMessage("Vous aviez " + ChatColor.RED + main.sqlPlayerListener.getXCoins(uuid));
                    main.sqlPlayerListener.setXCoins(uuid, amount);
                    p.sendMessage("Vous avez maintennant" + ChatColor.RED + main.sqlPlayerListener.getXCoins(uuid));
                }
                else if(args.length ==1){
                    Player target = Bukkit.getPlayer(args[0]);
                    UUID tUuid = target.getUniqueId();
                    amount = (Integer.parseInt(args[1]));
                    target.sendMessage("Vous aviez " + ChatColor.RED + main.sqlPlayerListener.getXCoins(tUuid));
                    main.sqlPlayerListener.setXCoins(tUuid, amount);
                    target.sendMessage("Vous avez maintennant" + ChatColor.RED + main.sqlPlayerListener.getXCoins(tUuid));
                    p.sendMessage("L'argent de" + target.getName() + "est maintenant de " + main.sqlPlayerListener.getXCoins(tUuid));

                }else{
                    p.sendMessage(ChatColor.RED + "Veuillez renseigner un argument valide");
                }

            }else{
                p.sendMessage("Vous n'avez pas la permission");
            }


        }



        return true;
    }
}
