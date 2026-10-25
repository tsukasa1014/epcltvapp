package com.daigorian.epcltvapp

/**
 * EPGStation v2 への取得並列数の設定値を正規化する。
 */
object RequestConcurrency {
    const val DEFAULT: Int = 5

    val CHOICES: List<Int> = listOf(2, 4, 5, 8, 12)

    fun fromPreference(value: String?): Int =
        value?.trim()?.toIntOrNull()?.let(::fromValue) ?: DEFAULT

    fun fromValue(value: Int): Int =
        if (value in CHOICES) value else DEFAULT
}
