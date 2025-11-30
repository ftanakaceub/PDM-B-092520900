package com.example.cuidarmais.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cuidarmais.data.repositories.UserRepository
import com.example.cuidarmais.data.room.db.AppDatabase
import com.example.cuidarmais.data.room.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : ViewModel() {

    private val repository: UserRepository
    private var _user: MutableLiveData<User?> = MutableLiveData()
    val user: LiveData<User?> = _user

    init {
        val userDao = AppDatabase.getInstance(application).getUserDao()
        repository = UserRepository(userDao)
        _user.postValue(null)
    }

    fun authenticate(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(repository.authenticate(email, password))
        }
    }

    fun create(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(repository.create(name, email, password))
        }
    }

    fun update(user:User) {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(repository.update(user))
        }
    }
}

class UserViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}