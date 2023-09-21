package uz.abbosbek.crud_task_5.database.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val first_name: String,
    val last_name: String,
    val avatar: String,
    val email: String
)