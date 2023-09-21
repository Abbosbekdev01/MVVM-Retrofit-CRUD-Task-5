package uz.abbosbek.crud_task_5.reopsitory

import android.util.Log
import retrofit2.Response
import uz.abbosbek.crud_task_5.database.AppDatabase
import uz.abbosbek.crud_task_5.database.entity.resource.ResourceEntity
import uz.abbosbek.crud_task_5.mapper.mapToResourceEntity
import uz.abbosbek.crud_task_5.models.responses.BaseResponse
import uz.abbosbek.crud_task_5.models.responses.BaseSingleResponse
import uz.abbosbek.crud_task_5.models.responses.ResourceResponse
import uz.abbosbek.crud_task_5.retrofit.RetrofitInstance

class ResourceRepository(
    private val database: AppDatabase
) {

    suspend fun getResources(): List<ResourceEntity> {
        database.informationDao().clearResource()
        database.informationDao()
            .addResources(
                list = RetrofitInstance.api.getResources()
                    .body()?.data!!.map { it.mapToResourceEntity() })
        return database.informationDao().getResourceDao()
    }

    suspend fun getResourceById(id: Int): Response<BaseSingleResponse<ResourceResponse>> {
        Log.d("AAAAA", "getResourceById: ${RetrofitInstance.api.getResourceById(id).body()}")
        return RetrofitInstance.api.getResourceById(id)
    }
}