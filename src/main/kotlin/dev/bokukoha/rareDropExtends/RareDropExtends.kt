package dev.bokukoha.rareDropExtends

import dev.bokukoha.rareDropExtends.listener.CombatListener
import dev.bokukoha.rareDropExtends.listener.FishingListener
import dev.bokukoha.rareDropExtends.listener.MiningListener
import dev.bokukoha.rareDropExtends.recipes.ArtifactCrafting
import org.bukkit.plugin.java.JavaPlugin

class ArtifactPlugin : JavaPlugin() {

    private lateinit var manager: ArtifactManager

    override fun onEnable() {
        logger.info("§a[Artifacts] プラグインを読み込み中...")
        saveDefaultConfig()

        // 設定読み込み
        manager = ArtifactManager(this)
        manager.loadConfigValues()

        // イベント登録
        server.pluginManager.registerEvents(FishingListener(this), this)
        server.pluginManager.registerEvents(MiningListener(this), this)
        server.pluginManager.registerEvents(CombatListener(this), this)
        server.pluginManager.registerEvents(ArtifactCrafting(this), this)

        logger.info("§a[Artifacts] 初期化完了！")
    }

    override fun onDisable() {
        logger.info("§c[Artifacts] プラグインを停止しました。")
    }
}

