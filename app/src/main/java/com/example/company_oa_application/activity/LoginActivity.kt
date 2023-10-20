package com.example.company_oa_application.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.company_oa_application.databinding.ActivityLoginBinding

import com.example.company_oa_application.data.LoginDataSource
import kotlin.concurrent.thread

class LoginActivity : BaseActivity() {
private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityLoginBinding.inflate(layoutInflater)
     setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login

            login.setOnClickListener {
                thread {
                    var loginuser = LoginDataSource.getInstance()
                        .login(username.text.toString(), password.text.toString())
                    runOnUiThread {
                        if (loginuser?.personal != null) {
                            MainActivity.loginPersonal = loginuser.personal
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            Toast.makeText(this, "登录失败", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
    }
}