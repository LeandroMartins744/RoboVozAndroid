package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.model.response.UserRequest
import com.example.myapplication.model.response.UserResponse
import com.example.myapplication.viewModel.repositories.UserRepository
import kotlinx.coroutines.launch


class UsersViewModel() : ViewModel() {
    private var repository: UserRepository = UserRepository()
    val userData = MutableLiveData<UserResponse>()

    private val _users = MutableLiveData<List<UserResponse>>()
    val user: LiveData<List<UserResponse>> = _users

    private val _user = MutableLiveData<UserResponse>()
    val userLogin: LiveData<UserResponse> = _user

    private val _res2 = MutableLiveData<Boolean>()
    val res2: LiveData<Boolean> = _res2

    fun getUsersViewModel(user: String, pass: String) {
       // viewModelScope.launch {
             repository.getUser(UserRequest(user, pass), userData)
//            if (res == null)
//                _user.value = UserResponse(
//                    id = 1,
//                    name = "",
//                    email = "",
//                    password = "",
//                    phone = "",
//                    firstAccess = false,
//                    active = false
//                )
//            else
//                _user.value = res
//
//            Log.e("res", _users.value.toString())
//            _res2.value = true
       // }
    }




//    val employeeListLiveData: LiveData<List<UserResponse>> = liveData(Dispatchers.IO){
//        emit(repository.getAll())
//    }
//
//    fun userListViewModel() {
//        this.liveData = repository.getAll()
//    }
}