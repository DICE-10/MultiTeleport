package io.github.dice10.multiteleport;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    private String path = MultiTeleport.getPlugin(MultiTeleport.class).getDataFolder().getAbsolutePath();
    private File folder = new File(path);

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

        ItemStack tp1Icon = new ItemStack(Material.MAP,1);
        ItemMeta tp1Meta = tp1Icon.getItemMeta();
        tp1Meta.setDisplayName(tpPoint.JSONRead(player,"tp1name"));
        tp1Meta.addEnchant(Enchantment.ARROW_DAMAGE,1,true);
        tp1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tp1Meta.setLore(Collections.singletonList("tp1"));
        tp1Icon.setItemMeta(tp1Meta);

        ItemStack tp2Icon = new ItemStack(Material.MAP,1);
        ItemMeta tp2Meta = tp2Icon.getItemMeta();
        tp2Meta.setDisplayName(tpPoint.JSONRead(player,"tp2name"));
        tp2Meta.addEnchant(Enchantment.ARROW_DAMAGE,1,true);
        tp2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tp2Meta.setLore(Collections.singletonList("tp2"));
        tp2Icon.setItemMeta(tp2Meta);

        ItemStack tp3Icon = new ItemStack(Material.MAP,1);
        ItemMeta tp3Meta = tp3Icon.getItemMeta();
        tp3Meta.setDisplayName(tpPoint.JSONRead(player,"tp3name"));
        tp3Meta.addEnchant(Enchantment.ARROW_DAMAGE,1,true);
        tp3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tp3Meta.setLore(Collections.singletonList("tp3"));
        tp3Icon.setItemMeta(tp3Meta);


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
//                    case 12:
//                        i.setItem(k, head);
//                        break;
                    case 9:
                        i.setItem(k,homeIcon);
                        break;
                    case 11:
                        i.setItem(k,tp1Icon);
                        break;
                    case 13:
                        i.setItem(k,tp2Icon);
                        break;
                    case 15:
                        i.setItem(k,tp3Icon);
                        break;
                    case 26:
                        i.setItem(k, next);
                        break;
                    default:
                        i.setItem(k,empty);
                }
            }
            else if(page == 2){
                if(playerlist.size() > k){
                    ItemStack p = new ItemStack(Material.PLAYER_HEAD,1);
                    SkullMeta pm = (SkullMeta)p.getItemMeta();
                    pm.setDisplayName(playerlist.get(k).getName());
                    pm.setOwner(playerlist.get(k).getName());
                    p.setItemMeta(pm);
                    i.setItem(k,p);
                }
                else if(((count + 1) * 9) == k){
                    i.setItem(k, prev);
                }
                else{
                    i.setItem(k,empty);
                }
            }
            player.openInventory(i);
        }
    }
    @EventHandler
    public boolean InventClick(InventoryClickEvent event) throws IOException {
        Player player = (Player) event.getWhoClicked();
        int slot = event.getRawSlot();
        ClickType click = event.getClick();
        Inventory open = event.getClickedInventory();
        ItemStack clicked = event.getCurrentItem();
        tpPointJson tpPoint = new tpPointJson();
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
                    event.setCancelled(true);
                    String playerName = event.getCurrentItem().getItemMeta().getDisplayName();
                    Collection players = getServer().getOnlinePlayers();
                    ArrayList<Player> playerlist = new ArrayList<>(players);

                    boolean flg = false;

                    //プレイヤー一覧を調査
                    for(Player p : playerlist) {
                        String fileName = tpPoint.safeUUID(player.getUniqueId().toString()+"notTP");
                        List<String> text = Files.readAllLines(Paths.get(path + "/" + fileName + ".notp"));
                        String Name = tpPoint.safeUUID(p.getUniqueId().toString()+"notTP");
                        List<String> resTxt = Files.readAllLines(Paths.get(path + "/" + Name + ".notp"));


                        // 選択したプレイヤーアイコン名がサーバー内にいるか否か
                        if (p.getName().equalsIgnoreCase(playerName)) {

                            //右クリック時
                            if(event.isRightClick()){
                                File file = new File(path+"/"+fileName+".notp");
                                Path filePath = Paths.get(path+"/"+fileName+".notp");
                                FileWriter fileWrite = new FileWriter(path+"/"+fileName+".notp");
                                ArrayList<String> strArray = new ArrayList<String>();

                                FileReader fileReader = new FileReader(file);
                                BufferedReader bufferedReader = new BufferedReader(fileReader);
                                PrintWriter pw = new PrintWriter(new BufferedWriter(fileWrite));

                                String line;
                                List<String> lineList = new ArrayList<String>();
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
                                getLogger().info(String.valueOf(text.size()));
                                for(String ss:text){
                                    getLogger().info(ss);
                                }
                                while((line = bufferedReader.readLine()) != null){
                                    lineList.add(line);
                                    player.sendMessage(line);
                                }
                                List<String> resList = new ArrayList<String>();
                                resList = text;
                                if(resList.size() == 0){
                                    resList.add(String.valueOf(p.getUniqueId()));
                                }
                                else {
                                    for (String s1 : resList) {
                                        if (s1.equals(String.valueOf(p.getUniqueId()))) {
                                            getLogger().info(">>>"+s1);
                                            resList.remove(s1);
                                            player.sendMessage(ChatColor.GREEN+p.getName()+"からのTPを許可します。");
                                            flg = true;
                                        } else {
                                            resList.add(String.valueOf(p.getUniqueId()));
                                        }
                                    }
                                }
                                if(flg!=true){
                                    player.sendMessage(ChatColor.GOLD +p.getName()+"からのTPを拒否します。");
                                }
                                for(String strList:resList){
                                    pw.println(strList);
                                }
                                pw.close();
//                                getLogger().info("test");
//                                List<MetadataValue> metadata = player.getMetadata(TpFlagMetaData.TELEPORT_FLAG);
//                                if(!player.hasMetadata(TpFlagMetaData.TELEPORT_FLAG)) {
//                                    player.setMetadata(TpFlagMetaData.TELEPORT_FLAG, new FixedMetadataValue(plugin, p.getUniqueId()));
//                                    player.sendMessage(ChatColor.GOLD +p.getName()+"からのTPを拒否します。");
//                                }
//                                else{
//                                    List<MetadataValue> meta = p.getMetadata(TpFlagMetaData.TELEPORT_FLAG);
//                                    //メタデータに登録されているデータを取得
//                                    for(MetadataValue s : meta){
//                                        String str = s.asString();
//                                        //メタデータの値に","が含まれている場合
//                                        if(str.contains(",")){
//                                            //カンマ区切りの配列へ
//                                            strArray = (ArrayList<String>) Arrays.asList(str.split(","));
//                                            for(String s1 : strArray){
//                                                // uuidが含まれていない場合は追加
//                                                if(!s1.equals(p.getUniqueId())){
//                                                    strArray.add(s1);
//                                                    player.sendMessage(ChatColor.GOLD +p.getName()+"からのTPを拒否します。");
//                                                }
//                                                else{
//                                                    strArray.remove(s1);
//                                                    player.sendMessage(ChatColor.GREEN+p.getName()+"からのTPを許可します。");
//                                                }
//                                            }
//                                        }
//                                        else{
//                                            String res = s + ","+p.getUniqueId();
//                                        }
//                                    }
//                                    if(player.getMetadata(TpFlagMetaData.TELEPORT_FLAG).contains(p.getUniqueId())){
//                                        player.getMetadata(TpFlagMetaData.TELEPORT_FLAG).remove(metadata.indexOf(p.getUniqueId()));
//                                        player.sendMessage(ChatColor.GREEN+p.getName()+"からのTPを許可します。");
//                                    }
//                                    else{
//                                        player.sendMessage("test");
//                                        player.getMetadata(TpFlagMetaData.TELEPORT_FLAG);
//                                        player.sendMessage("test2");
//                                        player.sendMessage(ChatColor.GOLD +p.getName()+"からのTPを拒否します。");
//                                    }
//                                }
                                break;
                            }
    //                        player.sendMessage(p.getName());
                            else if(event.isLeftClick()) {
                                getLogger().info(">>>"+String.valueOf(resTxt.size()));
                                if(resTxt.size() != 0) {
                                    for (String res : resTxt) {
                                        getLogger().info(res);
                                        getLogger().info(String.valueOf(player.getUniqueId()));
                                        if (res.equals(String.valueOf(player.getUniqueId()))) {
                                            player.sendMessage(ChatColor.RED + p.getName() + "へのTPは許可されていません。");
                                            p.sendMessage(ChatColor.YELLOW + player.getName() + "があなたへTPしようとしました。");
                                        }
                                        return false;
                                    }
                                }
                                else {
                                    numX = p.getLocation().getX();
                                    numY = p.getLocation().getY();
                                    numZ = p.getLocation().getZ();
                                    String world_name = p.getWorld().getName();
                                    player.teleport(new Location(getServer().getWorld(world_name), numX, numY, numZ));
                                    player.sendMessage(ChatColor.AQUA + p.getName() + "へテレポートしました。");
                                    return false;
                                }
                                    numX = p.getLocation().getX();
                                    numY = p.getLocation().getY();
                                    numZ = p.getLocation().getZ();
                                    String world_name = p.getWorld().getName();
                                    player.teleport(new Location(getServer().getWorld(world_name), numX, numY, numZ));
                                    player.sendMessage(ChatColor.AQUA + p.getName() + "へテレポートしました。1");
//                                List<MetadataValue> meta = p.getMetadata(String.valueOf(player.getUniqueId()));
//                                if(!meta.isEmpty()) {
//                                    if(p.hasMetadata(String.valueOf(player.getUniqueId()))){
//                                        player.sendMessage(ChatColor.RED + p.getName() + "へのTPは許可されていません。");
//                                        p.sendMessage(ChatColor.YELLOW + player.getName() + "があなたへTPしようとしました。");
//                                        break;
//                                    }
//                                }

                            }
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
                }

                else {
                    //何もしない
                }
            }
        }catch (Exception e){

        }
        return false;
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

    public void setPointGUI(Player player){
        Collection players = getServer().getOnlinePlayers();
        ArrayList<Player> playerlist = new ArrayList<>(players);
        Inventory i = null;
        int count = 0;
        int inventoryCount = 0;
        int online = players.size();
        i = plugin.getServer().createInventory(player, 27, ChatColor.DARK_GREEN + str);
        ItemStack head = new ItemStack(Material.PLAYER_HEAD,1);
        SkullMeta headMeta = (SkullMeta)head.getItemMeta();
        headMeta.setDisplayName("DICE_10");
        headMeta.setOwner("DICE_10");
        head.setItemMeta(headMeta);
    }
}
