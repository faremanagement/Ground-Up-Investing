package com.faremanagement.groundupinvesting

import androidx.lifecycle.LiveData

class UserRepository(private val userDAO: UserDAO) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<User>> = userDAO.getUsers()

    suspend fun insert(user: User) {
        userDAO.insert(user)
    }
}