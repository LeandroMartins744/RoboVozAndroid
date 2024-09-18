package com.example.myapplication.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.data.response.UserResponse
import com.example.myapplication.viewModel.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UsersViewModel() : ViewModel() {
    private var repository: UserRepository = UserRepository()
    val liveData: List<UserResponse> = repository.getAll()

    private var _list: List<UserResponse>? = repository.getAll()

    private val _users = MutableLiveData<List<UserResponse>>()
    val user: LiveData<List<UserResponse>> = _users

    fun getUsersViewModel(){
        viewModelScope.launch {
            try{
                var user = repository.getAll()
                _users.value = user

                Log.e("res", _users.value.toString())
            }
            catch(ex: Exception){
                Log.e("res", ex.message.toString())

                _users.value = listOf()
            }
        }
    }



//    val employeeListLiveData: LiveData<List<UserResponse>> = liveData(Dispatchers.IO){
//        emit(repository.getAll())
//    }
//
//    fun userListViewModel() {
//        this.liveData = repository.getAll()
//    }
}