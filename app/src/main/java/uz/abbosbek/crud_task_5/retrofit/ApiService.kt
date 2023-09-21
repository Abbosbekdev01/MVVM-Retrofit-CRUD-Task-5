package uz.abbosbek.crud_task_5.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.abbosbek.crud_task_5.models.requests.PutRequest
import uz.abbosbek.crud_task_5.models.requests.UserLoginRequest
import uz.abbosbek.crud_task_5.models.requests.UserRegisterRequest
import uz.abbosbek.crud_task_5.models.requests.UserRequest
import uz.abbosbek.crud_task_5.models.responses.BaseResponse
import uz.abbosbek.crud_task_5.models.responses.BaseSingleResponse
import uz.abbosbek.crud_task_5.models.responses.PutResponse
import uz.abbosbek.crud_task_5.models.responses.ResourceResponse
import uz.abbosbek.crud_task_5.models.responses.User
import uz.abbosbek.crud_task_5.models.responses.UserLoginResponse
import uz.abbosbek.crud_task_5.models.responses.UserRegisterResponse
import uz.abbosbek.crud_task_5.models.responses.UserResponse
import uz.abbosbek.crud_task_5.models.responses.UserSingleResponse

interface ApiService {

    @POST("/api/register")
    suspend fun register(@Body userRegisterRequest: UserRegisterRequest): Response<UserRegisterResponse>

    @POST("/api/login")
    suspend fun logIn(@Body userLoginRequest: UserLoginRequest): Response<UserLoginResponse>

    //todo: Resource ma'lumotlarini API olib kelyabmiz
    @GET("/api/unknown")
    suspend fun getResources(): Response<BaseResponse<ResourceResponse>>

    @GET("/api/unknown/{id}")
    suspend fun getResourceById(@Path("id") id: Int): Response<BaseSingleResponse<ResourceResponse>>

    //todo: Resource finished

    @GET("/api/users")
    suspend fun getUsers(): Response<BaseResponse<User>>

    @GET("/api/users/{id}")
    suspend fun getUserByID(
        @Path("id") id: Int
    ): Response<BaseSingleResponse<UserSingleResponse>>

    @POST("/api/users")
    suspend fun addUser(
        @Body userRequest: UserRequest
    ): Response<UserResponse>

    @PUT("/api/users/{id}")
    suspend fun editUser(
        @Path("id") id: Int,
        @Body putRequest: PutRequest
    ): Response<PutResponse>

    @DELETE("/api/users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Int
    ): Response<Any>
}