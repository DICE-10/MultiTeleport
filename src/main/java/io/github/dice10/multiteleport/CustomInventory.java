package io.github.dice10.multiteleport;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
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

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.bukkit.Bukkit.getServer;

//import org.bukkit.block.Container;

public class CustomInventory implements Listener {
    private String str = "Teleport Point.";
    private String str2 = "Set Teleport Point.";
    private MultiTeleport plugin = MultiTeleport.getPlugin(MultiTeleport.class);
    private double numX;
    private double numY;
    private double numZ;
    private String world;
    private String name;
    private Location spawnpoint;
    private String path = MultiTeleport.getPlugin(MultiTeleport.class).getDataFolder().getAbsolutePath();
    private File folder = new File(path);
    private TpMetaData tpMetaData = new TpMetaData();
    private tpPointProperty tpPointProperty = new tpPointProperty();
    private String TP = null;
    private String pointName = null;

    public void newInventory(Player player,int page){
        Collection players = getServer().getOnlinePlayers();
        ArrayList<Player> playerlist = new ArrayList<>(players);
        Inventory i = null;
        String HomeName = null;
        String Tp1Name = null;
        String Tp2Name = null;
        String Tp3Name = null;
        String TP = null;
        int count = 0;
        int inventoryCount = 0;
        int online = players.size();
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


        ItemStack empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);
        ItemStack slotIcon = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE,1);
        ItemMeta slotIconMeta = slotIcon.getItemMeta();
        slotIconMeta.setDisplayName("§9TPポイント名設定スロット");
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dこのスロットに名前付きのアイテムを入れて");
        loresList.add("§d対象のTP先のアイコンを右クリック");
        loresList.add("§dしてください。");
        slotIconMeta.setLore(loresList);
        slotIcon.setItemMeta(slotIconMeta);
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
        if(tpPointProperty.readXML(player,"homeName") != null){
//        if(player.hasMetadata(TpMetaData.HOME_NAME)){
//            HomeName=tpMetaData.getMetaValue(player.getMetadata(TpMetaData.HOME_NAME));
            HomeName = tpPointProperty.readXML(player,"homeName");
            if(HomeName == null || HomeName == "" || HomeName.length() <=0){
                HomeName = "HOME";
            }
        }
        else{
            HomeName = "HOME";
        }
        homeIconMeta.setDisplayName(HomeName);
        homeIconMeta.setLocalizedName("HOME_ICON");
        homeIcon.setItemMeta(homeIconMeta);

        ItemStack tp1Icon = new ItemStack(Material.MAP,1);
        ItemMeta tp1Meta = tp1Icon.getItemMeta();
        if(tpPointProperty.readXML(player,"tp1name") != null){
            Tp1Name = tpPointProperty.readXML(player,"tp1name");
            if(Tp1Name == null || Tp1Name == "" || Tp1Name.length() <= 0){
                Tp1Name = "tp1name";
            }
        }
        else{
            Tp1Name = "tp1name";
        }
        tp1Meta.setDisplayName(Tp1Name);
        tp1Meta.addEnchant(Enchantment.ARROW_DAMAGE,1,true);
        tp1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tp1Meta.setLocalizedName("tp1");
        tp1Meta.setLore(Collections.singletonList("tp1"));
        tp1Icon.setItemMeta(tp1Meta);

        ItemStack tp2Icon = new ItemStack(Material.MAP,1);
        ItemMeta tp2Meta = tp2Icon.getItemMeta();
        if(tpPointProperty.readXML(player,"tp2name") != null){
            Tp2Name = tpPointProperty.readXML(player,"tp2name");
            if(Tp2Name == null || Tp2Name == "" || Tp2Name.length() <= 0){
                Tp2Name = "tp2name";
            }
        }
        else{
            Tp2Name = "tp2name";
        }
        tp2Meta.setDisplayName(Tp2Name);
        tp2Meta.addEnchant(Enchantment.ARROW_DAMAGE,1,true);
        tp2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tp2Meta.setLocalizedName("tp2");
        tp2Meta.setLore(Collections.singletonList("tp2"));
        tp2Icon.setItemMeta(tp2Meta);

        ItemStack tp3Icon = new ItemStack(Material.MAP,1);
        ItemMeta tp3Meta = tp3Icon.getItemMeta();
        if(tpPointProperty.readXML(player,"tp3name") != null){
            Tp3Name = tpPointProperty.readXML(player,"tp3name");
            if(Tp3Name == null || Tp3Name == "" || Tp3Name.length() <= 0){
                Tp3Name = "tp3name";
            }
        }
        else{
            Tp3Name = "tp3name";
        }
        tp3Meta.setDisplayName(Tp3Name);
        tp3Meta.addEnchant(Enchantment.ARROW_DAMAGE,1,true);
        tp3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tp3Meta.setLocalizedName("tp3");
        tp3Meta.setLore(Collections.singletonList("tp3"));
        tp3Icon.setItemMeta(tp3Meta);


        for(int k =0;k<inventoryCount;k++) {
            if(page == 1) {
                switch (k) {
                    case 0:
                        i.setItem(k, worldIcon);
                        break;
                    case 1:
                        i.setItem(k, netherIcon);
                        break;
                    case 2:
                        i.setItem(k, endIcon);
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 14:
                    case 16:
                    case 23:
                    case 24:
                    case 25:
                        i.setItem(k,slotIcon);
                        break;
                    case 9:
                        i.setItem(k,homeIcon);
                        break;
                    case 10:
                        i.setItem(k,tp1Icon);
                        break;
                    case 11:
                        i.setItem(k,tp2Icon);
                        break;
                    case 12:
                        i.setItem(k,tp3Icon);
                        break;
                    case 15:
                        i.setItem(k,null);
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
        Map<String,Object> map = player.serialize();
        TpMetaData tpMetaData = new TpMetaData();
        ItemStack tpItem = null;
        ItemMeta tpItemMeta = null;
        List<String> loresList = null;
        String num = null;
        int limit = 0;
        if(player.getInventory().getItemInMainHand().getType().equals(Material.END_PORTAL_FRAME)){
            tpItem = player.getInventory().getItemInMainHand();
            tpItemMeta = tpItem.getItemMeta();
            loresList = tpItemMeta.getLore();
            num = loresList.get(1);
            num = num.replaceAll("[§][0-9,a-f]","");
            limit = Integer.parseInt(num);
        }
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
                    if(limit >0) {
                        player.sendMessage("オーバーワールドにテレポートしました。[ｘ：" + numX + ",ｙ：" + numY + ",ｚ：" + numZ + "]");
                        limit -= 1;
                        if(limit <= 0){
                            player.getInventory().setItemInMainHand(null);
                            player.playSound(player.getLocation(), Sound.valueOf("BLOCK_GLASS_BREAK"),1.0f,1.0f);
                            player.sendMessage("§c"+tpItemMeta.getDisplayName()+"が壊れました。");
                            return false;
                        }
                        if(limit <= 5){
                            loresList.set(1, "§c"+limit);
                        }
                        else if(limit <= 10){
                            loresList.set(1, "§e"+limit);
                        }
                        else{
                            loresList.set(1, "§f"+limit);
                        }

                        tpItemMeta.setLore(loresList);
                        tpItem.setItemMeta(tpItemMeta);
                        player.sendMessage("残り使用回数§c"+limit+"回");
                    }

                }
                else if(event.getCurrentItem().getType().equals(Material.NETHERRACK) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Nether")){
                    event.setCancelled(true);
                    spawnpoint =  getServer().getWorld("world_nether").getSpawnLocation();
                    numX = spawnpoint.getX();
                    numY = spawnpoint.getY();
                    numZ = spawnpoint.getZ();
                    player.teleport(new Location(getServer().getWorld("world_nether"),numX,numY,numZ));
                    if(limit >0) {
                        limit -= 1;
                        player.sendMessage("ネザーにテレポートしました。[ｘ：" + numX + ",ｙ：" + numY + ",ｚ：" + numZ + "]");
                        if(limit <= 0){
                            player.getInventory().setItemInMainHand(null);
                            player.playSound(player.getLocation(), Sound.valueOf("BLOCK_GLASS_BREAK"),1.0f,1.0f);
                            player.sendMessage("§c"+tpItemMeta.getDisplayName()+"が壊れました。");
                            return false;
                        }
                        if(limit <= 5){
                            loresList.set(1, "§c"+limit);
                        }
                        else if(limit <= 10){
                            loresList.set(1, "§e"+limit);
                        }
                        else{
                            loresList.set(1, "§f"+limit);
                        }
                        tpItemMeta.setLore(loresList);
                        tpItem.setItemMeta(tpItemMeta);
                        player.sendMessage("残り使用回数§c"+limit+"回");
                    }
                }
                else if(event.getCurrentItem().getType().equals(Material.END_STONE) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("The_End")){
                    event.setCancelled(true);
                    spawnpoint =  getServer().getWorld("world_the_end").getSpawnLocation();
                    
                    numX = spawnpoint.getX();
                    numY = spawnpoint.getY();
                    numZ = spawnpoint.getZ();
                    player.teleport(new Location(getServer().getWorld("world_the_end"),numX,numY,numZ));
                    if(limit >0) {
                        limit -= 1;
                        player.sendMessage("エンドにテレポートしました。[ｘ：" + numX + ",ｙ：" + numY + ",ｚ：" + numZ + "]");
                        if(limit <= 0){
                            player.getInventory().setItemInMainHand(null);
                            player.playSound(player.getLocation(), Sound.valueOf("BLOCK_GLASS_BREAK"),1.0f,1.0f);
                            player.sendMessage("§c"+tpItemMeta.getDisplayName()+"が壊れました。");
                            return false;
                        }
                        if(limit <= 5){
                            loresList.set(1, "§c"+limit);
                        }
                        else if(limit <= 10){
                            loresList.set(1, "§e"+limit);
                        }
                        else{
                            loresList.set(1, "§f"+limit);
                        }
                        tpItemMeta.setLore(loresList);
                        tpItem.setItemMeta(tpItemMeta);
                        player.sendMessage("残り使用回数§c"+limit+"回");
                    }
                }
                else if(event.getCurrentItem().getType().equals(Material.BIRCH_DOOR) && event.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("HOME_ICON")){
                    event.setCancelled(true);

                    if(event.isRightClick()) {
                        world = player.getWorld().getName();
                        numX = player.getLocation().getX();
                        numY = player.getLocation().getY();
                        numZ = player.getLocation().getZ();
                        if(event.getInventory().getItem(15) == null) {
                            name = "HOME";
                        }
                        else{
                            name = event.getInventory().getItem(15).getItemMeta().getDisplayName();
                            if(name.equals(null)){
                                name = "HOME";
                            }
                            player.playSound(player.getLocation(), Sound.valueOf("BLOCK_ANVIL_USE"),1.0f,1.0f);
                            event.getInventory().setItem(15,null);
                        }
                        player.sendMessage("{§a"+world+",§b"+numX+","+numY+","+numZ+"§a}§e{§6"+name+"§e}");
//                        tpMetaData.setHome(player,name);
                        tpPointProperty.writeXML(player,"home",name);
                    }
                    else{
                        TP = tpPointProperty.readXML(player,"home");
                        pointName = tpPointProperty.readXML(player,"homeName");
                        player.sendMessage(TP);
                        String[] tpArray = TP.split(",");
                        world = tpArray[0];
                        numX = Double.parseDouble(tpArray[1]);
                        numY = Double.parseDouble(tpArray[2]);
                        numZ = Double.parseDouble(tpArray[3]);
                        player.teleport(new Location(getServer().getWorld(world),numX,numY,numZ));
                        if(limit >0) {
                            limit -= 1;
                            player.sendMessage("§b" + pointName + "§fにテレポートしました。[ｘ：" + numX + ",ｙ：" + numY + ",ｚ：" + numZ + "]");
                            if(limit <= 0){
                                player.getInventory().setItemInMainHand(null);
                                player.playSound(player.getLocation(), Sound.valueOf("BLOCK_GLASS_BREAK"),1.0f,1.0f);
                                player.sendMessage("§c"+tpItemMeta.getDisplayName()+"が壊れました。");
                                return false;
                            }
                            if(limit <= 5){
                                loresList.set(1, "§c"+limit);
                            }
                            else if(limit <= 10){
                                loresList.set(1, "§e"+limit);
                            }
                            else{
                                loresList.set(1, "§f"+limit);
                            }
                            tpItemMeta.setLore(loresList);
                            tpItem.setItemMeta(tpItemMeta);
                            player.sendMessage("残り使用回数§c"+limit+"回");
                        }
                    }
                }
                else if(event.getCurrentItem().getType().equals(Material.MAP) && event.getCurrentItem().getItemMeta().getLocalizedName().contains("tp")){
                    event.setCancelled(true);
                    String localName = event.getCurrentItem().getItemMeta().getLocalizedName();
                    String tag = null;
                    String pointTag = null;
                    String nameTag = null;
                    if(event.isRightClick()) {
                        player.sendMessage("右クリック");
                        world = player.getWorld().getName();
                        numX = player.getLocation().getX();
                        numY = player.getLocation().getY();
                        numZ = player.getLocation().getZ();
                        if(event.getInventory().getItem(15) == null) {
                            switch (localName){
                                case "tp1": name = "tp1";
                                            break;
                                case "tp2": name = "tp2";
                                    break;
                                case "tp3": name = "tp3";
                                    break;
                            }
                        }
                        else{
                            name = event.getInventory().getItem(15).getItemMeta().getDisplayName();
                            if(name.equals(null)){
                                switch (localName){
                                    case "tp1": name = "tp1";
                                        break;
                                    case "tp2": name = "tp2";
                                        break;
                                    case "tp3": name = "tp3";
                                        break;
                                }
                            }
                            switch (localName){
                                case "tp1": tag = "tp1";
                                    break;
                                case "tp2": tag = "tp2";
                                    break;
                                case "tp3": tag = "tp3";
                                    break;
                            }
                            player.playSound(player.getLocation(), Sound.valueOf("BLOCK_ANVIL_USE"),1.0f,1.0f);
                            event.getInventory().setItem(15,null);

                            tpPointProperty.writeXML(player,tag,name);
                        }
                        switch (localName){
                            case "tp1": tpMetaData.setTp1(player,name);;
                                break;
                            case "tp2": tpMetaData.setTp2(player,name);;
                                break;
                            case "tp3": tpMetaData.setTp3(player,name);;
                                break;
                        }
                        player.sendMessage("{§a"+world+",§b"+numX+","+numY+","+numZ+"§a}§e{§6"+name+"§e}");
                    }
                    else{
                        switch (localName){
                            case "tp1":
                            case "tp2":
                            case "tp3":  pointTag = tpPointProperty.readXML(player,localName);
                                         nameTag = tpPointProperty.readXML(player,localName+"name");
                                break;
                        }
                        TP = pointTag;
                        pointName = nameTag;
                        player.sendMessage(TP);
                        String[] tpArray = TP.split(",");
                        world = tpArray[0];
                        numX = Double.parseDouble(tpArray[1]);
                        numY = Double.parseDouble(tpArray[2]);
                        numZ = Double.parseDouble(tpArray[3]);
                        player.teleport(new Location(getServer().getWorld(world),numX,numY,numZ));
                        if(limit >0) {
                            limit -= 1;
                            player.sendMessage("§b" + pointName + "§fにテレポートしました。[ｘ：" + numX + ",ｙ：" + numY + ",ｚ：" + numZ + "]");
                            if(limit <= 0){
                                player.getInventory().setItemInMainHand(null);
                                player.playSound(player.getLocation(), Sound.valueOf("BLOCK_GLASS_BREAK"),1.0f,1.0f);
                                player.sendMessage("§c"+tpItemMeta.getDisplayName()+"が壊れました。");
                                return false;
                            }
                            if(limit <= 5){
                                loresList.set(1, "§c"+limit);
                            }
                            else if(limit <= 10){
                                loresList.set(1, "§e"+limit);
                            }
                            else{
                                loresList.set(1, "§f"+limit);
                            }
                            tpItemMeta.setLore(loresList);
                            tpItem.setItemMeta(tpItemMeta);
                            player.sendMessage("残り使用回数§c"+limit+"回");
                        }
                    }
                }
                else if(event.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE) || event.getCurrentItem().getType().equals(Material.LIGHT_BLUE_STAINED_GLASS_PANE)){
                    event.setCancelled(true);
                }
                else if(event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){
                    event.setCancelled(true);
                    String playerName = event.getCurrentItem().getItemMeta().getDisplayName();
                    Collection players = getServer().getOnlinePlayers();
                    ArrayList<Player> playerlist = new ArrayList<>(players);

                    boolean flg = false;

                    //プレイヤー一覧を調査
                    for(Player p : playerlist) {
//                        String fileName = tpPoint.safeUUID(player.getUniqueId().toString()+"notTP");
//                        List<String> text = Files.readAllLines(Paths.get(path + "/" + fileName + ".notp"));
//                        String Name = tpPoint.safeUUID(p.getUniqueId().toString()+"notTP");
//                        List<String> resTxt = Files.readAllLines(Paths.get(path + "/" + Name + ".notp"));


                        // 選択したプレイヤーアイコン名がサーバー内にいるか否か
                        if (p.getName().equalsIgnoreCase(playerName)) {

                            //右クリック時
                            if(event.isRightClick()){
//                                player.setMetadata(p.getUniqueId().toString(),new FixedMetadataValue(plugin,p.getName()));
////                                File file = new File(path+"/"+fileName+".notp");
////                                Path filePath = Paths.get(path+"/"+fileName+".notp");
//                                FileWriter fileWrite = new FileWriter(path+"/"+fileName+".notp");
//                                ArrayList<String> strArray = new ArrayList<String>();
//
//                                FileReader fileReader = new FileReader(file);
//                                BufferedReader bufferedReader = new BufferedReader(fileReader);
//                                PrintWriter pw = new PrintWriter(new BufferedWriter(fileWrite));
//
//                                String line;
//                                List<String> lineList = new ArrayList<String>();
//                                if(!folder.exists()){
//                                    folder.mkdir();
//                                    getLogger().info("ディレクトリを作成しました。");
//                                }
//                                if(!file.exists()){
//                                    try {
//                                        boolean newFile = file.createNewFile();
//                                        getLogger().info("ファイルを作成しました。");
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                getLogger().info(String.valueOf(text.size()));
//                                for(String ss:text){
//                                    getLogger().info(ss);
//                                }
//                                while((line = bufferedReader.readLine()) != null){
//                                    lineList.add(line);
//                                }
//                                List<String> resList = new ArrayList<String>();
//                                resList = text;
//                                if(resList.size() == 0){
//                                    resList.add(String.valueOf(p.getUniqueId()));
//                                }
//                                else {
//                                    for (String s1 : resList) {
//                                        if (s1.equals(String.valueOf(p.getUniqueId()))) {
//                                            getLogger().info(">>>"+s1);
//                                            resList.remove(s1);
//                                            player.sendMessage(ChatColor.GREEN+p.getName()+"からのTPを許可します。");
//                                            flg = true;
//                                        } else {
//                                            resList.add(String.valueOf(p.getUniqueId()));
//                                        }
//                                    }
//                                }
//                                if(flg!=true){
//                                    player.sendMessage(ChatColor.GOLD +p.getName()+"からのTPを拒否します。");
//                                }
//                                for(String strList:resList){
//                                    pw.println(strList);
//                                }
//                                pw.close();
//                                break;
                            }
    //                        player.sendMessage(p.getName());
                            else if(event.isLeftClick()) {
                                if(/*resTxt.size() != 0*/false) {
//                                    for (String res : resTxt) {
//                                        getLogger().info(res);
//                                        getLogger().info(String.valueOf(player.getUniqueId()));
//                                        if (res.equals(String.valueOf(player.getUniqueId()))) {
//                                            player.sendMessage(ChatColor.RED + p.getName() + "へのTPは許可されていません。");
//                                            p.sendMessage(ChatColor.YELLOW + player.getName() + "があなたへTPしようとしました。");
//                                        }
//                                        return false;
//                                    }
                                }
                                else {
                                    numX = p.getLocation().getX();
                                    numY = p.getLocation().getY();
                                    numZ = p.getLocation().getZ();
                                    String world_name = p.getWorld().getName();
                                    player.teleport(new Location(getServer().getWorld(world_name), numX, numY, numZ));
                                    if(limit >0) {
                                        limit -= 1;
                                        player.sendMessage(ChatColor.AQUA + p.getName() + "へテレポートしました。");
                                        if(limit <= 0){
                                            player.getInventory().setItemInMainHand(null);
                                            player.playSound(player.getLocation(), Sound.valueOf("BLOCK_GLASS_BREAK"),1.0f,1.0f);
                                            player.sendMessage("§c"+tpItemMeta.getDisplayName()+"が壊れました。");
                                            return false;
                                        }
                                        if(limit <= 5){
                                            loresList.set(1, "§c"+limit);
                                        }
                                        else if(limit <= 10){
                                            loresList.set(1, "§e"+limit);
                                        }
                                        else{
                                            loresList.set(1, "§f"+limit);
                                        }
                                        tpItemMeta.setLore(loresList);
                                        tpItem.setItemMeta(tpItemMeta);
                                        player.sendMessage("残り使用回数§c"+limit+"回");
                                    }
                                    return false;
                                }
                                    numX = p.getLocation().getX();
                                    numY = p.getLocation().getY();
                                    numZ = p.getLocation().getZ();
                                    String world_name = p.getWorld().getName();
                                    player.teleport(new Location(getServer().getWorld(world_name), numX, numY, numZ));
                                    if(limit >0) {
                                        limit -= 1;
                                        player.sendMessage(ChatColor.AQUA + p.getName() + "へテレポートしました。");
                                        if(limit <= 0){
                                            player.getInventory().setItemInMainHand(null);
                                            player.playSound(player.getLocation(), Sound.valueOf("BLOCK_GLASS_BREAK"),1.0f,1.0f);
                                            player.sendMessage("§c"+tpItemMeta.getDisplayName()+"が壊れました。");
                                            return false;
                                        }
                                        if(limit <= 5){
                                            loresList.set(1, "§c"+limit);
                                        }
                                        else if(limit <= 10){
                                            loresList.set(1, "§e"+limit);
                                        }
                                        else{
                                            loresList.set(1, "§f"+limit);
                                        }
                                        tpItemMeta.setLore(loresList);
                                        tpItem.setItemMeta(tpItemMeta);
                                        player.sendMessage("残り使用回数§c"+limit+"回");
                                    }
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
