package com.example.company_oa_application.utils

import android.app.Activity
import com.example.company_oa_application.activity.ActivityList
import okhttp3.*
import java.util.concurrent.TimeUnit

class ConnectWebUtil {
    private var okHttpClient: OkHttpClient = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build()
    fun post(body: RequestBody, url:String):String{
        val request = Request.Builder().url(url).post(body).build()
        val call = okHttpClient.newCall(request);
        var execute = call.execute()
        return if(execute.isSuccessful) {
            execute.body()!!.string()
        } else {
            ""
        }
    }
    fun downLoad(url: String,fileName:String?){
        var build = FormBody.Builder().build()
        val request = Request.Builder().url(url).post(build).build()
        val newCall = okHttpClient.newCall(request)
        val path = "${ActivityList.getThisActivity().filesDir.path}/${fileName}"
        newCall.enqueue(Download(path))
    }
    companion object {
        private val connectWebUtil = ConnectWebUtil()
        public fun getInstance():ConnectWebUtil{
            return connectWebUtil
        }
    }
    fun get(url:String):String{
        try {
            val request = Request.Builder().url(url).get().build()
            val call = okHttpClient.newCall(request);
            var execute = call.execute()
            return if(execute.isSuccessful) {
                execute.body().toString()
            } else {
                ""
            }
        }catch (e:Exception){
            e.printStackTrace()
            return ""
        }
    }
}