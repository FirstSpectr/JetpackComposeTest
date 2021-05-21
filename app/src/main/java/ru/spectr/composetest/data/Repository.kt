package ru.spectr.composetest.data

class Repository {
    suspend fun getSessions() = MockSessions

    suspend fun getSession(id: String) = MockSessions.find { it.id == id }
}