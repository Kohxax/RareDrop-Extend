package dev.bokukoha.rareDropExtends

import org.bukkit.plugin.java.JavaPlugin

class RareDropExtends : JavaPlugin() {

    private lateinit var manager: ArtifactManager

    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig()
        manager = ArtifactManager(this)
        manager.loadConfigValues()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
