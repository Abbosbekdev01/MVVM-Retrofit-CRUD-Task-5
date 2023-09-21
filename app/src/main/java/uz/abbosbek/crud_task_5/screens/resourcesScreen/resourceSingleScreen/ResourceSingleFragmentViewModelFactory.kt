package uz.abbosbek.crud_task_5.screens.resourcesScreen.resourceSingleScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.abbosbek.crud_task_5.reopsitory.ResourceRepository

@Suppress("UNCHECKED_CAST")
class ResourceSingleFragmentViewModelFactory(
    private val resourceRepository: ResourceRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResourceSingleFragmentViewModel(resourceRepository) as T
    }
}