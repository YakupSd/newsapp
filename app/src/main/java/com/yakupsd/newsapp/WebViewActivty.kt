package com.yakupsd.newsapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class WebViewActivty : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_activty)

        val myWebView: WebView = findViewById(R.id.newsWebView)
        myWebView.settings.javaScriptEnabled = true
        myWebView.loadUrl(intent.getStringExtra("URL").orEmpty())

    }

    companion object {
        fun callingIntent(context: Context,url:String) = Intent(context,WebViewActivty::class.java).apply {
            putExtra("URL",url)
        }
    }
}