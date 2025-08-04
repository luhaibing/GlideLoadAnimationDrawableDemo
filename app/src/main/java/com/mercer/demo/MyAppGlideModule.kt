package com.mercer.demo

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.module.AppGlideModule

/**
 * @author :Mercer
 * @Created on 2024/12/08.
 * @Description:
 *
 */
@com.bumptech.glide.annotation.GlideModule
class MyAppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        LogUtils.e("MyAppGlideModule applyOptions")
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        LogUtils.e("MyAppGlideModule registerComponents")
    }

}