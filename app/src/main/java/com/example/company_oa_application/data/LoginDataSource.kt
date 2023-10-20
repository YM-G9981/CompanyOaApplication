package com.example.company_oa_application.data

import android.util.Log
import com.example.company_oa_application.R
import com.example.company_oa_application.activity.ActivityList
import com.example.company_oa_application.entity.hrmResource.Personal
import com.example.company_oa_application.entity.hrmResource.User
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.FormBody
import java.lang.reflect.Type

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): User? {
            try {
                var build = FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build()

                var message = ConnectWebUtil.getInstance()
                        .post(build, "${ActivityList.getThisActivity().getString(R.string.host)}/login")
                var build2 = FormBody.Builder().build()
                if(message == "true") {
                    var userString = ConnectWebUtil.getInstance()
                        .post(build2, "${ActivityList.getThisActivity().getString(R.string.host)}/getUser/getUserLikeUsername/${username}")
                    if(userString=="") return null
                    Log.e("message",userString)
                    val type: Type = object : TypeToken<List<User?>?>() {}.type
                    var users = Gson().fromJson<List<User>>(userString, type)
                    return users[0]
                }
            } catch (e: Throwable) {
                return null;
            }
            return null;
    }
    companion object {
        fun getInstance(): LoginDataSource {
            return LoginDataSource()
        }
    }
    fun logout() {
        // TODO: revoke authentication
    }
}