package com.example.teamcity.api

import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.asserts.SoftAssert

open class BaseTest {
    protected lateinit var softy: SoftAssert

    @BeforeMethod(alwaysRun = true)
    fun beforeTest () {
        softy = SoftAssert()
    }

    @AfterMethod(alwaysRun = true)
    fun afterTest () {
        softy.assertAll()
    }
}