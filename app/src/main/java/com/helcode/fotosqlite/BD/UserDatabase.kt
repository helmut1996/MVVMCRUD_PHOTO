package com.helcode.fotosqlite.BD

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.helcode.fotosqlite.DAO.userDao
import com.helcode.fotosqlite.model.User
import kotlinx.coroutines.CoroutineScope

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun getDao():userDao
    companion object{


        @Volatile
        var INSTANCE:UserDatabase?=null
        var DATABASE_NAME="UserDatabase.db"
        fun getDataBase(context: Context, scope: CoroutineScope): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}