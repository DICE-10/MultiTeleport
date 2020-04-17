package io.github.dice10.multiteleport;


import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;

import static org.bukkit.Bukkit.getLogger;

public class tpPointJson {
    private String path = MultiTeleport.getPlugin(MultiTeleport.class).getDataFolder().getAbsolutePath();
//    private File file;
    private File folder = new File(path);
    private Gson gson = new Gson();

    public void findJSONFile(Player player){
        String safeUUID = safeUUID(player.getUniqueId().toString());
        File file = new File(path+"/"+safeUUID+".json");
        if(!folder.exists()){
            folder.mkdir();
            getLogger().info("ディレクトリを作成しました。");
        }
        if(!file.exists()){
            try {
                boolean newFile = file.createNewFile();
                getLogger().info("ファイルを作成しました。");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void checkDate(Player player){
//        JSONRead(player);
        String safeUUID = safeUUID(player.getUniqueId().toString());
        File file = new File(path+"/"+safeUUID+".json");
        if(!file.exists()) {
            try {
                JsonWriter writer = new JsonWriter(new FileWriter(file));
                writer.setIndent(" ");  // インデントするようにした

                writer.beginObject();
//            writer.name(player.getUniqueId().toString());
//            writer.beginArray();
//            writer.beginObject();
                writer.name("name").value(player.getName());
                writer.name("home").value("");
                writer.name("homeName").value("");
                writer.name("tp1").value("");
                writer.name("tp1name").value("");
                writer.name("tp2").value("");
                writer.name("tp2name").value("");
                writer.name("tp3").value("");
                writer.name("tp3name").value("");
                writer.endObject();
                getLogger().info("書き込み");
//            writer.endArray();
//            writer.endObject();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void reload(Player player) {
        String safeUUID = safeUUID(player.getUniqueId().toString());
        File file = new File(path+"/"+safeUUID+".json");
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public String JSONRead(Player player,String tag){
        String safeUUID = safeUUID(player.getUniqueId().toString());
        File file = new File(path+"/"+safeUUID+".json");
        String jsonTxt = null;
        try {
            JSONParser jsonParser = new JSONParser();
            Object parsed = jsonParser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) parsed;
            switch (tag){
                case "name": jsonTxt = (String) jsonObject.get("name");
                            break;
                case "home": jsonTxt = (String) jsonObject.get("home");
                            break;
                case "homeName": jsonTxt = (String) jsonObject.get("homeName");
                            break;
                case "tp1": jsonTxt = (String) jsonObject.get("tp1");
                            break;
                case "tp1name": jsonTxt = (String) jsonObject.get("tp1name");
                            break;
                case "tp2": jsonTxt = (String) jsonObject.get("tp2");
                            break;
                case "tp2name": jsonTxt = (String) jsonObject.get("tp2name");
                            break;
                case "tp3": jsonTxt = (String) jsonObject.get("tp3");
                            break;
                case "tp3name": jsonTxt = (String) jsonObject.get("tp3name");
                            break;
                default: jsonTxt = null;
                            break;
            }
            //String name = (String) jsonObject.get("name");
            getLogger().info(jsonTxt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonTxt;
    }

    public void JSONWrite(Player player,String tpLocation,String name,String place){
        String safeUUID = safeUUID(player.getUniqueId().toString());
        File file = new File(path+"/"+safeUUID+".json");
        if(!file.exists()) {
            try {
                JsonWriter writer = new JsonWriter(new FileWriter(file));
                writer.setIndent(" ");  // インデントするようにした

                writer.beginObject();
                if(tpLocation.equalsIgnoreCase("home")){
                    if(!name.equals(null)){
                        writer.name("homeName").value(name);
                    }
                    writer.name("home").value(place);
                }


                writer.name("tp1").value("");
                writer.name("tp1name").value("");
                writer.name("tp2").value("");
                writer.name("tp2name").value("");
                writer.name("tp3").value("");
                writer.name("tp3name").value("");
                writer.endObject();
                getLogger().info("書き込み");
//            writer.endArray();
//            writer.endObject();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
