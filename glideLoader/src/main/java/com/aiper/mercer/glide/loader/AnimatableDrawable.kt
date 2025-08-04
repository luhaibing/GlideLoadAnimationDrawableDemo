package com.aiper.mercer.glide.loader

import android.content.Context
import android.util.Log
import com.airbnb.lottie.LottieDrawable
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.module.LibraryGlideModule
import com.opensource.svgaplayer.SVGADrawable

/**
 * @author :Mercer
 * @Created on  2024/12/08.
 * @Description:
 *
 */
@com.bumptech.glide.annotation.GlideModule
class AnimatableDrawable : LibraryGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        LogUtils.e("AnimatableDrawable 注册 组件. ")
        registry.append(String::class.java, LottieDrawable::class.java, com.aiper.mercer.glide.loader.lottie.Factory(context))
        registry.append(String::class.java, SVGADrawable::class.java, com.aiper.mercer.glide.loader.svga.Factory(context))
    }

}