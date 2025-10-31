package dev.bokukoha.rareDropExtends.util

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

object ArtifactUtils {

    fun shouldDrop(probability: Double): Boolean {
        return Math.random() < probability
    }

    fun dropWithEffects(plugin: JavaPlugin, player: Player, item: ItemStack, titleSubtitle: String) {
        val world = player.world
        world.dropItemNaturally(player.location, item)
        world.spawnParticle(Particle.END_ROD, player.location, 60, 0.3, 0.8, 0.3, 0.02)
        world.spawnParticle(Particle.FLASH, player.location, 1)
        world.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1.4f)

        player.sendTitle("§6§l伝説の欠片を発見！", "§7$titleSubtitle", 10, 60, 10)
    }
}
