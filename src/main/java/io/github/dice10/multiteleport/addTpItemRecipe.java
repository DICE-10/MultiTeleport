package io.github.dice10.multiteleport;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class addTpItemRecipe implements Listener {

    private MultiTeleport plugin = MultiTeleport.getPlugin(MultiTeleport.class);
    
    public boolean addRecipe(){
        addItemI(0);
        addItemII(0);
        addItemIII(0);
        addItemIII_II(0);
        addItemIV(0);
        addItemV(0);
        addItemVI(0);
        addItemVII(0);
        addItemVIII(0);
        addItemIX(0);
        addItemX(0);
        System.out.println("MulutiTeleportレシピを有効化しました。");
        return true;
    }
    
    protected ItemStack addItemI(int flg){
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dUsage Limit.");
        loresList.add("§c5");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.setDisplayName("§6TP-Portal I");
        itemMeta.setLocalizedName("MultiTeleport");
        itemMeta.setLore(loresList);
//        itemMeta.setCustomModelData(6798631);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        if(flg == 0) {
            NamespacedKey key = new NamespacedKey(plugin, "tp-portal");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("CCC", "CEC", "OOO");
            recipe.setIngredient('C', Material.COAL);
            recipe.setIngredient('E', Material.ENDER_PEARL);
            recipe.setIngredient('O', Material.OBSIDIAN);

            Bukkit.addRecipe(recipe);
        }
        return item;
    }

    protected ItemStack addItemII(int flg){
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dUsage Limit.");
        loresList.add("§e10");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.setDisplayName("§2TP-Portal II");
        itemMeta.setLocalizedName("MultiTeleport");
        itemMeta.setLore(loresList);
        itemMeta.setCustomModelData(6798632);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        if(flg == 0) {
            NamespacedKey key = new NamespacedKey(plugin, "tp-portal_I");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("III", "IEI", "OOO");
            recipe.setIngredient('I', Material.IRON_INGOT);
            recipe.setIngredient('E', Material.ENDER_PEARL);
            recipe.setIngredient('O', Material.OBSIDIAN);
            Bukkit.addRecipe(recipe);
        }
        return item;
    }

    protected ItemStack addItemIII(int flg){
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dUsage Limit.");
        loresList.add("§f30");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.setDisplayName("§eTP-Portal III");
        itemMeta.setLocalizedName("MultiTeleport");
        itemMeta.setLore(loresList);
        itemMeta.setCustomModelData(6798633);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        if(flg == 0) {
            NamespacedKey key = new NamespacedKey(plugin, "tp-portal_II");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("LLL", "LEL", "OOO");
            recipe.setIngredient('L', Material.LAPIS_LAZULI);
            recipe.setIngredient('E', Material.ENDER_PEARL);
            recipe.setIngredient('O', Material.OBSIDIAN);

            Bukkit.addRecipe(recipe);
        }
        return item;
    }

    protected ItemStack addItemIII_II(int flg){
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dUsage Limit.");
        loresList.add("§f30");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.setDisplayName("§eTP-Portal III");
        itemMeta.setLocalizedName("MultiTeleport");
        itemMeta.setLore(loresList);
        itemMeta.setCustomModelData(6798633);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        if(flg == 0) {
            NamespacedKey key = new NamespacedKey(plugin, "tp-portal_III");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("RRR", "RER", "OOO");
            recipe.setIngredient('R', Material.REDSTONE);
            recipe.setIngredient('E', Material.ENDER_PEARL);
            recipe.setIngredient('O', Material.OBSIDIAN);

            Bukkit.addRecipe(recipe);
        }
        return item;
    }

    protected ItemStack addItemIV(int flg){
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dUsage Limit.");
        loresList.add("§f50");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.setDisplayName("§cTP-Portal IV");
        itemMeta.setLocalizedName("MultiTeleport");
        itemMeta.setLore(loresList);
        itemMeta.setCustomModelData(6798634);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        if(flg == 0) {
            NamespacedKey key = new NamespacedKey(plugin, "tp-portal_IV");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("GGG", "GEG", "OOO");
            recipe.setIngredient('G', Material.GOLD_INGOT);
            recipe.setIngredient('E', Material.ENDER_PEARL);
            recipe.setIngredient('O', Material.OBSIDIAN);

            Bukkit.addRecipe(recipe);
        }
        return item;
    }

    protected ItemStack addItemV(int flg){
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dUsage Limit.");
        loresList.add("§f100");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.setDisplayName("§3TP-Portal V");
        itemMeta.setLocalizedName("MultiTeleport");
        itemMeta.setLore(loresList);
        itemMeta.setCustomModelData(6798635);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        if(flg == 0) {
            NamespacedKey key = new NamespacedKey(plugin, "tp-portal_V");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("DDD", "DED", "OOO");
            recipe.setIngredient('D', Material.DIAMOND);
            recipe.setIngredient('E', Material.ENDER_PEARL);
            recipe.setIngredient('O', Material.OBSIDIAN);

            Bukkit.addRecipe(recipe);
        }
        return item;
    }

    protected ItemStack addItemVI(int flg){
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dUsage Limit.");
        loresList.add("§f120");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.setDisplayName("§bTP-Portal VI");
        itemMeta.setLocalizedName("MultiTeleport");
        itemMeta.setLore(loresList);
        itemMeta.setCustomModelData(6798636);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        if(flg == 0) {
            NamespacedKey key = new NamespacedKey(plugin, "tp-portal_VI");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("DDD", "DED", "OOO");
            recipe.setIngredient('D', Material.DIAMOND);
            recipe.setIngredient('E', Material.ENDER_EYE);
            recipe.setIngredient('O', Material.OBSIDIAN);

            Bukkit.addRecipe(recipe);
        }
        return item;
    }

    protected ItemStack addItemVII(int flg){
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dUsage Limit.");
        loresList.add("§f120");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.setDisplayName("§bTP-Portal VII");
        itemMeta.setLocalizedName("MultiTeleport");
        itemMeta.setLore(loresList);
        itemMeta.setCustomModelData(6798637);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        if(flg == 0) {
            NamespacedKey key = new NamespacedKey(plugin, "tp-portal_VII");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("MMM", "MEM", "OOO");
            recipe.setIngredient('M', Material.EMERALD);
            recipe.setIngredient('E', Material.ENDER_EYE);
            recipe.setIngredient('O', Material.OBSIDIAN);

            Bukkit.addRecipe(recipe);
        }
        return item;
    }

    protected ItemStack addItemVIII(int flg){
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dUsage Limit.");
        loresList.add("§f200");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.setDisplayName("§5TP-Portal VIII");
        itemMeta.setLocalizedName("MultiTeleport");
        itemMeta.setLore(loresList);
        itemMeta.setCustomModelData(6798638);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        if(flg == 0) {
            NamespacedKey key = new NamespacedKey(plugin, "tp-portal_VIII");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("NNN", "NEN", "OOO");
            recipe.setIngredient('N', Material.NETHER_STAR);
            recipe.setIngredient('E', Material.ENDER_PEARL);
            recipe.setIngredient('O', Material.OBSIDIAN);

            Bukkit.addRecipe(recipe);
        }
        return item;
    }

    protected ItemStack addItemIX(int flg){
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dUsage Limit.");
        loresList.add("§f250");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.setDisplayName("§aTP-Portal IX");
        itemMeta.setLocalizedName("MultiTeleport");
        itemMeta.setLore(loresList);
        itemMeta.setCustomModelData(6798639);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        if(flg == 0) {
            NamespacedKey key = new NamespacedKey(plugin, "tp-portal_IX");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("NNN", "NEN", "OOO");
            recipe.setIngredient('N', Material.NETHER_STAR);
            recipe.setIngredient('E', Material.ENDER_EYE);
            recipe.setIngredient('O', Material.OBSIDIAN);

            Bukkit.addRecipe(recipe);
        }
        return item;
    }

    protected ItemStack addItemX(int flg){
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        List<String> loresList = new ArrayList<String>();
        loresList.add("§dUsage Limit.");
        loresList.add("§f300");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.setDisplayName("§4TP-Portal X");
        itemMeta.setLocalizedName("MultiTeleport");
        itemMeta.setLore(loresList);
        itemMeta.setCustomModelData(6798640);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        if(flg == 0) {
            NamespacedKey key = new NamespacedKey(plugin, "tp-portal_X");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("NNN", "NEN", "DDD");
            recipe.setIngredient('N', Material.NETHER_STAR);
            recipe.setIngredient('E', Material.ENDER_EYE);
            recipe.setIngredient('D', Material.DIAMOND_BLOCK);

            Bukkit.addRecipe(recipe);
        }
        return item;
    }
}
