package uz.abbosbek.crud_task_5.screens.registerScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.abbosbek.crud_task_5.reopsitory.RegisterRepository

@Suppress("UNCHECKED_CAST")
class RegisterFragmentViewModelFactory(
    private val registerRepository: RegisterRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterFragmentViewModel(registerRepository) as T

    }
}