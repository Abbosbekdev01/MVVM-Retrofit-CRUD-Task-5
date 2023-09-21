package uz.abbosbek.crud_task_5.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.abbosbek.crud_task_5.utils.Constants

object RetrofitInstance {

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    private val client = OkHttpClient.Builder().apply {
//        addInterceptor(headerInterceptor())
//    }.build
//
//    private fun headerInterceptor(): Interceptor = Interceptor { chain ->
//        val request = chain.request()
//            .newBuilder()
//            .addHeader("Content-type", "application/json; charset=UTF-8")
//            .build()
//        chain.proceed(request)
//    }
}