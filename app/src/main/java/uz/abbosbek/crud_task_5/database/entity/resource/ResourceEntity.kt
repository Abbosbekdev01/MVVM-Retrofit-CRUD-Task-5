package uz.abbosbek.crud_task_5.database.entity.resource

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ResourceEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val year: Int,
    val color: String,
    val pantone_value: String
)
