package uz.abbosbek.crud_task_5

import android.app.Application
import uz.abbosbek.crud_task_5.utils.LocalStorage

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(context = this)
    }
}