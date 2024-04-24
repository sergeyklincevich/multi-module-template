package com.klinserg.news.core

inline val <reified T> T.TAG: String
    get() = T::class.java.simpleName
