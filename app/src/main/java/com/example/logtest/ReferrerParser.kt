package com.example.logtest

import android.net.Uri

object ReferrerParser {
    fun parse(link: String): String {
        val str = Uri.parse(link).getQueryParameter("referrer")
        val result: String
        if (str != null) {
            val map = str.split("&").associate {
                val (left, right) = it.split("=")
                left to right
            }
            result = map.toString()
        } else {
            result = "Link is null"
        }
        return result
    }
}