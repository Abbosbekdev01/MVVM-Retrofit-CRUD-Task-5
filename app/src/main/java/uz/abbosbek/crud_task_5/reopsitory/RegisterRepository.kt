package uz.abbosbek.crud_task_5.reopsitory

import retrofit2.Response
import uz.abbosbek.crud_task_5.models.requests.UserRegisterRequest
import uz.abbosbek.crud_task_5.models.responses.UserRegisterResponse
import uz.abbosbek.crud_task_5.retrofit.RetrofitInstance

class RegisterRepository {

    suspend fun register(userRegisterRequest: UserRegisterRequest): Response<UserRegisterResponse> {
        return RetrofitInstance.api.register(userRegisterRequest)
    }
}