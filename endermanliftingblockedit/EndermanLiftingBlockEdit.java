package chibivaru.endermanliftingblockedit;

import java.util.HashMap;

import chibivaru.endermanliftingblockedit.common.ClassHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = EndermanLiftingBlockEdit.MODID, name = EndermanLiftingBlockEdit.MODNAME, version = EndermanLiftingBlockEdit.VERSION, dependencies = "required-after:FML")

public class EndermanLiftingBlockEdit {
	public static final String MODID = "endermanliftingblockedit";
	public static final String MODNAME = "EndermanLiftingBlockEdit";
	public static final String VERSION = "1.0";

	@Metadata(MODID)
	public static ModMetadata meta;

	public static HashMap<Block, Boolean> EndermanBlock = new HashMap<Block, Boolean>();

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		ClassHelper.endermanInspection();
		Configuration cfg = new Configuration();
		try {
			for (Block keyblock : ClassHelper.endermanCarriable().keySet()) {
				EndermanBlock.put(keyblock, cfg.getBoolean(keyblock.getUnlocalizedName(), "Enderman", false, ""));
			}
		} finally {
			cfg.save();
		}
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		for (Block keyblock : ClassHelper.endermanCarriable().keySet()) {
			if (ClassHelper.endermanCarriable().get(keyblock) == true && EndermanBlock.containsKey(keyblock) == true
					&& EndermanBlock.get(keyblock) == false) {
				EntityEnderman.setCarriable(keyblock, false);
			}
		}
	}
}
