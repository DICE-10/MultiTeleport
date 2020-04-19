package io.github.dice10.multiteleport;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class MultiTeleport extends JavaPlugin {
    private double numX;
    private double numY;
    private double numZ;
    private Location spawnpoint;
    private String str = "Teleport Point";

    public MultiTeleport getPlugin() {
        return this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("プラグインを有効化しました。");
        getServer().getPluginManager().registerEvents(new PluginListener(this), this);
        getServer().getPluginManager().registerEvents(new CustomInventory(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;
        Location location = player.getLocation();

        if (cmd.getName().equalsIgnoreCase("test")) {
            CustomInventory i = new CustomInventory();
            i.newInventory(player, 1);
        }
        if (cmd.getName().equalsIgnoreCase("getTpItem") && sender.hasPermission("MultiTeleport.getTpItem")) {
            ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            itemMeta.setDisplayName("TP-Portal");
            itemMeta.setLocalizedName("MultiTeleport");
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(itemMeta);
            ((Player) sender).getInventory().setItemInMainHand(item);
        } else if (cmd.getName().equalsIgnoreCase("ntp") && sender.hasPermission("MultiTeleport.ntp")) {
            if (args.length < 3) {
                sender.sendMessage(ChatColor.RED + "座標の引数が足りません!!");
                return false;
            } else {
                if (isNumeric(args[0])) {
                    location.setX(Double.parseDouble(args[0]));
                } else if (args[0].startsWith("~")) {
                    numX = Double.parseDouble(StringUtils.strip(args[0], "~"));
                    location.setX(location.getX() + numX);
                }

                if (isNumeric(args[1])) {
                    location.setY(Double.parseDouble(args[1]));
                } else if (args[1].startsWith("~")) {
                    numY = Double.parseDouble(StringUtils.strip(args[1], "~"));
                    location.setY(location.getY() + numY);
                }

                if (isNumeric(args[2])) {
                    location.setZ(Double.parseDouble(args[2]));
                } else if (args[2].startsWith("~")) {
                    numZ = Double.parseDouble(StringUtils.strip(args[2], "~"));
                    location.setZ(location.getZ() + numZ);
                }
                player.teleport(location);
                return true;
            }

        } else if (cmd.getName().equalsIgnoreCase("mtp") && sender.hasPermission("MultiTeleport.mtp")) {
            if (args[0].equalsIgnoreCase("world")) {
                if (args.length > 0 && args.length < 3) {
                    spawnpoint = getServer().getWorld("world").getSpawnLocation();
                    numX = spawnpoint.getX();
                    numY = spawnpoint.getY();
                    numZ = spawnpoint.getZ();
                } else {
                    numX = Double.parseDouble(args[1]);
                    numY = Double.parseDouble(args[2]);
                    numZ = Double.parseDouble(args[3]);
                }
                player.teleport(new Location(getServer().getWorld("world"), numX, numY, numZ));
                player.sendMessage("オーバーワールドにテレポートしました。[ｘ：" + numX + ",ｙ：" + numY + ",ｚ：" + numZ + "]");
                return true;
            } else if (args[0].equalsIgnoreCase("nether")) {
                if (args.length > 0 && args.length < 3) {
                    spawnpoint = getServer().getWorld("world_nether").getSpawnLocation();
                    numX = spawnpoint.getX();
                    numY = spawnpoint.getY();
                    numZ = spawnpoint.getZ();
                } else {
                    numX = Double.parseDouble(args[1]);
                    numY = Double.parseDouble(args[2]);
                    numZ = Double.parseDouble(args[3]);
                }
                player.teleport(new Location(getServer().getWorld("world_nether"), numX, numY, numZ));
                player.sendMessage("ネザーにテレポートしました。[ｘ：" + numX + ",ｙ：" + numY + ",ｚ：" + numZ + "]");
                return true;
            } else if (args[0].equalsIgnoreCase("end")) {
                if (args.length > 0 && args.length < 3) {
                    spawnpoint = getServer().getWorld("world_the_end").getSpawnLocation();
                    numX = spawnpoint.getX();
                    numY = spawnpoint.getY();
                    numZ = spawnpoint.getZ();
                } else {
                    numX = Double.parseDouble(args[1]);
                    numY = Double.parseDouble(args[2]);
                    numZ = Double.parseDouble(args[3]);
                }
                player.teleport(new Location(getServer().getWorld("world_the_end"), numX, numY, numZ));
                player.sendMessage("エンドにテレポートしました。[ｘ：" + numX + ",ｙ：" + numY + ",ｚ：" + numZ + "]");
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "権限がありません。");
        }
        return false;
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}