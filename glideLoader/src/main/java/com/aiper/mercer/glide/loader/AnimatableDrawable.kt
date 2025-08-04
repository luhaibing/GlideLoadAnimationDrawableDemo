package com.aiper.mercer.glide.loader

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.aiper.mercer.glide.loader.svg.SvgDecoder
import com.aiper.mercer.glide.loader.svg.SvgDrawableTranscoder
import com.airbnb.lottie.LottieDrawable
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.module.LibraryGlideModule
import com.caverock.androidsvg.SVG
import com.opensource.svgaplayer.SVGADrawable
import java.io.InputStream

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

        // 注册SVG解码器和转换器
        // registry.register(SVG::class.java, PictureDrawable::class.java, SvgDrawableTranscoder())
        // registry.append(InputStream::class.java, SVGADrawable::class.java, SvgDecoder())

    }

}