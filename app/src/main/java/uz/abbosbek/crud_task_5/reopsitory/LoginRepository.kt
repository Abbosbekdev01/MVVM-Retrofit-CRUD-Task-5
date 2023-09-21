package uz.abbosbek.crud_task_5.reopsitory

import retrofit2.Response
import uz.abbosbek.crud_task_5.models.requests.UserLoginRequest
import uz.abbosbek.crud_task_5.models.responses.UserLoginResponse
import uz.abbosbek.crud_task_5.retrofit.RetrofitInstance

class LoginRepository() {

    suspend fun logIn(userLoginRequest: UserLoginRequest):Response<UserLoginResponse>{
        return RetrofitInstance.api.logIn(userLoginRequest)
    }
}