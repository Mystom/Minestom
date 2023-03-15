package net.minestom.server.instance.block.rule.vanilla;

import net.minestom.server.coordinate.Point;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SlabPlacementRule extends BlockPlacementRule {

    public SlabPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull Instance instance, @NotNull Point pos, @NotNull Block block) {
        return block;
    }

    @Override
    public @Nullable Block blockPlace(@NotNull Instance instance, @NotNull Block block, @NotNull BlockFace blockFace,
                                      @NotNull Point pos, @NotNull Player pl, @NotNull Point cursor) {
        double cursorY = cursor.y();
        SlabType type = (cursorY != 1 && cursorY >= 0.5) || cursorY == 0 ? SlabType.TOP : SlabType.BOTTOM;

        Block currentBlock = instance.getBlock(pos);
        String current = currentBlock.getProperty("type");
        if (current != null && instance.getBlock(relative(pos, type.face)).compare(block)) {
            type = SlabType.DOUBLE;
        }
        System.out.println(cursorY + ":" + type + " at " + pos.toString());
        return block.withProperty("type", type.name().toLowerCase());
    }

    private enum SlabType {
        TOP(BlockFace.TOP), BOTTOM(BlockFace.BOTTOM), DOUBLE(null);

        private BlockFace face;

        SlabType(BlockFace face) {
            this.face = face;
        }
    }

    private Point relative(@NotNull Point blockPos, @NotNull BlockFace face) {
        return switch (face) {
            case BOTTOM -> blockPos.sub(0, 0, 0);
            case TOP -> blockPos.add(0, 0, 0);
            default -> blockPos;
        };
    }
}
