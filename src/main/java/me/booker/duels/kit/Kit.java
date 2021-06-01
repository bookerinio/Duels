package me.booker.duels.kit;

import org.bukkit.inventory.ItemStack;

public class Kit {

    private String name;
    private ItemStack[] contents;
    private ItemStack[] armor;

    private Kit(String name) {
        this.name = name;
        KitManager.addKit(this);
    }


    public String getName() {
        return name;
    }

    public ItemStack[] getContents() {
        return contents;
    }

    public void setContents(ItemStack[] contents) {
        this.contents = contents;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public void setArmor(ItemStack[] armor) {
        this.armor = armor;
    }

    public static Kit get(String name) {
        for(Kit k : KitManager.getKits()) {
            if(k.getName().equalsIgnoreCase(name)) return k;
        }
        return new Kit(name);
    }
}
