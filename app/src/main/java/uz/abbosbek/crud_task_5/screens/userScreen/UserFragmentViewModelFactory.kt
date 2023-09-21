package uz.abbosbek.crud_task_5.screens.userScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.abbosbek.crud_task_5.database.AppDatabase
import uz.abbosbek.crud_task_5.reopsitory.UserRepository
import uz.abbosbek.crud_task_5.retrofit.ApiService
import uz.abbosbek.crud_task_5.utils.NetworkHelper

@Suppress("UNCHECKED_CAST")
class UserFragmentViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserFragmentViewModel::class.java)) {
            return UserFragmentViewModel(userRepository) as T
        }
        return throw Exception("---- Error ----")
    }
}