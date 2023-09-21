package uz.abbosbek.crud_task_5.models.requests

data class UserRegisterRequest(
    val email: String,
    val password: String
)