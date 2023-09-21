package uz.abbosbek.crud_task_5.screens.loginScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.abbosbek.crud_task_5.models.requests.UserLoginRequest
import uz.abbosbek.crud_task_5.models.responses.UserLoginResponse
import uz.abbosbek.crud_task_5.reopsitory.LoginRepository

class LoginFragmentViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    var myResponseLogin: MutableLiveData<UserLoginResponse> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getLogin(userLoginRequest: UserLoginRequest) {

        viewModelScope.launch {
            val response = loginRepository.logIn(userLoginRequest)
            if (response.isSuccessful) {
                myResponseLogin.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }
}