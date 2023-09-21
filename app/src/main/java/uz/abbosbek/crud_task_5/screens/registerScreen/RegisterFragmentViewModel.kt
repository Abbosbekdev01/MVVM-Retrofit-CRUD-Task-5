package uz.abbosbek.crud_task_5.screens.registerScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.abbosbek.crud_task_5.models.requests.UserRegisterRequest
import uz.abbosbek.crud_task_5.models.responses.UserRegisterResponse
import uz.abbosbek.crud_task_5.reopsitory.RegisterRepository

class RegisterFragmentViewModel(private val registerRepository: RegisterRepository) : ViewModel() {
    var myResponseRegister: MutableLiveData<UserRegisterResponse> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getRegister(userRegisterRequest: UserRegisterRequest) {
        viewModelScope.launch {
            val response = registerRepository.register(userRegisterRequest)
            if (response.isSuccessful) {
                myResponseRegister.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }
}