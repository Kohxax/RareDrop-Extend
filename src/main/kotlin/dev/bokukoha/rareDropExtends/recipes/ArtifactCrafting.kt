package dev.bokukoha.rareDropExtends.recipes

import dev.bokukoha.rareDropExtends.data.ArtifactType
import dev.bokukoha.rareDropExtends.util.ItemFactory
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.NamespacedKey

class ArtifactCrafting(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onCraft(e: PrepareItemCraftEvent) {
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
                e.inventory.result = ItemFactory.createResult(plugin, type)
                return
            }
        }

        e.inventory.result = null
    }
}
