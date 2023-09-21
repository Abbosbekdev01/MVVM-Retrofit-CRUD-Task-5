package uz.abbosbek.crud_task_5.mapper

import uz.abbosbek.crud_task_5.database.entity.user.UserEntity
import uz.abbosbek.crud_task_5.models.responses.BaseResponse
import uz.abbosbek.crud_task_5.models.responses.User

fun User.mapToUserEntity(): UserEntity {
    return UserEntity(
        id = this.id ?: 0,
        first_name = this.first_name ?: "",
        last_name = this.last_name ?: "",
        avatar = this.avatar ?: "",
        email = this.email ?: ""
    )
}