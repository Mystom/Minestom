package net.minestom.server.instance.block.rule.vanilla;

import net.minestom.server.coordinate.Point;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DefaultPlacementRule extends BlockPlacementRule {

    public DefaultPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull Instance instance, @NotNull Point blockPosition, @NotNull Block block) {
        return block;
    }

    @Override
    public @Nullable Block blockPlace(@NotNull Instance instance,
                                      @NotNull Block block, @NotNull BlockFace blockFace,
                                      @NotNull Point blockPosition, @NotNull Player pl) {

        Point currentPos = blockPosition.relative(blockFace);
        Block current = instance.getBlock(currentPos);
        return current.isAir() || current.isLiquid() ? block : current;
    }
}
