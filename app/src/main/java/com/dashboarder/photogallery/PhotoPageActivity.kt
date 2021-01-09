package com.dashboarder.photogallery

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PhotoPageActivity : AppCompatActivity() {
    private lateinit var photoPageFragment: PhotoPageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_page)

        val fm = supportFragmentManager
        val currentFragment = fm.findFragmentById(R.id.fragment_container)

        if(currentFragment == null) {
            photoPageFragment = PhotoPageFragment.newInstance(intent.data!!)
            fm.beginTransaction().add(R.id.fragment_container, photoPageFragment).commit()
        }
    }

    override fun onBackPressed() {
        //  super.onBackPressed()

        if(photoPageFragment.webViewCanGoBack()) {
            photoPageFragment.webViewGoBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        fun newIntent(context: Context, photoPageUri: Uri): Intent {
            return Intent(context, PhotoPageActivity::class.java).apply {
                data = photoPageUri
            }
        }
    }
}