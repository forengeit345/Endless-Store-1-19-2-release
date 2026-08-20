package net.es.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomSpawnEggItem extends SpawnEggItem {

    private final Component shiftTooltip;

    public CustomSpawnEggItem(EntityType<? extends Mob> type, int primaryColor, int secondaryColor,
                              Properties properties, Component shiftTooltip) {
        super(type, primaryColor, secondaryColor, properties);
        this.shiftTooltip = shiftTooltip;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable TooltipContext context,
                                List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (tooltipFlag.isAdvanced()) {   // именно при зажатом Shift (F3+H тоже)
            tooltipComponents.add(this.shiftTooltip);
        }
    }
}
