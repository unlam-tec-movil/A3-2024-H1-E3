package ar.edu.unlam.mobile.scaffolding.ui.screens.configuracion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.ui.components.MyTopBar

@Preview
@Composable
private fun MyPreview() {
    val navController: NavHostController = rememberNavController()

    Configuracion(navController)
}

@Composable
fun Configuracion(controller: NavHostController) {
    Scaffold(
        topBar = { MyTopBar(onNavigateBack = { controller.popBackStack() }, title = "Configuracion") },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(10.dp)
                    .padding(end = 10.dp)
                    .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column {
                Text(
                    text = "Nombre:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
                TextField(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    value = "",
                    onValueChange = { "" },
                )
                Text(
                    text = "Tienda:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
                TextField(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    value = "",
                    onValueChange = { "" },
                )
                Text(
                    text = "Avisarme la falta de stock en mis productos a partir de: ",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
                TextField(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    value = "",
                    onValueChange = {
                        ""
                    },
                    keyboardOptions =
                        KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                        ),
                )
                Box(contentAlignment = Alignment.BottomCenter) {
                    Button(
                        colors =
                            ButtonColors(
                                containerColor = Color.DarkGray,
                                contentColor = Color.White,
                                disabledContainerColor = Color.Transparent,
                                disabledContentColor = Color.Transparent,
                            ),
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                        },
                    ) {
                        Text(
                            "Guardar cambios",
                            fontSize = 17.sp,
                        )
                    }
                }
            }
        }
    }
}
