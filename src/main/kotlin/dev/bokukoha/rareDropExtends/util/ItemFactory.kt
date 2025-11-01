package dev.bokukoha.rareDropExtends.util

import dev.bokukoha.rareDropExtends.data.ArtifactType
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlotGroup
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

object ItemFactory {

    private fun addModifier(
        plugin: JavaPlugin,
        meta: org.bukkit.inventory.meta.ItemMeta,
        attribute: Attribute,
        keyName: String,
        amount: Double,
        operation: AttributeModifier.Operation,
        slotGroup: EquipmentSlotGroup
    ) {
        val key = NamespacedKey(plugin, keyName)
        val modifier = AttributeModifier(key, amount, operation, slotGroup)
        meta.addAttributeModifier(attribute, modifier)
    }

    fun createPart(plugin: JavaPlugin, id: String, name: String, material: Material): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta!!

        meta.setDisplayName(name)
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true)
        meta.persistentDataContainer.set(
            NamespacedKey(plugin, "artifact_id"),
            PersistentDataType.STRING,
            id
        )

        item.itemMeta = meta
        return item
    }

    fun createResult(plugin: JavaPlugin, type: ArtifactType): ItemStack {
        val item = when (type) {
            ArtifactType.FISH -> ItemStack(Material.HEART_OF_THE_SEA)
            ArtifactType.MINE -> ItemStack(Material.CLOCK)
            ArtifactType.SLAY -> ItemStack(Material.FEATHER)
        }

        val meta = item.itemMeta!!
        meta.setDisplayName(type.displayName)
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true)

        when (type) {
            ArtifactType.FISH -> {
                meta.addEnchant(Enchantment.INFINITY, 1, true)
                addModifier(plugin, meta, Attribute.ATTACK_DAMAGE, "elder_attack", 20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                addModifier(plugin, meta, Attribute.MAX_HEALTH,   "elder_health",  -18.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                addModifier(plugin, meta, Attribute.ATTACK_DAMAGE, "elder_attack_head", 20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                addModifier(plugin, meta, Attribute.MAX_HEALTH,   "elder_health_head",  -18.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                meta.lore = listOf(
                    "§7深海に棲まう古代の力。",
                    "§c最大体力 -18",
                    "§a攻撃力 +20"
                )
            }
            ArtifactType.MINE -> {
                meta.addEnchant(Enchantment.FORTUNE, 1, true)
                addModifier(plugin, meta, Attribute.BLOCK_INTERACTION_RANGE, "ancient_reach", 2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                addModifier(plugin, meta, Attribute.SAFE_FALL_DISTANCE,      "ancient_fall",  5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                addModifier(plugin, meta, Attribute.BLOCK_INTERACTION_RANGE, "ancient_reach_head", 2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                addModifier(plugin, meta, Attribute.SAFE_FALL_DISTANCE,      "ancient_fall_head",  5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                meta.lore = listOf(
                    "§7古代文明の遺物。建築で役立つ加護が受けられるらしい…?",
                    "§aリーチ +2",
                    "§a安全落下距離 +5"
                )
            }
            ArtifactType.SLAY -> {
                meta.addEnchant(Enchantment.SMITE, 5, true)
                addModifier(plugin, meta, Attribute.MOVEMENT_SPEED, "ender_speed", 0.10, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.HAND)
                addModifier(plugin, meta, Attribute.ATTACK_DAMAGE,   "ender_damage", -10.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                addModifier(plugin, meta, Attribute.MOVEMENT_SPEED, "ender_speed_head", 0.10, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.HEAD)
                addModifier(plugin, meta, Attribute.ATTACK_DAMAGE,   "ender_damage_head", -10.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                meta.lore = listOf(
                    "§7竜の加護を受けし羽",
                    "§a移動速度 +10%",
                    "§c攻撃力 -10"
                )
            }
        }

        meta.persistentDataContainer.set(
            NamespacedKey(plugin, "artifact_type"),
            PersistentDataType.STRING,
            type.id
        )

        item.itemMeta = meta
        return item
    }
}
