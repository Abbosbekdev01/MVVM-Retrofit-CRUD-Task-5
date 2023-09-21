package uz.abbosbek.crud_task_5.models.responses

data class UserResponse(
    val id: Int,
    val createdAt: String,
    val job: String,
    val name: String
)