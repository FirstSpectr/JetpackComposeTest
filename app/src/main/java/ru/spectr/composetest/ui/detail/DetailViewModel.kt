package ru.spectr.composetest.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.spectr.composetest.data.Repository
import ru.spectr.composetest.data.Session
import ru.spectr.composetest.domain.SessionVs

class DetailViewModel(private val repository: Repository = Repository()) : ViewModel() {
    val session = mutableStateOf(SessionVs())

    fun setId(id: String) {
        viewModelScope.launch {
            try {
                session.value = repository.getSession(id)!!.toVs()
            } catch (e: Exception) {

            }
        }
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