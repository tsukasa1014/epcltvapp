package com.daigorian.epcltvapp

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CompatibilityPreferencesTest {
    @Test fun api25AndOlderUsesShortCardsInitially() {
        listOf(22, 23, 24, 25).forEach { assertTrue(CompatibilityPreferences.shouldAutoShortenCards(it)) }
    }

    @Test fun api26AndNewerDefaultsToOff() {
        listOf(26, 28, 30, 34).forEach { assertFalse(CompatibilityPreferences.shouldAutoShortenCards(it)) }
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
