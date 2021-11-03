package com.eicnam.steamfeed.ui.webactivity

import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.R

class WebviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView = findViewById<WebView>(R.id.game_webview)
        webView.webViewClient = WebViewClient()
        //val game_url = intent.getStringExtra("url")
        webView.loadUrl(Uri.parse("https://steamdb.info/").toString())
    }
}