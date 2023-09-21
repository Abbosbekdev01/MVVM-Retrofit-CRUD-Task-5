package uz.abbosbek.crud_task_5.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.abbosbek.crud_task_5.database.entity.resource.ResourceEntity
import uz.abbosbek.crud_task_5.database.entity.user.UserEntity
import uz.abbosbek.crud_task_5.models.responses.User

@Dao
interface InformationDao {

    @Insert
    suspend fun addUsers(list: List<UserEntity>)

    @Query("select * from userentity")
    suspend fun getUsersDao(): List<UserEntity>

    @Query("delete from userentity")
    suspend fun clearUsers()



    @Insert
    suspend fun addResources(list: List<ResourceEntity>)

    @Query("select * from resourceentity")
    suspend fun getResourceDao(): List<ResourceEntity>

    @Query("delete from resourceentity")
    suspend fun clearResource()


    @Query("select count(*) from userentity")
    suspend fun getUserCount(): Int

    @Query("select count(*) from resourceentity")
    suspend fun getResourceCount(): Int
}