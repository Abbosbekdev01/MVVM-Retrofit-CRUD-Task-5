package uz.abbosbek.crud_task_5.models.responses

data class ResourceResponse(
    val id: Int,
    val name: String,
    val year: Int,
    val color: String,
    val pantone_value: String
)