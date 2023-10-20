package com.example.company_oa_application.adapter

import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.mapapi.map.MapView
import com.baidu.mapapi.map.MyLocationData

class LocationListener(var mMapView: MapView?):BDAbstractLocationListener() {
    private lateinit var locationData: LocationData
    override fun onReceiveLocation(location: BDLocation?) {
        //mapView 销毁后不在处理新接收的位置
        if (location == null||mMapView == null){
            return;
        }
        val locData = MyLocationData.Builder()
            .accuracy(location.radius)
            // 此处设置开发者获取到的方向信息，顺时针0-360
            .direction(location.direction).latitude(location.latitude)
            .longitude(location.longitude).build();
        mMapView!!.map.setMyLocationData(locData);
        locationData = LocationData(location.latitude,location.longitude,location.addrStr)
    }
    fun getLocationData():LocationData{
        return locationData
    }
    data class LocationData(var latitude:Double, var longitude:Double, var address :String){

    }
}