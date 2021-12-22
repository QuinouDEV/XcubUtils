package fr.quinou.xcubutils.commands.MoneySystem;

import fr.quinou.xcubutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        // getcoin (no permissions) retourne thune
        if(sender instanceof Player){
            Player p = (Player) sender;
            if (args.length >=1){

                if(p.hasPermission("op")){
                    Player target  = Bukkit.getPlayerExact(args[0]);
                    if(target == null){
                        sender.sendMessage(ChatColor.RED + "Le Joueuer n'est pas connecté");
                        return false;
                    } else{
                        UUID uuid = target.getUniqueId();
                        String res = main.sqlPlayerListener.getXCoins(uuid);
                        sender.sendMessage("La thune de " + target.getName() + " est de " + ChatColor.RED + res);
                    }
                }else{
                    sender.sendMessage(ChatColor.DARK_RED + "Tu n'as pas la permission de faire ceci");
                }

            }else{


            UUID uuid = p.getUniqueId();

            String res = main.sqlPlayerListener.getXCoins(uuid);
            p.sendMessage(res);
            // getcoin Pseudo (permission op) retourne la thune de l'autre joueur



            }





        }



        return true;
    }
}
