package com.aiper.mercer.glide.loader

import java.math.BigInteger
import java.security.MessageDigest

/**
 * @author      Mercer
 * @Created     2025/08/04.
 * @Description:
 *
 */
internal val Any.md5: String
    get() {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toString().toByteArray())).toString(16).padStart(32, '0')
    }