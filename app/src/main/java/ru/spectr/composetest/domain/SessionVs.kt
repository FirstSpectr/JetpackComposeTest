package ru.spectr.composetest.domain

data class SessionVs(
    val id: String = "SOME_CARD_ID",
    val speaker: String = "Степан Чурюканов",
    val date: String = "19 апреля",
    val timeInterval: String = "10:00-11:00",
    val description: String = "Доклад: Краткий экскурс в мир многопоточности",
    val imageUrl: String = "",
    val isFavorite: Boolean = false
)