package uz.abbosbek.crud_task_5.screens.splashScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.abbosbek.crud_task_5.utils.Constants
import uz.abbosbek.crud_task_5.utils.LocalStorage

class SplashFragmentViewModel : ViewModel() {
    var registerToken: MutableLiveData<Boolean> = MutableLiveData()

    fun checkUser() {
        registerToken.value = !LocalStorage.getString(Constants.KEY_TOKEN).isNullOrEmpty()
    }
}