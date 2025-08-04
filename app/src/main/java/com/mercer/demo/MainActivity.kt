package com.mercer.demo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mercer.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
        val SVG = "https://simpleicons.org/icons/github.svg"
        val SVGA = "https://dev-management-bucket.s3.cn-north-1.amazonaws.com.cn/equipment/%E5%BC%80%E6%9C%BA%E5%87%86%E5%A4%87-%E9%80%9A%E7%94%A8_1731913405707.svga"
        val LOTTIE = "https://dev-management-bucket.s3.cn-north-1.amazonaws.com.cn/equipment/x5pro%E9%85%8D%E7%BD%91%E6%93%8D%E4%BD%9C_1729057883965.json"
        val PNG = "https://dev-management-bucket.s3.cn-north-1.amazonaws.com.cn/equipment/W2%E4%BA%A7%E5%93%81%E5%9B%BE%E7%89%87%20%281%29_1730174106589.png"
    }
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.bt.setOnClickListener {
            load()
        }
    }

    private fun load() {
        binding.apply {
            v1.loadFromURL(SVGA)
            v2.loadFromURL(LOTTIE)
        }
    }

}