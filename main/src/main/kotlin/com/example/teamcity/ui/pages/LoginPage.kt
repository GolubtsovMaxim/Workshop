package com.example.teamcity.ui.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.example.teamcity.api.models.User

import com.codeborne.selenide.Selenide.`$`

class LoginPage : BasePage() {

    companion object {
        private val LOGIN_URL = "/login.html"

        fun open() : LoginPage {
            return Selenide.open(LOGIN_URL, LoginPage::class.java)
        }
    }

    private val inputUsername: SelenideElement = `$`("#username")
    private val inputPassword: SelenideElement = `$`("#password")
    private val inputSubmitLogin: SelenideElement = `$`(".loginButton")

    fun login(user: User): ProjectsPage {
        inputUsername.`val`(user.username)
        inputPassword.`val`(user.password)
        inputSubmitLogin.click()

        return Selenide.page(ProjectsPage::class.java)
    }
}