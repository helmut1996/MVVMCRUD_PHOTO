package com.helcode.fotosqlite.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helcode.fotosqlite.model.User
import com.helcode.fotosqlite.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.SQLException

class UserViewModel:ViewModel() {
    fun insert(context: Context, user:User) = viewModelScope.launch( Dispatchers.IO)
    {
        try {
            UserRepository.insert(context,user)
        }catch (e:SQLException){
            Log.d("Error",e.sqlState)
        }

    }

    fun getAllUserData(context: Context):LiveData<List<User>>?{
        return UserRepository.getUserData(context)
    }
}