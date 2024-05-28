package ar.edu.unlam.mobile.scaffolding.ui.screens.AgregarProductoVender // ktlint-disable package-name

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AgregarProductoVender() {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .fillMaxWidth(),

            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        ) {
            IconButton(
                modifier = Modifier
                    .padding(5.dp),
                onClick = {
                    /* navController.navigate(route = ScreenNav.Home.route)*/
                },
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(45.dp),
                )
            }
        } // CIERRA CARD PARA ATRAS

        Column(modifier = Modifier) {
            Text(
                text = "Agregar Producto para vender",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color(39, 40, 41),
                fontSize = 22.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 1.dp),
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Información:",
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.labelLarge,
            )
            // EMPIEZA LA LISTA DE PRODUCTOS
            val list = listOf("producto1", "producto2", "producto3")

            var isExpanded by remember {
                mutableStateOf(false)
            }

            var selectedText by remember {
                mutableStateOf(list[0])
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = {
                        isExpanded = !isExpanded
                    },
                ) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                        },
                    )
                    ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                        list.forEachIndexed { index, text ->
                            DropdownMenuItem(
                                text = { Text(text = text) },
                                onClick = {
                                    selectedText = list[index]
                                    isExpanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                color = Color.Black,
                text = "Cantidad:",
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(10.dp),
            )
            TextField(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .background(Color.White),
                value = "",
                onValueChange = {},
            )
            Spacer(modifier = Modifier.height(15.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(10.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            ) {
                Text(
                    color = Color.Black,
                    text = "Escanear",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .shadow(12.dp, CardDefaults.shape)
                        .background(Color.DarkGray)
                        .padding(3.dp),
                    onClick = {
                        // logica para escanear
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(45.dp)
                            .padding(10.dp),
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color(39, 40, 41)),
                    onClick = {
                        /* navController.navigate(route = ScreenNav.Home.route)*/
                    },
                ) {
                    Text(
                        "AGREGAR",
                        fontSize = 17.sp,
                        color = Color.White,
                    )
                }
            }
        }
    } // CIERRA EL PRIMER COLUMN
} // CIERRA LA FUNCION
