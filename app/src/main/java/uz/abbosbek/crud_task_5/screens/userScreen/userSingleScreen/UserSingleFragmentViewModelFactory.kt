package uz.abbosbek.crud_task_5.screens.userScreen.userSingleScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.abbosbek.crud_task_5.reopsitory.UserRepository

@Suppress("UNCHECKED_CAST")
class UserSingleFragmentViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserSingleFragmentViewModel(userRepository) as T
    }
}