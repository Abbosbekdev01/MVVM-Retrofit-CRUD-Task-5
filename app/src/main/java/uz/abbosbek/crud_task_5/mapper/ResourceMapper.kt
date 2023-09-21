package uz.abbosbek.crud_task_5.mapper

import uz.abbosbek.crud_task_5.database.entity.resource.ResourceEntity
import uz.abbosbek.crud_task_5.models.responses.BaseResponse
import uz.abbosbek.crud_task_5.models.responses.ResourceResponse

fun ResourceResponse.mapToResourceEntity(): ResourceEntity {
    return ResourceEntity(
        id = this.id ?: 0,
        name = this.name ?: "",
        year = this.year ?: 0,
        color = this.color ?: "",
        pantone_value = this.pantone_value ?: ""

    )
}