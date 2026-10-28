package com.daigorian.epcltvapp

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CompatibilityPreferencesTest {
    @Test fun fireOs5UsesShortCardsInitially() {
        assertTrue(CompatibilityPreferences.shouldAutoShortenCards(true, 22))
    }

    @Test fun fireOs6UsesShortCardsInitially() {
        assertTrue(CompatibilityPreferences.shouldAutoShortenCards(true, 25))
    }

    @Test fun newerFireTvDefaultsToOff() {
        listOf(26, 28, 30).forEach { assertFalse(CompatibilityPreferences.shouldAutoShortenCards(true, it)) }
    }

    @Test fun otherAndroidTvDefaultsToOff() {
        assertFalse(CompatibilityPreferences.shouldAutoShortenCards(false, 25))
    }

    @Test fun unsetUsesDeviceDefault() {
        assertTrue(CompatibilityPreferences.resolveShortenCards(null, true))
    }

    @Test fun savedOffWinsOverAutomaticOn() {
        assertFalse(CompatibilityPreferences.resolveShortenCards(false, true))
    }

    @Test fun unsetDefaultsToOff() {
        assertFalse(CompatibilityPreferences.resolveShortenCards(null))
    }

    @Test fun savedOnIsPreserved() {
        assertTrue(CompatibilityPreferences.resolveShortenCards(true))
    }

    @Test fun savedOffIsPreserved() {
        assertFalse(CompatibilityPreferences.resolveShortenCards(false))
    }
}
