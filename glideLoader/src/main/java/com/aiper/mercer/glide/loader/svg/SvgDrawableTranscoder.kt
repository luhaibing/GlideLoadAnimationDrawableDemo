package com.aiper.mercer.glide.loader.svg

import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.caverock.androidsvg.SVG
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder

/**
 * @author      Mercer
 * @Created     2025/08/04.
 * @Description:
 *
 */

class SvgDrawableTranscoder : ResourceTranscoder<SVG, PictureDrawable> {

    override fun transcode(
        toTranscode: Resource<SVG>,
        options: com.bumptech.glide.load.Options
    ): Resource<PictureDrawable> {
        val svg = toTranscode.get()
        val picture = svg.renderToPicture()
        val drawable = PictureDrawable(picture)
        return SimpleResource(drawable)
    }
}