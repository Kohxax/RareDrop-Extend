package dev.bokukoha.rareDropExtends.recipes

import dev.bokukoha.rareDropExtends.data.ArtifactType
import dev.bokukoha.rareDropExtends.util.ItemFactory
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import java.time.LocalDate

class ArtifactCrafting(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onCraft(e: PrepareItemCraftEvent) {
        val player = e.view.player as? Player ?: return
        val items = e.inventory.matrix.filterNotNull()
        if (items.size < 3) return

        val foundIds = items.mapNotNull {
            it.itemMeta?.persistentDataContainer?.get(
                NamespacedKey(plugin, "artifact_id"),
                PersistentDataType.STRING
            )
        }

        for (type in ArtifactType.values()) {
            if (foundIds.containsAll(type.parts)) {
                val result = ItemFactory.createResult(plugin, type)

                val meta = result.itemMeta!!
                val lore = meta.lore?.toMutableList() ?: mutableListOf()
                lore.add("§4Crafted by ${player.name}")
                meta.lore = lore

                meta.persistentDataContainer.set(
                    NamespacedKey(plugin, "crafted_by"),
                    PersistentDataType.STRING,
                    player.name
                )

                result.itemMeta = meta
                e.inventory.result = result
                return
            }
        }

        e.inventory.result = null
    }
}

