package com.helcode.fotosqlite.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.helcode.fotosqlite.model.User

@Dao
interface userDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(user:User)

    @Query("SELECT * FROM users ORDER BY id_foto ASC")
    fun getAllUserData():LiveData<List<User>>
}