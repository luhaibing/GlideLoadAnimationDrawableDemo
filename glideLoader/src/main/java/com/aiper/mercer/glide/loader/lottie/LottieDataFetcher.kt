package com.aiper.mercer.glide.loader.lottie

import android.content.Context
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher


/**
 * @author :Mercer
 * @Created on 2024/12/06.
 * @Description:
 *
 */
class LottieDataFetcher(
    private var context: Context?, private var model: String?
) : DataFetcher<LottieDrawable> {

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in LottieDrawable>) {
        try {
            LottieCompositionFactory.fromUrl(context, model)
                .addListener { composition: LottieComposition? ->
                    val drawable = LottieDrawable()
                    drawable.setComposition(composition)
                    callback.onDataReady(drawable)
                }
                .addFailureListener { e: Throwable ->
                    (e as? Exception)?.let { callback.onLoadFailed(it) }
                }
        } catch (e: Exception) {
            e.printStackTrace()
            callback.onLoadFailed(e)
        }
    }

    override fun cleanup() {
        // No need to clean up
    }

    override fun cancel() {
        // No need to cancel
    }

    override fun getDataClass(): Class<LottieDrawable> {
        return LottieDrawable::class.java
    }

    override fun getDataSource(): DataSource {
        return DataSource.LOCAL
    }

}