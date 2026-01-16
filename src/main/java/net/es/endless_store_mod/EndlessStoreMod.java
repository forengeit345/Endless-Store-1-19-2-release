package net.es.endless_store_mod;

import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.es.endless_store_mod.block.entity.CustomBlockEntities;
import net.es.endless_store_mod.entity.ConfigurableHeightSpawnRestriction;
import net.es.endless_store_mod.entity.EndlessStoreEntities;
import net.es.endless_store_mod.entity.custom.*;
import net.es.endless_store_mod.fluid.EndlessStoreFluids;
import net.es.endless_store_mod.item.EndlessStoreItemGroup;
import net.es.endless_store_mod.item.EndlessStoreItems;
import net.es.endless_store_mod.registry.EndlessStoreFeatures;
import net.es.endless_store_mod.registry.EndlessStoreFuel;
import net.es.endless_store_mod.screen.DimensionEnterHandler;
import net.es.endless_store_mod.screen.PlayerDeathHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class EndlessStoreMod implements ModInitializer {
	public static final String MOD_ID = "endless_store_mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		EndlessStoreItems.registerModItems();
		EndlessStoreFluids.register();
		EndlessStoreBlocks.registerModBlocks();
		EndlessStoreItemGroup.registerItemGroups();

		CustomBlockEntities.init();

		DimensionEnterHandler.init();
		PlayerDeathHandler.register();

		EndlessStoreFuel.init();
		GeckoLib.initialize();

		FabricDefaultAttributeRegistry.register(EndlessStoreEntities.EMPLOYEE, EmployeeEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(EndlessStoreEntities.JACK, JackEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(EndlessStoreEntities.SECURITY, SecurityEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(EndlessStoreEntities.WATCHER, WatcherEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(EndlessStoreEntities.WATER_STRIDER, WaterStriderEntity.setAttributes());

		ConfigurableHeightSpawnRestriction.register();

		EndlessStoreFeatures.registerFeatures();
	}
}