package net.es.endless_store_mod;

import net.es.endless_store_mod.block.entity.CustomBlockEntities;
import net.es.endless_store_mod.entity.custom.JackEntity;
import net.es.endless_store_mod.entity.custom.SecurityEntity;
import net.es.endless_store_mod.fluid.EndlessStoreFluids;
import net.es.endless_store_mod.registry.EndlessStoreFeatures;
import net.es.endless_store_mod.registry.EndlessStoreFuel;
import net.es.endless_store_mod.screen.DimensionEnterHandler;
import net.es.endless_store_mod.screen.PlayerDeathHandler;
import net.es.endless_store_mod.world.dimension.EndlessStoreDimension;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.es.endless_store_mod.entity.EndlessStoreEntities;
import net.es.endless_store_mod.entity.custom.EmployeeEntity;
import net.es.endless_store_mod.item.EndlessStoreItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class EndlessStoreMod implements ModInitializer {
	public static final String MOD_ID = "endless_store_mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		EndlessStoreItems.registerModItems();
		EndlessStoreFluids.register();
		EndlessStoreBlocks.registerModBlocks();

		CustomBlockEntities.init();

		DimensionEnterHandler.init();
		PlayerDeathHandler.register();

		EndlessStoreFuel.init();
		GeckoLib.initialize();

		FabricDefaultAttributeRegistry.register(EndlessStoreEntities.EMPLOYEE, EmployeeEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(EndlessStoreEntities.JACK, JackEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(EndlessStoreEntities.SECURITY, SecurityEntity.setAttributes());

		EndlessStoreFeatures.registerFeatures();

		EndlessStoreDimension.register();
	}
}
