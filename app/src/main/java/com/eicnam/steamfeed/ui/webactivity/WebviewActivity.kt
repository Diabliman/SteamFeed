package com.eicnam.steamfeed.ui.webactivity

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.eicnam.steamfeed.R


class WebviewActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView = findViewById<WebView>(R.id.game_webview)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.userAgentString = System.getProperty("http.agent")
        webView.settings.domStorageEnabled = true

        val gameUrl = intent.getStringExtra("url")
        webView.loadUrl(Uri.parse(gameUrl).toString())
    }
}