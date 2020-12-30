package com.example.android4a.presentation.main

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.domain.entity.User
import com.example.android4a.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
   // private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
): ViewModel(){
    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()



    fun onClickedLogin(emailUser: String, password: String){
        viewModelScope.launch(Dispatchers.IO){
            val user : User? = getUserUseCase.invoke(emailUser)
            val loginStatus = if(user != null && user.password == password){
                LoginSuccess(user.email, user.password, user)
            } else {
                LoginError
            }
            withContext(Dispatchers.Main){
                loginLiveData.value = loginStatus
            }

        }
    }
    fun onClickedCreateAccount(emailUser: String, password: String, fragmentManager: FragmentManager) {
        val createAccount = Create(emailUser, password)
        createAccount.show(fragmentManager, "activity_dialog")
    }
}



