package ru.spectr.composetest.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.spectr.composetest.Router
import ru.spectr.composetest.data.Repository
import ru.spectr.composetest.data.Session
import ru.spectr.composetest.domain.SessionVs

class MainViewModel(private val repository: Repository = Repository()) : ViewModel() {
    val sessions = mutableStateOf(listOf<SessionVs>())
    val query = mutableStateOf("")
    val snackbarVisibleState = mutableStateOf(false)

    private var router: Router? = null

    init {
        viewModelScope.launch {
            try {
                sessions.value = repository.getSessions().map { it.toVs() }
            } catch (e: Exception) {

            }
        }
    }

    fun onCardClick(id: String) = router?.navigateTo(id)

    fun attachRouter(router: Router) {
        this.router = router
    }

    fun onFavoriteClick(id: String) {
        val item = sessions.value.find { it.id == id } ?: return

        if (!item.isFavorite && sessions.value.count { it.isFavorite } >= 3) {
            snackbarVisibleState.value = true
            viewModelScope.launch {
                delay(3000)
                snackbarVisibleState.value = false
            }
            return
        }

        val mutableList = sessions.value.toMutableList()
        val index = sessions.value.indexOf(item)
        mutableList[index] = item.copy(isFavorite = !item.isFavorite)
        sessions.value = mutableList
    }

    private fun Session.toVs() = SessionVs(
        id = id,
        speaker = speaker,
        description = description,
        timeInterval = timeInterval,
        date = date,
        imageUrl = imageUrl,
        isFavorite = false
    )
}
