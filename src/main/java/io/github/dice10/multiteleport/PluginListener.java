package io.github.dice10.multiteleport;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class PluginListener implements Listener {
    private final MultiTeleport mt;
    private Random random = new Random();
    private addTpItemRecipe addItem = new addTpItemRecipe();
    private MultiTeleport plugin = MultiTeleport.getPlugin(MultiTeleport.class);
    public PluginListener(MultiTeleport mt_){
        this.mt = mt_;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // ログインしたプレイヤーにようこそメッセージを表示する
        Player player = event.getPlayer();
        tpPointJson tpPointJson = new tpPointJson();
        tpPointProperty tpPointProperty = new tpPointProperty();
//        tpPointJson.findJSONFile(event.getPlayer());
//        tpPointJson.checkDate(event.getPlayer());
        boolean flg = false;
        flg = tpPointProperty.findProperty(event.getPlayer());
        Collection players = getServer().getOnlinePlayers();
        ArrayList<Player> playerlist = new ArrayList<>(players);
        String[] tpFlgList = {TpMetaData.HOME,TpMetaData.HOME_NAME,TpMetaData.TP1,TpMetaData.TP1_NAME,TpMetaData.TP2,TpMetaData.TP2_NAME,TpMetaData.TP3,TpMetaData.TP3_NAME,TpMetaData.NOTP};
        String[] tag = {"home","homeName","tp1","tp1name","tp2","tp2name","tp3","tp3name","notp"};
        int i = 0;
        if(flg){
            for(String tpFlg : tpFlgList){
                flg = player.hasMetadata(tpFlg);
                if(!flg){
                    player.setMetadata(tpFlg,new FixedMetadataValue(plugin,tag[i]));
                }
                i++;
            }
        }
//        event.getPlayer().sendMessage("aaa");
    }

    @EventHandler
    public void  onClick(PlayerInteractEvent event){
        ItemStack i = event.getPlayer().getInventory().getItemInMainHand();
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(i.getType() == Material.END_PORTAL_FRAME
                && i.getItemMeta().getLocalizedName().equalsIgnoreCase("MultiTeleport")){
                CustomInventory inv = new CustomInventory();
                inv.newInventory(event.getPlayer(),1);
            }
        }
    }
    @EventHandler
    public void onMoveItemInventory(BlockPlaceEvent event) {
        if(event.getItemInHand().getType() == Material.END_PORTAL_FRAME
                && event.getItemInHand().getItemMeta().getLocalizedName().equalsIgnoreCase("MultiTeleport")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void craftEvent(CraftItemEvent event) {
        CraftingInventory inventory = event.getInventory();
        int itemsChecked = 0;
        int possibleCreations = 1;
        ItemStack TpItem = null;
        ItemMeta TpItemMeta = null;
        String[] TpItemName = null;
        ItemStack[] st = {};
        if (event.isShiftClick() && !event.getCurrentItem().getItemMeta().getLocalizedName().equals(null) && event.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("MultiTeleport")) {
            TpItemName = event.getCurrentItem().getItemMeta().getDisplayName().split(" ");
            for (ItemStack item : event.getInventory().getMatrix()) {
                if (item != null && !item.getType().equals(Material.AIR)) {
                    if (itemsChecked == 0)
                        possibleCreations = item.getAmount();
                    else
                        possibleCreations = Math.min(possibleCreations, item.getAmount());
                    itemsChecked++;
                }
            }
            int amountOfItems = event.getRecipe().getResult().getAmount() * possibleCreations;
            for(int i=0; i<amountOfItems;i++){
                st = event.getInventory().getMatrix();
                for(ItemStack it : st){
                    it.setAmount(it.getAmount()-1);
                }
            }
            event.getInventory().setMatrix(st);
            for(int i=0;amountOfItems > i; i++){
                ItemStack itemStack1 = new ItemStack(Material.AIR,1);
                if(TpItemName[1].equalsIgnoreCase("I")) {
                    TpItem = addItem.addItemI(1);
                    TpItemMeta = TpItem.getItemMeta();
                    TpItemMeta.setCustomModelData(random.nextInt(999999998) + 1);
                    TpItem.setItemMeta(TpItemMeta);
                }
                else if(TpItemName[1].equalsIgnoreCase("II")) {
                    TpItem = addItem.addItemII(1);
                    TpItemMeta = TpItem.getItemMeta();
                    TpItemMeta.setCustomModelData(random.nextInt(999999998) + 1);
                    TpItem.setItemMeta(TpItemMeta);
                }
                else if(TpItemName[1].equalsIgnoreCase("III")) {
                    TpItem = addItem.addItemIII(1);
                    TpItemMeta = TpItem.getItemMeta();
                    TpItemMeta.setCustomModelData(random.nextInt(999999998) + 1);
                    TpItem.setItemMeta(TpItemMeta);
                }
                else if(TpItemName[1].equalsIgnoreCase("IV")) {
                    TpItem = addItem.addItemIV(1);
                    TpItemMeta = TpItem.getItemMeta();
                    TpItemMeta.setCustomModelData(random.nextInt(999999998) + 1);
                    TpItem.setItemMeta(TpItemMeta);
                }
                else if(TpItemName[1].equalsIgnoreCase("V")) {
                    TpItem = addItem.addItemV(1);
                    TpItemMeta = TpItem.getItemMeta();
                    TpItemMeta.setCustomModelData(random.nextInt(999999998) + 1);
                    TpItem.setItemMeta(TpItemMeta);
                }
                else if(TpItemName[1].equalsIgnoreCase("VI")) {
                    TpItem = addItem.addItemVI(1);
                    TpItemMeta = TpItem.getItemMeta();
                    TpItemMeta.setCustomModelData(random.nextInt(999999998) + 1);
                    TpItem.setItemMeta(TpItemMeta);
                }
                else if(TpItemName[1].equalsIgnoreCase("VII")) {
                    TpItem = addItem.addItemVII(1);
                    TpItemMeta = TpItem.getItemMeta();
                    TpItemMeta.setCustomModelData(random.nextInt(999999998) + 1);
                    TpItem.setItemMeta(TpItemMeta);
                }
                else if(TpItemName[1].equalsIgnoreCase("VIII")) {
                    TpItem = addItem.addItemVIII(1);
                    TpItemMeta = TpItem.getItemMeta();
                    TpItemMeta.setCustomModelData(random.nextInt(999999998) + 1);
                    TpItem.setItemMeta(TpItemMeta);
                }
                else if(TpItemName[1].equalsIgnoreCase("IX")) {
                    TpItem = addItem.addItemIX(1);
                    TpItemMeta = TpItem.getItemMeta();
                    TpItemMeta.setCustomModelData(random.nextInt(999999998) + 1);
                    TpItem.setItemMeta(TpItemMeta);
                }
                else if(TpItemName[1].equalsIgnoreCase("X")) {
                    TpItem = addItem.addItemX(1);
                    TpItemMeta = TpItem.getItemMeta();
                    TpItemMeta.setCustomModelData(random.nextInt(999999998) + 1);
                    TpItem.setItemMeta(TpItemMeta);
                }
                try {
                    event.setCurrentItem(itemStack1);
                    event.getWhoClicked().getInventory().addItem(TpItem);
                }
                catch (Exception e){
                    event.getWhoClicked().getInventory().addItem(TpItem);
                }
            }
        }


//        if(items[0].getType().equals(Material.COAL)&&items[1].getType().equals(Material.COAL)&&items[2].getType().equals(Material.COAL)&&
//           items[3].getType().equals(Material.COAL)&&items[4].getType().equals(Material.ENDER_PEARL)&&items[5].getType().equals(Material.COAL)&&
//           items[6].getType().equals(Material.OBSIDIAN)&&items[7].getType().equals(Material.OBSIDIAN)&&items[8].getType().equals(Material.OBSIDIAN)){
//            //途中
//        }
//        for (ItemStack item : inventory.getMatrix()) {
//            getl
//        }
    }
}
