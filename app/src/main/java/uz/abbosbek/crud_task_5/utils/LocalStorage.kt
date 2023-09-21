package uz.abbosbek.crud_task_5.utils

import android.content.Context
import android.content.SharedPreferences

object LocalStorage {
    private var sharedPreferences: SharedPreferences? = null
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("app_local_storage", Context.MODE_PRIVATE)
    }

    //todo:
    fun putString(key: String, value: String) {
        sharedPreferences?.edit()?.putString(key, value)?.apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences?.getString(key, null)
    }

    fun removeData(key: String) {
        sharedPreferences?.edit()?.remove(key)?.apply()
    }
}