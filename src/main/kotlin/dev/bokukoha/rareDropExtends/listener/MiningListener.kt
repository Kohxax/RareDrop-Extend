package dev.bokukoha.rareDropExtends.listener

import dev.bokukoha.rareDropExtends.data.ArtifactType
import dev.bokukoha.rareDropExtends.util.ArtifactUtils
import dev.bokukoha.rareDropExtends.util.ItemFactory
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class MiningListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val player = e.player
        val block = e.block
        val type = ArtifactType.MINE

        if (!isTargetBlock(block.type)) return
        if (!ArtifactUtils.shouldDrop(type.dropChance)) return

        val parts = listOf(
            Triple("ancient_plan", "§6古代の設計図", Material.PAPER),
            Triple("ancient_gear", "§6古代の歯車", Material.MUSIC_DISC_5), // レコード破片代用
            Triple("ancient_meter", "§6古代の計測器", Material.COMPASS)
        )
        val (id, name, mat) = parts.random()

        val item = ItemFactory.createPart(plugin, id, name, mat)
        ArtifactUtils.dropWithEffects(plugin, player, item, "地底から古代の遺物を掘り当てた！")
        player.world.playSound(player.location, Sound.BLOCK_BEACON_POWER_SELECT, 1f, 1.2f)
    }

    private fun isTargetBlock(material: Material): Boolean {
        return when (material) {
            Material.STONE,
            Material.DEEPSLATE,
            Material.GRASS_BLOCK,
            Material.DIRT,
            Material.SAND,
            Material.GRAVEL -> true

            else -> false
        }
    }
}
