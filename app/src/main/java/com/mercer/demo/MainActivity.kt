package com.mercer.demo

import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.ImageViewTarget
import com.caverock.androidsvg.SVG
import com.mercer.demo.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import okio.IOException
import okio.source
import java.io.File

class MainActivity : AppCompatActivity() {
    companion object {
        val SVG_URL = "https://simpleicons.org/icons/github.svg"
        val SVGA_URL =
            "https://dev-management-bucket.s3.cn-north-1.amazonaws.com.cn/equipment/%E5%BC%80%E6%9C%BA%E5%87%86%E5%A4%87-%E9%80%9A%E7%94%A8_1731913405707.svga"
        val LOTTIE_URL =
            "https://dev-management-bucket.s3.cn-north-1.amazonaws.com.cn/equipment/x5pro%E9%85%8D%E7%BD%91%E6%93%8D%E4%BD%9C_1729057883965.json"
        val PNG_URL =
            "https://dev-management-bucket.s3.cn-north-1.amazonaws.com.cn/equipment/W2%E4%BA%A7%E5%93%81%E5%9B%BE%E7%89%87%20%281%29_1730174106589.png"

        // val PNG_URL = "https://www.freepnglogos.com/uploads/google-logo-png/google-logo-png-webinar-optimizing-for-success-google-business-webinar-13.png"
        val JPEG_URL = "https://fastly.picsum.photos/id/779/800/600.jpg?hmac=AqQVSvC4EDrWxi8BnTBaxQ12KUncMaGmXsLuoFYZeT0"
        // val PNG_URL = ""
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
            downloadSVG(SVG_URL)
        }
    }

    private fun load() {
        /*
        binding.apply {
            v1.loadFromURL(SVGA)
            v2.loadFromURL(LOTTIE)
        }

        return
        */

        binding.apply {
            Glide.with(v1)
                // .`as`()
                .load(SVGA_URL)
                //.placeholder(LoadingDrawable(v1, v1.context.getColor(R.color.color_eeeeee), 50F))
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.loaded_failure_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                // .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                // .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(object : ImageViewTarget<Drawable>(v1) {
                    override fun setResource(resource: Drawable?) {
                        resource ?: return
                        Log.e("TAG", "******************* svga 加载成功 *******************")
                        v1.setImageDrawable(resource)
                        v1.startAnimation()
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                        Log.e("TAG", "******************* svga 加载失败 *******************")
                    }

                    override fun onStart() {
                        super.onStart()
                        Log.e("TAG", "******************* svga 开始加载 *******************")
                    }
                })

            Glide.with(v2)
                .load(LOTTIE_URL)
                // .placeholder(LoadingDrawable(v2, v2.context.getColor(R.color.color_eeeeee), 50F))
                // .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.loaded_failure_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(object : ImageViewTarget<Drawable>(v2) {
                    override fun setResource(resource: Drawable?) {
                        resource ?: return
                        Log.e("TAG", "******************* lottie 加载成功 *******************")
                        v2.setImageDrawable(resource)
                        v2.playAnimation()
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                        Log.e("TAG", "******************* lottie 加载失败 *******************")
                    }

                    override fun onStart() {
                        super.onStart()
                        Log.e("TAG", "******************* lottie 开始加载 *******************")
                    }
                })
        }

    }

    private fun downloadSVG(value: String) {

        val md5 = value.md5
        val path = File(cacheDir, "$md5.svg")
        val predicate: (File) -> Unit = {
            val svg: SVG = SVG.getFromInputStream(it.inputStream())
            val picture = svg.renderToPicture()
            binding.root.post {
                binding.v3.setImageDrawable(PictureDrawable(picture))
            }
        }
        if (path.exists()) {
            /*
            val inputStream = path.inputStream()
            val svg: SVG = SVG.getFromInputStream(inputStream)
            val picture = svg.renderToPicture()
            binding.root.post {
                binding.v3.setImageDrawable(PictureDrawable(picture))
            }
            */
            LogUtils.e("******************* svg 已存在 >>> 加载缓存 *******************")
            predicate(path)
            return
        }
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder()
            .url(value)
            .build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                LogUtils.e("******************* svg 加载失败 *******************")
            }

            override fun onResponse(call: Call, response: Response) {
                LogUtils.e("******************* svg 加载成功 *******************")
                val inputStream = response.body?.source()?.inputStream() ?: return
                if (path.exists()) {
                    path.delete()
                }
                path.createNewFile()
                // val source = path.source()
                val buffer = Buffer()
                buffer.readFrom(inputStream)
                buffer.writeTo(path.outputStream())
                LogUtils.e("******************* svg 下载成功 >>> 写入文件成功 >>> 开始加载 *******************")
                /*
                val svg: SVG = SVG.getFromInputStream(inputStream)
                val picture = svg.renderToPicture()
                binding.root.post {
                    binding.v3.setImageDrawable(PictureDrawable(picture))
                }
                */
                predicate(path)
            }
        })
    }

}