package com.mercer.demo

import com.aiper.mercer.glide.loader.LoadingDrawable
import com.airbnb.lottie.LottieAnimationView
import com.opensource.svgaplayer.SVGAImageView
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity
import java.math.BigInteger
import java.net.URL
import java.security.MessageDigest

/**
 * @author :Mercer
 * @Created on 2024/12/06.
 * @Description:
 *   xx
 */

fun LottieAnimationView.loadFromURL(source: String) {
    val loadingDrawable = LoadingDrawable(this, context.getColor(R.color.color_eeeeee), 50F)
    setImageDrawable(loadingDrawable)
    setFailureListener {
        loadingDrawable.stop()
        setImageResource(R.drawable.loaded_failure_placeholder)
    }
    addLottieOnCompositionLoadedListener {
        loadingDrawable.stop()
    }
    setAnimationFromUrl(source)
    playAnimation()
}

fun SVGAImageView.loadFromURL(source: String) {
    val loadingDrawable = LoadingDrawable(this, context.getColor(R.color.color_eeeeee), 50F)
    setImageDrawable(loadingDrawable)
    val parser = SVGAParser(context)
    parser.decodeFromURL(URL(source), object : SVGAParser.ParseCompletion {
        override fun onComplete(videoItem: SVGAVideoEntity) {
            loadingDrawable.stop()
            setVideoItem(videoItem)
            startAnimation()
        }

        override fun onError() {
            // 错误处理
            loadingDrawable.stop()
            setImageResource(R.drawable.loaded_failure_placeholder)
        }
    })
}


fun String.toMd5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

val Any.md5: String
    get() {
        return this.toString().toMd5()
    }