package com.example.teamcity.api

import com.example.teamcity.api.generators.TestDataGenerator.generate
import com.example.teamcity.api.generators.TestDataStorage
import com.example.teamcity.api.models.TestData
import com.example.teamcity.api.requests.checked.CheckedRequests
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.asserts.SoftAssert

open class BaseTest {
    protected lateinit var softy: SoftAssert
    protected val superUserCheckRequests : CheckedRequests = CheckedRequests(Specifications.retriveSpec().superUserAuth())
    protected lateinit var testData: TestData

    @BeforeMethod(alwaysRun = true)
    fun beforeTest () {
        softy = SoftAssert()
        testData = generate()
    }

    @AfterMethod(alwaysRun = true)
    fun afterTest () {
        softy.assertAll()
        TestDataStorage.deleteCreatedEntities()
    }
}