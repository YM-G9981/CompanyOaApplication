package com.example.company_oa_application.activity;

object ActivityList {
    private val list:ArrayList<BaseActivity> = ArrayList()
    fun addActivity(activity: BaseActivity){
        list.add(activity)
    }
    fun removeActivity(activity: BaseActivity){
        list.remove(activity)
    }
    fun clear(){
        list.clear()
    }
    fun getThisActivity():BaseActivity{
        return list.last()
    }
}
