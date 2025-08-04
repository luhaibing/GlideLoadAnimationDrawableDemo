package com.aiper.mercer.glide.loader.lottie

import android.content.Context
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoader.LoadData
import com.bumptech.glide.signature.ObjectKey



/**
 * @author :Mercer
 * @Created on 2024/12/06.
 * @Description:
 *
 */
class LottieModelLoader(
    val context: Context
) : ModelLoader<String, LottieDrawable> {

    override fun buildLoadData(model: String, width: Int, height: Int, options: Options): LoadData<LottieDrawable> {
        return LoadData(ObjectKey(model), LottieDataFetcher(context, model))
    }

    override fun handles(model: String): Boolean {
        return model.endsWith(".json")
    }

}