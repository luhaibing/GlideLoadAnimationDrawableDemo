package com.aiper.mercer.glide.loader.svga

import android.content.Context
import android.util.Log
import com.aiper.mercer.glide.loader.md5
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.caverock.androidsvg.SVG
import com.opensource.svgaplayer.SVGADrawable
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.File
import java.io.IOException
import java.net.URL


/**
 * @author :Mercer
 * @Created on 2024/12/06.
 * @Description:
 *
 */
class SvgaDataFetcher(
    private var context: Context, private var model: String?
) : DataFetcher<SVGADrawable> {

    val client: OkHttpClient by lazy { OkHttpClient.Builder().build() }

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in SVGADrawable>) {
        try {
            val url = model
            if (url == null) {
                callback.onLoadFailed(Exception("SVG解析失败"))
                return
            }
            val md5 = url.md5
            val path = File(context.cacheDir, "$md5.svga")
            val parser = SVGAParser(context)
            if (path.exists()) {
                // TODO: 暂未处理文件损毁的情况
                loadFile(path, parser, md5, callback)
                return
            }
            val request = Request.Builder().url(url).build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    LogUtils.e("******************* svg 加载失败 *******************")
                    callback.onLoadFailed(Exception("SVG解析失败"))
                }
                override fun onResponse(call: Call, response: Response) {
                    LogUtils.e("******************* svg 加载成功 *******************")
                    val inputStream = response.body?.source()?.inputStream()
                    if (inputStream==null) {
                        callback.onLoadFailed(Exception("inputStream 读取失败."))
                        return
                    }
                    if (path.exists()) {
                        path.delete()
                    }
                    path.createNewFile()
                    // val source = path.source()
                    val buffer = Buffer()
                    buffer.readFrom(inputStream)
                    buffer.writeTo(path.outputStream())
                    loadFile(path, parser, md5, callback)
                }
            })
            /*
            // val drawable = SVGADrawable()
            val parser = SVGAParser(context)
            parser.decodeFromURL(URL(model), object : SVGAParser.ParseCompletion {
                override fun onComplete(videoItem: SVGAVideoEntity) {
                    val drawable = SVGADrawable(videoItem)
                    callback.onDataReady(drawable)
                }

                override fun onError() {
                    // 错误处理
                    callback.onLoadFailed(Exception("SVG解析失败"))
                }
            })
            */
        } catch (e: Exception) {
            Log.e("TAG", "SVGADrawable: ", e)
            e.printStackTrace()
            callback.onLoadFailed(e)
        }
    }

    private fun loadFile(
        path: File,
        parser: SVGAParser,
        md5: String,
        callback: DataFetcher.DataCallback<in SVGADrawable>
    ) {
        val inputStream = path.inputStream()
        parser.decodeFromInputStream(inputStream, md5, object : SVGAParser.ParseCompletion {
            override fun onComplete(videoItem: SVGAVideoEntity) {
                val drawable = SVGADrawable(videoItem)
                callback.onDataReady(drawable)
            }

            override fun onError() {
                // 错误处理
                callback.onLoadFailed(Exception("SVG解析失败"))
            }
        })
    }

    override fun cleanup() {
        // No need to clean up
    }

    override fun cancel() {
        // No need to cancel
    }

    override fun getDataClass(): Class<SVGADrawable> {
        return SVGADrawable::class.java
    }

    override fun getDataSource(): DataSource {
        return DataSource.LOCAL
    }

}