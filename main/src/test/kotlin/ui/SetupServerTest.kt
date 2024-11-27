package ui

import com.example.teamcity.ui.setup.FirstStartPage
import org.testng.annotations.Test

class SetupServerTest : BaseUiTest () {
    @Test(groups = ["Setup"])
    fun setupTeamCityServerTest() {
        FirstStartPage.open().setupFirstStart()
    }
}