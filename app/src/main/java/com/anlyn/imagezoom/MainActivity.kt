package com.anlyn.imagezoom

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.anlyn.imagezoom.presentation.ZoomableImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val zoom = findViewById<ZoomableImageView>(R.id.zoom)
        GlobalScope.launch {
            val bitmap = setZoom()
//            Log.d("bitmap",bitmap?.hashCode().toString())
//            if(bitmap!=null)
            zoom.setImageBitmap(bitmap)
        }

    }

    suspend fun setZoom():Bitmap{

        val bitmap = withContext(Dispatchers.IO) {
            val drawable = resources.getDrawable(R.drawable.ic_android_black_24dp,null)
            drawable.toBitmap()

        }
        bitmap
        return bitmap
    }
}