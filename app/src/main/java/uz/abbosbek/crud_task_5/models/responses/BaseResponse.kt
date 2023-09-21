package uz.abbosbek.crud_task_5.models.responses

data class BaseResponse<T>(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<T>
)
