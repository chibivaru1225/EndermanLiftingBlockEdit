package chibivaru.endermanliftingblockedit.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.IdentityHashMap;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.potion.Potion;

public class ClassHelper
{
    private static IdentityHashMap<Block, Boolean> endermanCarriable;

    public static void endermanInspection()
    {
        endermanCarriable = new IdentityHashMap<Block, Boolean>();
        try
        {
            Field stupidForgePrivateVariable = ReflectionHelper.findField(EntityEnderman.class, "carriable");

            endermanCarriable = (IdentityHashMap<Block, Boolean>) stupidForgePrivateVariable.get(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static IdentityHashMap<Block, Boolean> endermanCarriable()
    {
        return endermanCarriable;
    }
}