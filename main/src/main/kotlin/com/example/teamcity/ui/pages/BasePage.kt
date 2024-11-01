package com.example.teamcity.ui.pages

import java.time.Duration

abstract class BasePage {
    protected val BASE_WAITING: Duration = Duration.ofSeconds(30)
}