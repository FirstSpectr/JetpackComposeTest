package ru.spectr.composetest.data

import kotlinx.coroutines.delay

class Repository {
    suspend fun getSessions(): List<Session> {
        delay(1000)
        return MockSessions
    }

    suspend fun getSession(id: String): Session? {
        delay(500)
        return MockSessions.find { it.id == id }
    }
}