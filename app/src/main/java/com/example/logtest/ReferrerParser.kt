package com.example.logtest

import android.net.Uri

object ReferrerParser {
    var map = mutableMapOf<String, String>()
    fun parse(link: String): Map<String, String> {
        val str = Uri.parse(link).getQueryParameter("referrer")
        if (str != null) {
            str.split("&").associateTo(map) {
                val (left, right) = it.split("=")
                left to right
            }
        }
        return map
    }
}