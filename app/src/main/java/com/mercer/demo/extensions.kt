package com.mercer.demo

import com.airbnb.lottie.LottieAnimationView
import com.opensource.svgaplayer.SVGAImageView
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity
import java.net.URL

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