package uz.abbosbek.crud_task_5.reopsitory

import retrofit2.Response
import uz.abbosbek.crud_task_5.database.AppDatabase
import uz.abbosbek.crud_task_5.database.entity.user.UserEntity
import uz.abbosbek.crud_task_5.mapper.mapToUserEntity
import uz.abbosbek.crud_task_5.models.requests.PutRequest
import uz.abbosbek.crud_task_5.models.requests.UserRequest
import uz.abbosbek.crud_task_5.models.responses.BaseSingleResponse
import uz.abbosbek.crud_task_5.models.responses.PutResponse
import uz.abbosbek.crud_task_5.models.responses.UserResponse
import uz.abbosbek.crud_task_5.models.responses.UserSingleResponse
import uz.abbosbek.crud_task_5.retrofit.RetrofitInstance

class UserRepository(
    private val database: AppDatabase
) {
    suspend fun getUsers(): List<UserEntity> {
        database.informationDao().clearUsers()
        database.informationDao()
            .addUsers(
                list = RetrofitInstance.api.getUsers().body()?.data!!.map { it.mapToUserEntity() })
        return database.informationDao().getUsersDao()
    }

    suspend fun getUserById(id: Int): Response<BaseSingleResponse<UserSingleResponse>> {
        return RetrofitInstance.api.getUserByID(id)
    }

    suspend fun addUser(userRequest: UserRequest): Response<UserResponse> {
        return RetrofitInstance.api.addUser(userRequest)
    }

    suspend fun editUser(id: Int, putRequest: PutRequest): Response<PutResponse> {
        return RetrofitInstance.api.editUser(id, putRequest)
    }

    suspend fun deleteUser(id: Int): Response<Any> {
        return RetrofitInstance.api.deleteUser(id)
    }
}