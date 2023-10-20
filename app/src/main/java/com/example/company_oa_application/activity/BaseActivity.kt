
package com.example.company_oa_application.activity

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mikepenz.iconics.Iconics
import com.xuexiang.xui.XUI


open class BaseActivity:AppCompatActivity() {
    private val permissions = arrayOf(
        "android.permission.INTERNET",
        "android.permission.ACCESS_NETWORK_STATE",
        "android.permission.READ_EXTERNAL_STORAGE",
        "android.permission.WRITE_EXTERNAL_STORAGE",
        "android.permission.ACCESS_COARSE_LOCATION",
        "android.permission.ACCESS_FINE_LOCATION"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        ActivityList.addActivity(this)
        super.onCreate(savedInstanceState)
        XUI.initTheme(this)
        Iconics.init(this)

        checkPermission(permissions)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun checkPermission(permissions:Array<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions, 200);
                }
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                }
                else{
                    Toast.makeText(this,"用户拒绝授权",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}