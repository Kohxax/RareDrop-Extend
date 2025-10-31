package dev.bokukoha.rareDropExtends

import dev.bokukoha.rareDropExtends.data.ArtifactType
import org.bukkit.plugin.java.JavaPlugin

class ArtifactManager(private val plugin: JavaPlugin) {

    fun loadConfigValues() {
        val section = plugin.config.getConfigurationSection("drop-rates") ?: return

        section.getKeys(false).forEach { key ->
            val type = ArtifactType.fromString(key.uppercase())
            val value = section.getDouble(key)
            if (type != null && value > 0) {
                type.dropChance = value
                plugin.logger.info("Loaded drop chance for ${type.name}: $value")
            }
        }
    }
}
