package com.adl.firebaseauthentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_promotion_page.*

class PromotionPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion_page)

        val title = intent.extras?.get("title") as String?
        val url = intent.extras?.get("url") as String?

        val webViewClient = WebViewClient()
        webView.webViewClient = webViewClient

        webView.settings.apply{
            javaScriptEnabled = true
            builtInZoomControls = true

        }

        webView.loadUrl(url.toString())
    }
}