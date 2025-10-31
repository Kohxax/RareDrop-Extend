package dev.bokukoha.rareDropExtends.listener

import dev.bokukoha.rareDropExtends.data.ArtifactType
import dev.bokukoha.rareDropExtends.util.ArtifactUtils
import dev.bokukoha.rareDropExtends.util.ItemFactory
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.plugin.java.JavaPlugin

class FishingListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onFish(event: PlayerFishEvent) {
        val player = event.player
        if (event.state != PlayerFishEvent.State.CAUGHT_FISH) return

        // ドロップ確率判定
        val type = ArtifactType.FISH
        if (!ArtifactUtils.shouldDrop(type.dropChance)) return

        // 3種ランダムに抽選
        val parts = listOf(
            Triple("elder_heart_part_right", "§c???の右心房", Material.FERMENTED_SPIDER_EYE),
            Triple("elder_heart_part_left", "§c???の左心房", Material.RED_DYE),
            Triple("elder_heart_part_aorta", "§c???の大動脈", Material.GLISTERING_MELON_SLICE)
        )
        val (id, displayName, material) = parts.random()

        // アイテム生成（PDC付き）
        val item = ItemFactory.createPart(plugin, id, displayName, material)

        // 演出付きでドロップ
        ArtifactUtils.dropWithEffects(plugin, player, item, "海がざわめいた気がする...")
        player.world.playSound(player.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f)
    }
}
