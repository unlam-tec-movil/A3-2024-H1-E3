package ar.edu.unlam.mobile.scaffolding

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.WelcomeViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.welcome.InputAnimated
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun welcomeScreenCambiaLuegoDePresionarBoton() {
        val mockViewModel = mock(WelcomeViewModel::class.java)

        composeTestRule.setContent {
            InputAnimated(
                coroutineScope = rememberCoroutineScope(),
                onNavigateToHomeScreen = {},
                mockViewModel,
            )
        }

        composeTestRule.onNodeWithText("Ingrese su nombre")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Continuar")
            .performClick()

        composeTestRule.onNodeWithText("Por favor, ingrese el nombre de su negocio")
            .assertIsDisplayed()
    }
}
