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
        val oldKey = context.getString(R.string.pref_key_show_card_description)
        if (prefs.contains(shortKey)) return resolveShortenCards(prefs.getBoolean(shortKey, false), null)
        if (prefs.contains(oldKey)) return resolveShortenCards(null, prefs.getBoolean(oldKey, false))
        return resolveShortenCards(
            null, null,
            shouldAutoShortenCards(context.packageManager.hasSystemFeature("amazon.hardware.fire_tv"), Build.VERSION.SDK_INT)
        )
    }

    internal fun resolveShortenCards(saved: Boolean?, oldShowDescription: Boolean?, autoDefault: Boolean = false): Boolean =
        saved ?: oldShowDescription?.not() ?: autoDefault

    internal fun shouldAutoShortenCards(isFireTv: Boolean, sdkInt: Int): Boolean =
        isFireTv && sdkInt <= Build.VERSION_CODES.N_MR1

    fun requestConcurrency(context: Context): Int = RequestConcurrency.fromPreference(
        PreferenceManager.getDefaultSharedPreferences(context)
            .getString(context.getString(R.string.pref_key_request_concurrency), null)
    )
}
