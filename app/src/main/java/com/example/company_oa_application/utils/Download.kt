package com.example.company_oa_application.utils

import android.telecom.Call
import android.view.View
import android.widget.Toast
import com.example.company_oa_application.activity.ActivityList
import okhttp3.Callback
import okhttp3.Response
import org.jetbrains.annotations.NotNull
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.util.*
import kotlin.concurrent.thread

class Download(var fileDirPath:String):Callback{
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            ActivityList.getThisActivity().runOnUiThread {
                Toast.makeText(ActivityList.getThisActivity(),"下载失败",Toast.LENGTH_LONG).show()
            }
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            thread {
                val inputStream = Objects.requireNonNull(response.body())!!.byteStream();
                val target = File(fileDirPath);
                if (target.exists()) target.delete()
                target.createNewFile()
                val fileOutputStream = FileOutputStream(target);
                try {
                    var readBytes = inputStream.readBytes()
                    fileOutputStream.write(readBytes);
                    fileOutputStream.flush();
                    ActivityList.getThisActivity().runOnUiThread {
                        Toast.makeText(ActivityList.getThisActivity(),"下载成功",Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace();
                }
            }
        }
}
