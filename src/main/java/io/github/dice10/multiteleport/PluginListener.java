package io.github.dice10.multiteleport;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
        event.getPlayer().sendMessage("aaa");
    }
}
