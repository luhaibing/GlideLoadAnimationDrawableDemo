package com.aiper.mercer.glide.loader.svga

import android.content.Context
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoader.LoadData
import com.bumptech.glide.signature.ObjectKey
import com.opensource.svgaplayer.SVGADrawable


/**
 * @author :Mercer
 * @Created on 2024/12/06.
 * @Description:
 *
 */
class SvgaModelLoader(
    val context: Context
) : ModelLoader<String, SVGADrawable> {

    override fun buildLoadData(model: String, width: Int, height: Int, options: Options): LoadData<SVGADrawable> {
        return LoadData(ObjectKey(model), SvgaDataFetcher(context, model))
    }

    override fun handles(model: String): Boolean {
        return model.endsWith(".svga")
    }

}