package uz.abbosbek.crud_task_5.screens.userScreen.userSingleScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.abbosbek.crud_task_5.models.responses.UserSingleResponse
import uz.abbosbek.crud_task_5.reopsitory.UserRepository

class UserSingleFragmentViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    var myResponse: MutableLiveData<UserSingleResponse?> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getUserById(id: Int) {
        viewModelScope.launch {
            val response = userRepository.getUserById(id)
            if (response.isSuccessful) {
                myResponse.value = response.body()?.data
            } else {
                errorMessage.value = response.message()
            }
        }
    }
}