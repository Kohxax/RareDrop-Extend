package dev.bokukoha.rareDropExtends.data

import org.bukkit.Material

data class ArtifactResult(
    val id: String,
    val displayName: String,
    val material: Material,
    val lore: List<String>,
    val attributes: Map<String, Double>
)
