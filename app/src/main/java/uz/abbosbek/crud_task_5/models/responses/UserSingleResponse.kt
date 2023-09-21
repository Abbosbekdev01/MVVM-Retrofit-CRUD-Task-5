package uz.abbosbek.crud_task_5.models.responses

data class UserSingleResponse(
    val id: Int,
    val avatar: String,
    val email: String,
    val first_name: String,
    val last_name: String
)