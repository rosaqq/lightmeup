package net.sknv.lightmeup;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.sknv.lightmeup.proxy.CommonProxy;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

@Mod(modid = LightMeUp.MODID, name = LightMeUp.NAME, version = LightMeUp.VERSION)
public class LightMeUp {

    public static final String MODID = "lightmeup";
    public static final String NAME = "LightMeUp";
    public static final String VERSION = "1.12.2-0.1.0";
    public static final String CLIENT_PROXY_CLASS = "net.sknv.lightmeup.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "net.sknv.lightmeup.proxy.CommonProxy";


    private static Logger logger;

    @Instance
    public static LightMeUp instance;

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.log(Level.DEBUG, "pre init");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
