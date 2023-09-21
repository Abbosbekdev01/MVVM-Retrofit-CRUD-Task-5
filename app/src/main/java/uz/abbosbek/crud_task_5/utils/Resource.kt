package uz.abbosbek.crud_task_5.utils

import uz.abbosbek.crud_task_5.database.entity.resource.ResourceEntity
import uz.abbosbek.crud_task_5.database.entity.user.UserEntity

sealed class Resource<T> {

    class Loading<T> : Resource<T>()
    data class Success<T>(val data: List<T>) : Resource<T>()

    //    class SuccessUser<T : Any>(val userdata: List<UserEntity>) : Resource<T>()
//    class SuccessResource<T : Any>(val resource: List<ResourceEntity>) : Resource<T>()
    data class Error<T>(val e: Throwable) : Resource<T>()
}