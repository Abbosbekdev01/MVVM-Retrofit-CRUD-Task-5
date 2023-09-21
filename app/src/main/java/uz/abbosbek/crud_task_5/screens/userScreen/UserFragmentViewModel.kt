package uz.abbosbek.crud_task_5.screens.userScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.abbosbek.crud_task_5.database.entity.user.UserEntity
import uz.abbosbek.crud_task_5.models.requests.PutRequest
import uz.abbosbek.crud_task_5.models.requests.UserRequest
import uz.abbosbek.crud_task_5.models.responses.PutResponse
import uz.abbosbek.crud_task_5.models.responses.UserResponse
import uz.abbosbek.crud_task_5.reopsitory.UserRepository

class UserFragmentViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    val myResponse: MutableLiveData<List<UserEntity>> = MutableLiveData()

    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val editResponse: MutableLiveData<PutResponse> = MutableLiveData()
    val addResponse: MutableLiveData<UserResponse> = MutableLiveData()
    val deleteResponse: MutableLiveData<String> = MutableLiveData()


     fun getUsers() {
        viewModelScope.launch {
            myResponse.value = userRepository.getUsers()
        }
    }

    fun addUser(userRequest: UserRequest) {
        viewModelScope.launch {
            val response = userRepository.addUser(userRequest)
            if (response.isSuccessful) {
                addResponse.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }

    fun editUser(id: Int, putRequest: PutRequest) {
        viewModelScope.launch {
            val response = userRepository.editUser(id, putRequest)
            if (response.isSuccessful) {
                editResponse.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            val response = userRepository.deleteUser(id)

            if (response.isSuccessful) {
                deleteResponse.value = "User Deleted"
            } else {
                errorMessage.value = response.message()
            }
        }
    }
}