package fr.quinou.xcubutils.commands.MoneySystem;

import fr.quinou.xcubutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GiveMoney implements CommandExecutor {
    public Main main;

    public GiveMoney(Main main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        int amount;

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("op")) {





                // /givemoney <montant> "op"

                if (args.length == 0) {
                    UUID uuid = p.getUniqueId();
                    amount = (Integer.parseInt(args[0]));
                    p.sendMessage("Vous aviez " + ChatColor.RED + main.sqlPlayerListener.getXCoins(uuid));
                    main.sqlPlayerListener.addXCoin(uuid, amount);
                    p.sendMessage("Vous avez maintennant" + ChatColor.RED + main.sqlPlayerListener.getXCoins(uuid));
                } else if (args.length == 1) {

                    // /givemoney Pseudo <montant> "op"

                    Player target = Bukkit.getPlayerExact(args[0]);
                    amount = (Integer.parseInt(args[1]));
                    if (target == null) {
                        sender.sendMessage(ChatColor.RED + "Le Joueuer n'est pas connecté");
                        return false;
                    } else {
                        UUID tUuid = target.getUniqueId();
                        target.sendMessage("Vous aviez " + ChatColor.RED + main.sqlPlayerListener.getXCoins(tUuid));
                        main.sqlPlayerListener.addXCoin(tUuid, amount);
                        target.sendMessage("Vous avez maintennant" + ChatColor.RED + main.sqlPlayerListener.getXCoins(tUuid));
                        p.sendMessage("Vous venez de give" + args[1] + "d'argent à " + target.getName());
                    }

                } else {
                    p.sendMessage(ChatColor.RED + "Veuillez renseigner un argument valide");
                }
            }
        }
        return true;
    }
}
