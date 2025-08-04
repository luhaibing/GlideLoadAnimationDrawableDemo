package com.aiper.mercer.glide.loader.svg

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import java.io.IOException
import java.io.InputStream

/**
 * @author      Mercer
 * @Created     2025/08/04.
 * @Description:
 *
 */


class SvgDecoder : ResourceDecoder<InputStream, SVG> {

    override fun handles(source: InputStream, options: Options): Boolean {
        // 可以在这里添加检查以确认是SVG文件
        return true
    }

    @Throws(IOException::class)
    override fun decode(
        source: InputStream,
        width: Int,
        height: Int,
        options: Options
    ): Resource<SVG> {
        return try {
            // 从输入流解析SVG
            val svg = SVG.getFromInputStream(source)

            // 可选：设置默认尺寸
            svg.documentWidth = width.toFloat()
            svg.documentHeight = height.toFloat()

            SimpleResource(svg)
        } catch (ex: SVGParseException) {
            throw IOException("Cannot load SVG from stream", ex)
        }
    }
}