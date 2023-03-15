package net.minestom.server.extras;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockManager;
import net.minestom.server.instance.block.rule.vanilla.*;

public final class PlacementRules {

    /**
     * Register all the block placement rules supported:
     * - axis blocks (logs, hay block, etc)
     * - stairs
     * - walls
     * - redstone dust
     */
    public static void init() {
        BlockManager manager = MinecraftServer.getBlockManager();
        for (Block block : Block.values()) {
            String namespace = block.namespace().value();

            // Stairs
            if (namespace.endsWith("stairs")) {
                manager.registerBlockPlacementRule(new StairsPlacementRule(block));
                continue;
            }

            // Walls
            if (namespace.endsWith("wall")) {
                manager.registerBlockPlacementRule(new WallPlacementRule(block));
                continue;
            }

            // Slabs
            if (namespace.endsWith("slab")){
                manager.registerBlockPlacementRule(new SlabPlacementRule(block));
                continue;
            }

            // Axis - Hay bales, logs, etc
            if (block.getProperty("axis") != null) {
                manager.registerBlockPlacementRule(new AxisPlacementRule(block));
                continue;
            }

            if (block.isSolid())
                manager.registerBlockPlacementRule(new DefaultPlacementRule(block));
        }

        manager.registerBlockPlacementRule(new RedstonePlacementRule());
    }
}
