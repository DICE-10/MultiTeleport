package io.github.dice10.multiteleport;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collection;

import static org.bukkit.Bukkit.getServer;

public class CustomInventory implements Listener {
    private String str = "Teleport Point";
    private MultiTeleport plugin = MultiTeleport.getPlugin(MultiTeleport.class);
    private double numX;
    private double numY;
    private double numZ;
    private Location spawnpoint;

    public void newInventory(Player player,int page){
        Collection players = getServer().getOnlinePlayers();
        ArrayList<Player> playerlist = new ArrayList<>(players);

        for(Player p : playerlist){
            player.sendMessage(p.getName());
            player.sendMessage("X座標："+String.valueOf(p.getLocation().getX()));
            player.sendMessage("Y座標："+String.valueOf(p.getLocation().getY()));
            player.sendMessage("Z座標："+String.valueOf(p.getLocation().getZ()));
        }
        int inventoryCount = 27;
        Inventory i = plugin.getServer().createInventory(player,inventoryCount, ChatColor.DARK_GREEN+str);

        ItemStack head = new ItemStack(Material.PLAYER_HEAD,1);
        SkullMeta headMeta = (SkullMeta)head.getItemMeta();
        headMeta.setDisplayName("DICE_10");
        headMeta.setOwner("DICE_10");
        head.setItemMeta(headMeta);

        ItemStack next = new ItemStack(Material.ENCHANTED_BOOK,1);
        ItemMeta nextMeta = next.getItemMeta();
        nextMeta.setDisplayName("Next");
        next.setItemMeta(nextMeta);

        ItemStack prev = new ItemStack(Material.ENCHANTED_BOOK,1);
        ItemMeta prevMeta = next.getItemMeta();
        prevMeta.setDisplayName("Prev");
        prev.setItemMeta(nextMeta);


        ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE,1);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);

        ItemStack worldIcon = new ItemStack(Material.GRASS_BLOCK,1);
        ItemMeta worldIconMeta = worldIcon.getItemMeta();
        worldIconMeta.setDisplayName("Overworld");
        worldIcon.setItemMeta(worldIconMeta);

        ItemStack netherIcon = new ItemStack(Material.NETHERRACK,1);
        ItemMeta netherIconMeta = netherIcon.getItemMeta();
        netherIconMeta.setDisplayName("Nether");
        netherIcon.setItemMeta(netherIconMeta);

        ItemStack endIcon = new ItemStack(Material.END_STONE,1);
        ItemMeta endIconMeta = endIcon.getItemMeta();
        endIconMeta.setDisplayName("The_End");
        endIcon.setItemMeta(endIconMeta);
        for(int k =0;k<inventoryCount;k++) {
            if(page == 1) {
                switch (k) {
                    case 1:
                        i.setItem(k, worldIcon);
                        break;
                    case 3:
                        i.setItem(k, netherIcon);
                        break;
                    case 5:
                        i.setItem(k, endIcon);
                        break;
                    case 12:
                        i.setItem(k, head);
                        break;
                    case 26:
                        i.setItem(k, next);
                        break;
                    default:
                        i.setItem(k,empty);
                }
            }
            /*i.setItem(0, worldIcon);
            i.setItem(2, netherIcon);
            i.setItem(4, endIcon);
            i.setItem(26, next);*/
            player.openInventory(i);
        }
    }
    @EventHandler
    public void InventClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ClickType click = event.getClick();
        Inventory open = event.getClickedInventory();
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN+str)){
            event.setCancelled(true);
            if(event.getCurrentItem().getType().equals(Material.GRASS_BLOCK)){
                spawnpoint =  getServer().getWorld("world").getSpawnLocation();
                numX = spawnpoint.getX();
                numY = spawnpoint.getY();
                numZ = spawnpoint.getZ();
                player.teleport(new Location(getServer().getWorld("world"),numX,numY,numZ));
                player.sendMessage("オーバーワールドにテレポートしました。[ｘ："+numX+",ｙ："+numY+",ｚ："+numZ+"]");
            }
            else if(event.getCurrentItem().getType().equals(Material.NETHERRACK)){
                spawnpoint =  getServer().getWorld("world_nether").getSpawnLocation();
                numX = spawnpoint.getX();
                numY = spawnpoint.getY();
                numZ = spawnpoint.getZ();
                player.teleport(new Location(getServer().getWorld("world_nether"),numX,numY,numZ));
                player.sendMessage("ネザーにテレポートしました。[ｘ："+numX+",ｙ："+numY+",ｚ："+numZ+"]");
            }
            else if(event.getCurrentItem().getType().equals(Material.END_STONE)){
                spawnpoint =  getServer().getWorld("world_the_end").getSpawnLocation();
                numX = spawnpoint.getX();
                numY = spawnpoint.getY();
                numZ = spawnpoint.getZ();
                player.teleport(new Location(getServer().getWorld("world_the_end"),numX,numY,numZ));
                player.sendMessage("エンドにテレポートしました。[ｘ："+numX+",ｙ："+numY+",ｚ："+numZ+"]");
            }
            else if(event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){
                String playerName = event.getCurrentItem().getItemMeta().getDisplayName();
                player.sendMessage(playerName);
                Collection players = getServer().getOnlinePlayers();
                ArrayList<Player> playerlist = new ArrayList<>(players);
                for(Player p : playerlist) {
                    if (p.getName().equalsIgnoreCase(playerName)) {
                        player.sendMessage(p.getName());
                        player.sendMessage("X座標：" + String.valueOf(p.getLocation().getX()));
                        player.sendMessage("Y座標：" + String.valueOf(p.getLocation().getY()));
                        player.sendMessage("Z座標：" + String.valueOf(p.getLocation().getZ()));
                        continue;
                    }
                }
            }

            else {
                //何もしない
            }
        }
    }
}
