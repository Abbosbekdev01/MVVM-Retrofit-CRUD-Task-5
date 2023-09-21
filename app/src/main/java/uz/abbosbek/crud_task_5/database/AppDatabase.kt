package uz.abbosbek.crud_task_5.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.abbosbek.crud_task_5.database.dao.InformationDao
import uz.abbosbek.crud_task_5.database.entity.resource.ResourceEntity
import uz.abbosbek.crud_task_5.database.entity.user.UserEntity


@Database(entities = [UserEntity::class, ResourceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun informationDao(): InformationDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "my_task_5")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }

}