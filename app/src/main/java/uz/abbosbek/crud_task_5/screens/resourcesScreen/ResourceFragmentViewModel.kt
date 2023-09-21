package uz.abbosbek.crud_task_5.screens.resourcesScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import uz.abbosbek.crud_task_5.database.AppDatabase
import uz.abbosbek.crud_task_5.database.entity.resource.ResourceEntity
import uz.abbosbek.crud_task_5.reopsitory.ResourceRepository
import uz.abbosbek.crud_task_5.utils.Resource

class ResourceFragmentViewModel(
    private val resourceRepository: ResourceRepository
) : ViewModel() {

    //    val myResponse = MutableStateFlow<Resource<List<ResourceEntity>>>(Resource.Loading())
    val myResponse: MutableLiveData<List<ResourceEntity>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()


    fun getResources() {
        viewModelScope.launch {
          myResponse.value = resourceRepository.getResources()
        }
    }

//    fun getStateFlow(): StateFlow<Resource<List<ResourceEntity>>> {
////        return myResponse
//    }
}