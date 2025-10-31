package dev.bokukoha.rareDropExtends.data

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

/*
 * ドロップするカスタムパーツのデータ
 */
data class ArtifactPart(
    val key: String,
    val type: ArtifactType,
    val material: Material,
    val displayName: String,
    val enchantments: Map<Enchantment, Int> = emptyMap(),
    val lore: List<String> = emptyList()
)