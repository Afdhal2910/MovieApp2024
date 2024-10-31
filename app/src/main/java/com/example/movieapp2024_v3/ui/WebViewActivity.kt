package com.example.movieapp2024_v3.ui

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp2024_v3.databinding.ActivityWebViewBinding


class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("EXTRA_URL")
        url?.let {
            val webSettings: WebSettings =   binding.webView.getSettings()
            webSettings.javaScriptEnabled = true
            binding.webView.setWebViewClient(WebViewClient())
            binding.webView.loadUrl(it)
        }
    }
}