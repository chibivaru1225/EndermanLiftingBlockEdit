package chibivaru.endermanliftingblockedit;

import java.util.HashMap;

import chibivaru.endermanliftingblockedit.common.ClassHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

@Mod(modid = EndermanLiftingBlockEdit.MODID, name = EndermanLiftingBlockEdit.MODNAME, version = EndermanLiftingBlockEdit.VERSION, dependencies = "required-after:FML")

public class EndermanLiftingBlockEdit {
	public static final String MODID = "endermanliftingblockedit";
	public static final String MODNAME = "EndermanLiftingBlockEdit";
	public static final String VERSION = "1.1";

	@Metadata(MODID)
	public static ModMetadata meta;

	public static boolean liftable;

	public static Configuration cfg;

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		cfg = new Configuration(event.getSuggestedConfigurationFile());
		try {
			liftable = cfg.get("Enderman", "Liftable", false).getBoolean();
		} finally {
			cfg.save();
		}
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		try {
			ClassHelper.endermanInspection();
			for (Block keyblock : ClassHelper.endermanCarriable().keySet()) {
				EntityEnderman.setCarriable(keyblock, liftable);
			}
		} finally {
			cfg.save();
		}
	}
}
