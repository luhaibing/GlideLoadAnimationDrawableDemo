package com.aiper.mercer.glide.loader.svga

import android.content.Context
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.opensource.svgaplayer.SVGADrawable


/**
 * @author :Mercer
 * @Created on 2024/12/06.
 * @Description:
 *
 */
class Factory(private val context: Context) : ModelLoaderFactory<String, SVGADrawable> {
    override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<String, SVGADrawable> {
        return SvgaModelLoader(context)
    }

    override fun teardown() {
        // Do nothing.
    }
}