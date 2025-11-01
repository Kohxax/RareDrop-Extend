package dev.bokukoha.rareDropExtends.listener

import dev.bokukoha.rareDropExtends.data.ArtifactType
import dev.bokukoha.rareDropExtends.util.ArtifactUtils
import dev.bokukoha.rareDropExtends.util.ItemFactory
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.plugin.java.JavaPlugin

class CombatListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onMobDeath(e: EntityDeathEvent) {
        val killer = e.entity.killer ?: return
        if (killer !is Player) return
        val type = ArtifactType.SLAY

        if (!ArtifactUtils.shouldDrop(type.dropChance)) return

        val parts = listOf(
            Triple("ender_skin", "§d???の表皮", Material.PHANTOM_MEMBRANE),
            Triple("ender_shaft", "§d???の羽軸", Material.STICK),
            Triple("ender_breath", "§d???の息吹", Material.DRAGON_BREATH)
        )
        val (id, name, mat) = parts.random()

        val item = ItemFactory.createPart(plugin, id, name, mat)
        ArtifactUtils.dropWithEffects(plugin, killer, item, "特別なアイテムがドロップしたようだ……")
        killer.world.playSound(killer.location, Sound.ENTITY_ENDER_DRAGON_GROWL, 1f, 0.8f)
    }
}
