package uz.abbosbek.crud_task_5.models.responses

data class User(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val avatar: String,
    val email: String
)