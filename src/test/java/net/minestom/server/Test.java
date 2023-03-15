package net.minestom.server;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extras.PlacementRules;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class Test {

    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init();
        InstanceContainer world = MinecraftServer.getInstanceManager().createInstanceContainer();
        world.setGenerator(unit -> unit.modifier().fillHeight(0, 4, Block.GRASS_BLOCK));

        MinecraftServer.getGlobalEventHandler().addListener(PlayerLoginEvent.class, event -> {
            event.setSpawningInstance(world);
            event.getPlayer().setRespawnPoint(new Pos(0, 5, 0));
            event.getPlayer().setGameMode(GameMode.CREATIVE);
            event.getPlayer().getInventory().addItemStack(ItemStack.of(Material.STONE));
            event.getPlayer().getInventory().addItemStack(ItemStack.of(Material.DARK_OAK_SLAB));
        });

        PlacementRules.init();
        server.start("0.0.0.0", 25565);
    }
}
