package uz.abbosbek.crud_task_5.screens.resourcesScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.abbosbek.crud_task_5.database.AppDatabase
import uz.abbosbek.crud_task_5.reopsitory.ResourceRepository

@Suppress("UNCHECKED_CAST")
class ResourceFragmentViewModelFactory(
    private val resourceRepository: ResourceRepository
) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResourceFragmentViewModel::class.java)) {
            return ResourceFragmentViewModel(resourceRepository) as T
        }
        return throw Exception("---- Error ----")
    }
}