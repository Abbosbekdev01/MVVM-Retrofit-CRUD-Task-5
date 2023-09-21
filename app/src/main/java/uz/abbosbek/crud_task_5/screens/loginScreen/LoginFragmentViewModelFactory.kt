package uz.abbosbek.crud_task_5.screens.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.abbosbek.crud_task_5.reopsitory.LoginRepository

@Suppress("UNCHECKED_CAST")
class LoginFragmentViewModelFactory(
    private val loginRepository: LoginRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginFragmentViewModel(loginRepository) as T
    }
}