package com.yashovardhan99.bobblebigtext

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(vararg message: Message): List<Long>

    @Query("SELECT * FROM MESSAGES_TABLE")
    fun loadMessages(): Flow<List<Message>>
}