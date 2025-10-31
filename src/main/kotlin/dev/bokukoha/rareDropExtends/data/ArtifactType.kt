package dev.bokukoha.rareDropExtends.data

enum class ArtifactType(
    val id: String,
    val displayName: String,
    var dropChance: Double, // varにして外部から更新可能に
    val parts: List<String>
) {
    FISH(
        id = "elder_heart",
        displayName = "§bエルダーガーディアンの心臓",
        dropChance = 0.00005,
        parts = listOf(
            "elder_heart_part_right",
            "elder_heart_part_left",
            "elder_heart_part_aorta"
        )
    ),
    MINE(
        id = "ancient_clock",
        displayName = "§6古代建築士の時計",
        dropChance = 0.00003,
        parts = listOf(
            "ancient_plan",
            "ancient_gear",
            "ancient_meter"
        )
    ),
    SLAY(
        id = "ender_wing",
        displayName = "§dエンダードラゴンの羽",
        dropChance = 0.00001,
        parts = listOf(
            "ender_skin",
            "ender_shaft",
            "ender_breath"
        )
    );

    companion object {
        fun fromString(name: String): ArtifactType? {
            return entries.find { it.name.equals(name, ignoreCase = true) }
        }
    }
}
