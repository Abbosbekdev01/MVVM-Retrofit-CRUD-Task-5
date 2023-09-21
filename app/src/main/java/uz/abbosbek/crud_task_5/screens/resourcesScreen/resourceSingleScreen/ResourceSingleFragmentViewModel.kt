package uz.abbosbek.crud_task_5.screens.resourcesScreen.resourceSingleScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.abbosbek.crud_task_5.models.responses.ResourceResponse
import uz.abbosbek.crud_task_5.reopsitory.ResourceRepository

class ResourceSingleFragmentViewModel(
    private val resourceRepository: ResourceRepository
) : ViewModel() {

    var postSingleResource: MutableLiveData<ResourceResponse?> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getResourceById(id: Int) {
        viewModelScope.launch {
            val response = resourceRepository.getResourceById(id)

            if (response.isSuccessful) {
                postSingleResource.value = response.body()?.data
                Log.d("AA", "getResourceById: ${response.body()}")
            } else {
                errorMessage.value = response.message()
            }
        }
    }
}