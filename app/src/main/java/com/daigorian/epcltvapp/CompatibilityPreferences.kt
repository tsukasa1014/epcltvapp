package com.daigorian.epcltvapp

import android.content.Context
import android.os.Build
import androidx.preference.PreferenceManager

object CompatibilityPreferences {
    fun migrate(context: Context) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val shortKey = context.getString(R.string.pref_key_shorten_card_content)
        if (!prefs.contains(shortKey)) {
            prefs.edit().putBoolean(shortKey, shortenCards(context)).apply()
        }
    }

    fun shortenCards(context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val shortKey = context.getString(R.string.pref_key_shorten_card_content)
        if (prefs.contains(shortKey)) return resolveShortenCards(prefs.getBoolean(shortKey, false))
        return resolveShortenCards(null, shouldAutoShortenCards(Build.VERSION.SDK_INT))
    }

    internal fun resolveShortenCards(saved: Boolean?, autoDefault: Boolean = false): Boolean =
        saved ?: autoDefault

    internal fun shouldAutoShortenCards(sdkInt: Int): Boolean =
        sdkInt <= Build.VERSION_CODES.N_MR1
}
