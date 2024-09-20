package com.example.myapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.myapplication.model.response.UserRequest
import com.example.myapplication.model.response.UserResponse
import com.example.myapplication.viewModel.repositories.UserRepository

class UsersViewModel() : ViewModel() {
    private var repository: UserRepository = UserRepository()
    val userData = MutableLiveData<UserResponse>()

    var textCampo by mutableStateOf("Login")
        private set
    var isButton by mutableStateOf(true)
        private set

    fun setIsButton(value: Boolean){
        isButton = value
    }

    fun setValue(){
        if(textCampo == "Login")
            textCampo = "Carregando ..."
        else if(textCampo == "Carregando ...")
            textCampo = "Login"
    }

    fun getUsersViewModel(user: String, pass: String) {
             repository.getUser(UserRequest(user, pass), userData)
    }
}