package io.github.dice10.multiteleport;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PluginListener implements Listener {
    private final MultiTeleport mt;
    public PluginListener(MultiTeleport mt_){
        this.mt = mt_;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // ログインしたプレイヤーにようこそメッセージを表示する
        tpPointJson tpPointJson = new tpPointJson();
        tpPointJson.findJSONFile(event.getPlayer());
        tpPointJson.checkDate(event.getPlayer());
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
}
