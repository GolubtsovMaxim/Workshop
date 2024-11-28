@file:Suppress("DEPRECATION")

package com.example.teamcity.api.generators

import org.apache.commons.lang3.RandomStringUtils

object RandomData {
    val TEST_PREFIX: String = "test_PROJECT_"
    val MAX_LENGTH: Int = 10

    fun getString(): String {
        return TEST_PREFIX + RandomStringUtils.randomAlphabetic(MAX_LENGTH)
    }

    fun getString(length: Int): String {
        return TEST_PREFIX + RandomStringUtils.randomAlphabetic(kotlin.math.max(length - TEST_PREFIX.length, MAX_LENGTH))
    }

    fun getInt(): Int {
        return (0..10000).random()
    }
}