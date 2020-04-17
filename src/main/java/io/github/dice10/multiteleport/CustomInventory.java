package io.github.dice10.multiteleport;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static net.wesjd.anvilgui.AnvilGUI.*;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

//import org.bukkit.block.Container;

public class CustomInventory implements Listener {
    private String str = "Teleport Point.";
    private String str2 = "Set Teleport Point.";
    private MultiTeleport plugin = MultiTeleport.getPlugin(MultiTeleport.class);
    private double numX;
    private double numY;
    private double numZ;
    private Location spawnpoint;

    public void newInventory(Player player,int page){
        Collection players = getServer().getOnlinePlayers();
        ArrayList<Player> playerlist = new ArrayList<>(players);
        Inventory i = null;
        int count = 0;
        int inventoryCount = 0;
        int online = players.size();
        tpPointJson tpPoint = new tpPointJson();
        tpPoint.JSONRead(player,"name");

        if(page == 1) {
            inventoryCount = 27;
            i = plugin.getServer().createInventory(player, inventoryCount, ChatColor.DARK_GREEN + str);
        }
        else if(page == 2){
            count = online / 9;
            inventoryCount = 9 * (count + 2);
            i = plugin.getServer().createInventory(player, inventoryCount, ChatColor.DARK_GREEN + str);
        }
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
        prev.setItemMeta(prevMeta);


        ItemStack empty = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE,1);
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

        ItemStack homeIcon = new ItemStack(Material.BIRCH_DOOR,1);
        ItemMeta homeIconMeta = homeIcon.getItemMeta();
        homeIconMeta.setDisplayName("Home");
        homeIcon.setItemMeta(homeIconMeta);

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
                    case 9:
                        i.setItem(k,homeIcon);
                        break;
                    case 26:
                        i.setItem(k, next);
                        break;
                    default:
                        i.setItem(k,empty);
                }
            }
            else if(page == 2){
                if(k == inventoryCount -1){
                    i.setItem(k, next);
                }
                else if(((count + 1) * 9) == k){
                    i.setItem(k, prev);
                }
                else if(k==0){
                    i.setItem(k, head);
                }
                else{
                    i.setItem(k,empty);
                }
            }
            player.openInventory(i);
        }
    }
    @EventHandler
    public void InventClick(InventoryClickEvent event) throws IOException {
        Player player = (Player) event.getWhoClicked();
        int slot = event.getRawSlot();
        ClickType click = event.getClick();
        Inventory open = event.getClickedInventory();
        ItemStack clicked = event.getCurrentItem();
        try{
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN+str)){
//            event.setCancelled(true);
            if(event.getCurrentItem().getType().equals(Material.GRASS_BLOCK) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Overworld")){
                event.setCancelled(true);
                spawnpoint =  getServer().getWorld("world").getSpawnLocation();
                numX = spawnpoint.getX();
                numY = spawnpoint.getY();
                numZ = spawnpoint.getZ();
                player.teleport(new Location(getServer().getWorld("world"),numX,numY,numZ));
                player.sendMessage("オーバーワールドにテレポートしました。[ｘ："+numX+",ｙ："+numY+",ｚ："+numZ+"]");
            }
            else if(event.getCurrentItem().getType().equals(Material.NETHERRACK) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Nether")){
                event.setCancelled(true);
                spawnpoint =  getServer().getWorld("world_nether").getSpawnLocation();
                numX = spawnpoint.getX();
                numY = spawnpoint.getY();
                numZ = spawnpoint.getZ();
                player.teleport(new Location(getServer().getWorld("world_nether"),numX,numY,numZ));
                player.sendMessage("ネザーにテレポートしました。[ｘ："+numX+",ｙ："+numY+",ｚ："+numZ+"]");
            }
            else if(event.getCurrentItem().getType().equals(Material.END_STONE) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("The_End")){
                event.setCancelled(true);
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
                        break;
                    }
                }
            }
            else if(event.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(" ")){
                event.setCancelled(true);
            }
            else if(event.getCurrentItem().getType().equals(Material.ENCHANTED_BOOK)) {
                String name = event.getCurrentItem().getItemMeta().getDisplayName();
                if(name.equalsIgnoreCase("Next")){
                    CustomInventory i = new CustomInventory();
                    i.newInventory(player,2);
                }
                else if(name.equalsIgnoreCase("Prev")){
                    CustomInventory i = new CustomInventory();
                    i.newInventory(player,1);
                }
                else if(slot >= 0 && slot == 2){
                    try {
                        getLogger().info("anvil");
                    }catch (Exception e){

                    }
                }
            }

            else {
                //何もしない
            }
        }
        else if(event.getSlot() == Slot.INPUT_LEFT){
            player.sendMessage("test");
        }
        else if(event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN+str2) && open.getType().equals(InventoryType.ANVIL)){
            player.sendMessage("test");
            if(clicked != null){
                if(event.getSlotType().equals(InventoryType.SlotType.RESULT)){
                    if(clicked.hasItemMeta() && clicked.getItemMeta().hasDisplayName()){
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.RED+"test");
                    }
                }
                else if(event.getSlotType().equals(InventoryType.SlotType.CONTAINER)){
                    getLogger().info("testa");
                }
            }
            try{
                getLogger().info("test");
                if(event.getRawSlot() == 0 && event.getCurrentItem() == null){
                    getLogger().info("0");
                }
                if(event.getRawSlot() == 1){
                    getLogger().info("1");
                }
                if(event.getRawSlot() == 2){
                    getLogger().info("2");
                }
            }catch (Exception e){
                getLogger().info("エラー");
            }
        }
        }catch (Exception e){

        }
    }

    public void newAnvil(Player player){
        Inventory anvilInventory = null;
        anvilInventory = plugin.getServer().createInventory(player, InventoryType.ANVIL, ChatColor.DARK_GREEN + str2);
//        anvilInventory = plugin.getServer().createInventory(player, InventoryType.ANVIL, ChatColor.DARK_GREEN + str2);
        List<String> lore = new ArrayList<>();
        lore.add("テスト");
        lore.add("TEST");
        ItemStack item = new ItemStack(Material.PAPER,1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("テスト");
        item.setItemMeta(itemMeta);

        anvilInventory.setItem(0,item);
        player.openInventory(anvilInventory);
    }

    public void openAnvil(Player player){
//        Builder builder = new Builder();
//        builder.onClose(p -> {                      //called when the inventory is closing
//            player.sendMessage("You closed the inventory.");
//        })
//                .onComplete((p, text) -> {           //called when the inventory output slot is clicked
//                    if(text.equalsIgnoreCase("you")) {
//                        player.sendMessage("You have magical powers!");
//                        return Response.close();
//                    } else {
//                        return Response.text("Incorrect.");
//                    }
//                })                          //prevents the inventory from being closed
//                .text("What is the meaning of life?")     //sets the text the GUI should start with
//                .item(new ItemStack(Material.GOLD_BLOCK)) //use a custom item for the first slot
//                .title("Enter your answer.")              //set the title of the GUI (only works in 1.14+)
//                .plugin(plugin)                 //set the plugin instance
//                .open(player);
    }
//
//    private static void newContainer(EntityPlayer entPlayer, Container container, String name, String txt){
//
//        Container cont = CraftEventFactory.callInventoryOpenEvent(entPlayer,container);
//
//        int nextContainerID = entPlayer.nextContainerCounter();
//
//        cont.windowId = nextContainerID;
//        cont.addSlotListener(entPlayer);
//        cont.checkReachable = false;
//
//        entPlayer.activeContainer = cont;
//
//        entPlayer.playerConnection.sendPacket(new PacketPlayOutOpenWindow(nextContainerID,name,new ChatComponentText(txt)));
//    }
}
