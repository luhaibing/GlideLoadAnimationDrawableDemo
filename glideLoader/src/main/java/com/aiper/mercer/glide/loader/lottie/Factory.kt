package com.aiper.mercer.glide.loader.lottie

import android.content.Context
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory


/**
 * @author :Mercer
 * @Created on 2024/12/06.
 * @Description:
 *
 */
class Factory(private val context: Context) : ModelLoaderFactory<String, LottieDrawable> {
    override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<String, LottieDrawable> {
        return LottieModelLoader(context)
    }
    override fun teardown() {
        // Do nothing.
    }
}