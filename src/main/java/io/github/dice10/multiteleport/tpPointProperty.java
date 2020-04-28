package io.github.dice10.multiteleport;

import org.bukkit.entity.Player;

import java.io.*;
import java.security.spec.InvalidKeySpecException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import static org.bukkit.Bukkit.getLogger;

public class tpPointProperty {
    private String path = MultiTeleport.getPlugin(MultiTeleport.class).getDataFolder().getAbsolutePath();
    //    private File file;
    private File folder = new File(path);
    private double numX;
    private double numY;
    private double numZ;
    private String world;
    private String result = null;
    public String NOTP;
    public String HOME;
    public String HOME_NAME;
    public String TP1;
    public String TP1_NAME;
    public String TP2;
    public String TP2_NAME;
    public String TP3 ;
    public String TP3_NAME;

    public void findJSONFile(Player player){
        String safeUUID = safeUUID(player.getUniqueId().toString());
        File file = new File(path+"/"+safeUUID+".json");
        if(!folder.exists()){
            folder.mkdir();
            getLogger().info("ディレクトリを作成しました。");
        }
        if(!file.exists()){
            try {
//                checkDate(player);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void checkProperty(Player player){
        if(!folder.exists()){
            folder.mkdir();
            getLogger().info("ディレクトリを作成しました。");
        }
        String safeUUID = safeUUID(player.getUniqueId().toString());
        Properties settings = new Properties();
        settings.setProperty("name", player.getName());
        settings.setProperty("home", "");
        settings.setProperty("homeName", "");
        settings.setProperty("tp1", "");
        settings.setProperty("tp1name", "");
        settings.setProperty("tp2", "");
        settings.setProperty("tp2name", "");
        settings.setProperty("tp3", "");
        settings.setProperty("tp3name", "");
        settings.setProperty("notp", "");

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path+"/"+safeUUID+".xml");
            settings.storeToXML(out, player.getName()+"TP Property");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String readXML(Player player,String tag){
        String safeUUID = safeUUID(player.getUniqueId().toString());
        Properties settings = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(path+"/"+safeUUID+".xml");
            settings.loadFromXML(in);
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // lang の値を出力
        String result = settings.getProperty(tag);

        return result;
    }

    public boolean writeXML(Player player,String tag,String name){
        String safeUUID = safeUUID(player.getUniqueId().toString());
        try {
            Properties settings = new Properties();
            FileInputStream in = new FileInputStream(path+"/"+safeUUID+".xml");
            settings.loadFromXML(in);
            HOME = settings.getProperty("home");
            HOME_NAME = settings.getProperty("homeName");
            TP1 = settings.getProperty("tp1");
            TP1_NAME = settings.getProperty("tp1name");
            TP2 = settings.getProperty("tp2");
            TP2_NAME = settings.getProperty("tp2name");
            TP3 = settings.getProperty("tp3");
            TP3_NAME = settings.getProperty("tp3name");
            NOTP = settings.getProperty("notp");
            FileOutputStream out = null;
            if(tag.equalsIgnoreCase("home")){
                world = player.getLocation().getWorld().getName();
                numX = player.getLocation().getX();
                numY = player.getLocation().getY();
                numZ = player.getLocation().getZ();
                result = world + ","+numX+","+numY+","+numZ;
                settings.setProperty("home", result);
                settings.setProperty("homeName", name);
                settings.setProperty("tp1", TP1);
                settings.setProperty("tp1name", TP1_NAME);
                settings.setProperty("tp2", TP2);
                settings.setProperty("tp2name", TP2_NAME);
                settings.setProperty("tp3", TP3);
                settings.setProperty("tp3name", TP3_NAME);
                settings.setProperty("notp", NOTP);
            }
            else if(tag.equalsIgnoreCase("tp1")){
                world = player.getLocation().getWorld().getName();
                numX = player.getLocation().getX();
                numY = player.getLocation().getY();
                numZ = player.getLocation().getZ();
                result = world + ","+numX+","+numY+","+numZ;
                settings.setProperty("home", HOME);
                settings.setProperty("homeName", HOME_NAME);
                settings.setProperty("tp1", result);
                settings.setProperty("tp1name", name);
                settings.setProperty("tp2", TP2);
                settings.setProperty("tp2name", TP2_NAME);
                settings.setProperty("tp3", TP3);
                settings.setProperty("tp3name", TP3_NAME);
                settings.setProperty("notp", NOTP);
            }
            else if(tag.equalsIgnoreCase("tp2")){
                world = player.getLocation().getWorld().getName();
                numX = player.getLocation().getX();
                numY = player.getLocation().getY();
                numZ = player.getLocation().getZ();
                result = world + ","+numX+","+numY+","+numZ;
                settings.setProperty("home", HOME);
                settings.setProperty("homeName", HOME_NAME);
                settings.setProperty("tp1", TP1);
                settings.setProperty("tp1name", TP1_NAME);
                settings.setProperty("tp2", result);
                settings.setProperty("tp2name", name);
                settings.setProperty("tp3", TP3);
                settings.setProperty("tp3name", TP3_NAME);
                settings.setProperty("notp", NOTP);
            }
            else if(tag.equalsIgnoreCase("tp3")){
                world = player.getLocation().getWorld().getName();
                numX = player.getLocation().getX();
                numY = player.getLocation().getY();
                numZ = player.getLocation().getZ();
                result = world + ","+numX+","+numY+","+numZ;
                settings.setProperty("home", HOME);
                settings.setProperty("homeName", HOME_NAME);
                settings.setProperty("tp1", TP1);
                settings.setProperty("tp1name", TP1_NAME);
                settings.setProperty("tp2", TP2);
                settings.setProperty("tp2name", TP2_NAME);
                settings.setProperty("tp3", result);
                settings.setProperty("tp3name", name);
                settings.setProperty("notp", NOTP);
            }
            try {
                out = new FileOutputStream(path+"/"+safeUUID+".xml");
                settings.storeToXML(out, player.getName()+"TP Property");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//        settings.setProperty("tp1", "");
//        settings.setProperty("tp1name", "");
//        settings.setProperty("tp2", "");
//        settings.setProperty("tp2name", "");
//        settings.setProperty("tp3", "");
//        settings.setProperty("tp3name", "");
//        settings.setProperty("notp", "");


        return true;
    }

    public String safeUUID(String str){
        String safeUUID = null;
        try {
            safeUUID = safetyUUID.getSafetyUUID(str, "DICE10");
        } catch (InvalidKeySpecException e) {
            safeUUID = str;
        }
        return safeUUID;
    }
}
