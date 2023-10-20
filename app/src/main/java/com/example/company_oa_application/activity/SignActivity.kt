package com.example.company_oa_application.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.MapView
import com.example.company_oa_application.R
import com.example.company_oa_application.adapter.LocationListener
import com.example.company_oa_application.databinding.ActivitySignBinding
import com.example.company_oa_application.entity.attendance.Sign
import com.example.company_oa_application.entity.attendance.SignRule
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xuexiang.xui.widget.actionbar.TitleBar
import com.xuexiang.xui.widget.button.roundbutton.RoundButton
import okhttp3.FormBody
import java.lang.Exception
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread
import kotlin.math.pow
import kotlin.math.sqrt

class SignActivity : BaseActivity() {
    private lateinit var signRule: SignRule
    private lateinit var mapView: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SDKInitializer.setAgreePrivacy(this.applicationContext,true)
        LocationClient.setAgreePrivacy(true)
        SDKInitializer.initialize(this.applicationContext)
        SDKInitializer.setCoordType(CoordType.BD09LL)

        setContentView(R.layout.activity_sign)
        val binding = ActivitySignBinding.inflate(layoutInflater)
        mapView = findViewById(R.id.baiduMapView)
        var listener= doMapLocated(mapView)
        var signButton = findViewById<RoundButton>(R.id.signButton)
        var time = findViewById<TextView>(R.id.time)

        setTimeListener(time)
        var signTitle = findViewById<TitleBar>(R.id.sign_title)
        signTitle.setLeftClickListener{
            super.onDestroy()
        }
        val host = "${getString(R.string.host)}/sign/getSignRuleByPersonal/${MainActivity.loginPersonal!!.personalId}"

        thread {
            var connectWebUtil =  ConnectWebUtil.getInstance()
             var build = FormBody.Builder().build()
            val messages = connectWebUtil.post(build,host)
            val type: Type = object : TypeToken<SignRule>() {}.type
            Gson().fromJson<SignRule>(messages,type)
            sign(signButton,listener)
        }

    }
    private fun setTimeListener(textView: TextView) {
        thread {
            var dateFormat =SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            dateFormat.timeZone = TimeZone.getTimeZone("Etc/GMT-8")
            while (true){
                textView.text = dateFormat.format(Date())
                Thread.sleep(1000)
            }
        }
    }

    private fun sign(button: Button,locationListener: LocationListener){
        button.setOnClickListener {
            var loginPersonal = MainActivity.loginPersonal
            val locationData = locationListener.getLocationData()
            val status= if(checkSign(locationData)) 1L else 2L
                var sign = Sign().apply {
                    signStatus = status
                    signLocation = locationData.address
                    signRule = signRule
                    signTime = Date(System.currentTimeMillis())
                    signUser = MainActivity.loginPersonal!!.user
                    latitude = locationData.latitude
                    longitude = locationData.longitude
                }
            var signMessage = Gson().toJson(sign)
            thread {
                var connectWebUtil =  ConnectWebUtil.getInstance()
                val host = getString(R.string.host) + "/sign/saveSign"
                var build = FormBody.Builder().add("sign", signMessage).build()
                val messages = connectWebUtil.post(build,host)
                runOnUiThread {
                    if ( messages.equals("true"))
                        Toast.makeText(this,"签到成功",Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(this,"签到失败",Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun checkSign(locationData: LocationListener.LocationData):Boolean {
        var date = Calendar.getInstance()
        var onRule = Calendar.getInstance()
        onRule.time = signRule.signOnTime
        onRule.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH))
        var offRule = Calendar.getInstance()
        offRule.time = signRule.signOffTime
        offRule.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH))
        if(date.get(Calendar.HOUR)<12) {
            if(date.after(onRule)) return false
        }else{
            if(date.before(offRule)) return false
        }
        val radius = sqrt((locationData.latitude - signRule.signLatitude).pow(2.0) + (locationData.longitude - signRule.signLongitude).pow(2.0))
        return radius < signRule.signRadius
    }

    private fun doMapLocated(mapView: MapView):LocationListener{
        val baiduMap = mapView.map
        val client = LocationClient(applicationContext)
        baiduMap.run{
            isMyLocationEnabled = true
        }
        val option = LocationClientOption()
        option.apply {
            coorType = "bd0911"
            scanSpan = 1000
            openGps = true
            isIgnoreKillProcess = false
            isNeedNewVersionRgc = true
            setScanSpan(1000)
            setIsNeedAddress(true)
        }
        val locationListener = LocationListener(mapView)
        client.run {
            locOption = option
            registerLocationListener(locationListener)
            start()
        }
        return locationListener
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}