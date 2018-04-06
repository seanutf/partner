package com.sean.partner.module.camera

import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sean.partner.R

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_camera)
        if (null == savedInstanceState){
            if(Build.VERSION.SDK_INT >= LOLLIPOP)
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Camera21Fragment.newInstance())
                        .commit()
            else
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Camera21Fragment.newInstance())
                        .commit()
        }
    }

}
