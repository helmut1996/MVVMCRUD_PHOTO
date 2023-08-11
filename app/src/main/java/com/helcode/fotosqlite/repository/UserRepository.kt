package com.helcode.fotosqlite.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.helcode.fotosqlite.BD.UserDatabase
import com.helcode.fotosqlite.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class UserRepository {

    companion object{
        private val scope = CoroutineScope(SupervisorJob())
        private var userDatabase:UserDatabase?= null
        fun initialseDB(context:Context):UserDatabase?{
            return UserDatabase.getDataBase(context, scope = scope)
        }

         suspend fun insert(context: Context, user:User){
            userDatabase= initialseDB(context)
                userDatabase?.getDao()?.insert(user)

        }

        fun getUserData(context: Context):LiveData<List<User>>?{
            userDatabase= initialseDB(context)
            return  userDatabase?.getDao()?.getAllUserData()
        }
    }
}