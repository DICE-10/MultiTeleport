package io.github.dice10.multiteleport;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class TpMetaData {
    public static final String TELEPORT_FLAG = "teleportflg";
    public static final String NOTP = "notp";
    public static final String HOME = "home";
    public static final String HOME_NAME = "homeName";
    public static final String TP1 = "tp1";
    public static final String TP1_NAME = "tp1Name";
    public static final String TP2 = "tp2";
    public static final String TP2_NAME = "tp2Name";
    public static final String TP3 = "tp3";
    public static final String TP3_NAME = "tp3Name";

    private MultiTeleport plugin = MultiTeleport.getPlugin(MultiTeleport.class);
    private String temp = null;
    private String result = null;
    private tpPointJson tpPointJson = new tpPointJson();
    private double numX;
    private double numY;
    private double numZ;
    private String world;
    String home;
    String homeName;
    String tp1;
    String tp1Name;
    String tp2;
    String tp2Name;
    String tp3;
    String tp3Name;
    String str;

    public boolean setHome(Player player,String name){
        world = player.getLocation().getWorld().getName();
        numX = player.getLocation().getX();
        numY = player.getLocation().getY();
        numZ = player.getLocation().getZ();
        result = world + "," + numX + "," + numY + "," + numZ;
        player.setMetadata(HOME,new FixedMetadataValue(plugin,result));
        if (name.equals(null)) {
            name = "HOME";
        }
        tpPointJson.JSONWrite(player,"home",name,result);
        player.setMetadata(HOME_NAME,new FixedMetadataValue(plugin,name));
        return true;
    }

    public boolean setTp1(Player player,String name){
        world = player.getLocation().getWorld().getName();
        numX = player.getLocation().getX();
        numY = player.getLocation().getY();
        numZ = player.getLocation().getZ();
        result = world + "," + numX + "," + numY + "," + numZ;
        player.setMetadata(TP1,new FixedMetadataValue(plugin,result));
        if (name.equals(null)) {
            name = "tp1name";
        }
        tpPointJson.JSONWrite(player,"tp1",name,result);
        player.setMetadata(TP1_NAME,new FixedMetadataValue(plugin,name));
        return true;
    }

    public boolean setTp2(Player player,String name){
        world = player.getLocation().getWorld().getName();
        numX = player.getLocation().getX();
        numY = player.getLocation().getY();
        numZ = player.getLocation().getZ();
        result = world + "," + numX + "," + numY + "," + numZ;
        player.setMetadata(TP2,new FixedMetadataValue(plugin,result));
        if (name.equals(null)) {
            name = "tp2name";
        }
        tpPointJson.JSONWrite(player,"tp2",name,result);
        player.setMetadata(TP2_NAME,new FixedMetadataValue(plugin,name));
        return true;
    }

    public boolean setTp3(Player player,String name){
        world = player.getLocation().getWorld().getName();
        numX = player.getLocation().getX();
        numY = player.getLocation().getY();
        numZ = player.getLocation().getZ();
        result = world + "," + numX + "," + numY + "," + numZ;
        player.setMetadata(TP3,new FixedMetadataValue(plugin,result));
        if (name.equals(null)) {
            name = "tp3name";
        }
        tpPointJson.JSONWrite(player,"tp3",name,result);
        player.setMetadata(TP3_NAME,new FixedMetadataValue(plugin,name));
        return true;
    }

    public boolean setNotp(Player player,Player p){
        result = String.valueOf(p.getUniqueId());
        if(player.hasMetadata(NOTP)){
            str = getMetaValue(player.getMetadata(NOTP));
            List<MetadataValue> metadata = player.getMetadata(NOTP);
            if(str.equals(null)){
                // not action.
            }
            if(!str.contains(String.valueOf(p.getUniqueId()))){
                if(str.contains(",")){
                    result = result + "," + str;
                }
                else{
                    result = str;
                }
            }
            else{
                removeNotp(player, String.valueOf(p.getUniqueId()));
                return true;
            }
        }
        player.setMetadata(NOTP,new FixedMetadataValue(plugin,result));
        return false;
    }
    public boolean getNotp(Player p, Player player){
        if(p.hasMetadata(NOTP)){
            List<MetadataValue> metadata = p.getMetadata(NOTP);
            if(metadata.size() == 0){
                // not action.
            }
            for(MetadataValue value : metadata){
                if(value.asString().contains(String.valueOf(player.getUniqueId()))){
                    return true;
                }
                else{
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }
    public boolean removeNotp(Player player,String uuid){
        if(player.hasMetadata(NOTP)){
            str = getMetaValue(player.getMetadata(NOTP));
            List<MetadataValue> metadata = player.getMetadata(NOTP);
            if(str.equals(null)){
                // not action.
            }
            if(str.contains(",")) {
                String[] strArray = str.split(",");
                for (String str : strArray) {
                    if (!str.equalsIgnoreCase(uuid) && result.equals(null)) {
                        result += uuid;
                    } else if (!str.equalsIgnoreCase(uuid)) {
                        result += "," + uuid;
                    }
                }
            }
            else{
                result = null;
            }
        }
        player.setMetadata(NOTP,new FixedMetadataValue(plugin,result));
        return true;
    }

    //Metaデータの取得
    public String getMetaValue(List<MetadataValue> values){
        for (MetadataValue v : values) {
            result = v.asString();
        }
        return result;
    }
}
