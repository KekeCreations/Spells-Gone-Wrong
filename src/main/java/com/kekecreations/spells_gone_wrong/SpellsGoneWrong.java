package com.kekecreations.spells_gone_wrong;

import com.kekecreations.spells_gone_wrong.core.config.SpellsGoneWrongCommonConfig;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(SpellsGoneWrong.MOD_ID)
public class SpellsGoneWrong {

    public static final String MOD_ID = "spells_gone_wrong";
    private static final Logger LOGGER = LogUtils.getLogger();


    public SpellsGoneWrong(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);


        NeoForge.EVENT_BUS.register(this);


        modContainer.registerConfig(ModConfig.Type.COMMON, SpellsGoneWrongCommonConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}
