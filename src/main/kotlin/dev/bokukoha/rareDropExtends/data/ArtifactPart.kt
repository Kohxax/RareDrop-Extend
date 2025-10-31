package dev.bokukoha.rareDropExtends.data

import org.bukkit.Material

/*
 * ドロップするカスタムパーツのデータ
 */
data class ArtifactPart(
    val id: String,
    val name: String,
    val material: Material,
    val type: ArtifactType
)