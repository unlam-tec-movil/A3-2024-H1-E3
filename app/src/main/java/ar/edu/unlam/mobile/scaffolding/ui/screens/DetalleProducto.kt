package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DetalleProducto() {
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
                    Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(45.dp),
                )
            }
        } // CIERRA CARD PARA ATRAS

        Column(modifier = Modifier) {
            Text(
                text = "Detalles del Producto",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color(39, 40, 41),
                fontSize = 22.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 1.dp),
            )
        }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray,
            ),
            modifier = Modifier
                .padding(10.dp)
                .aspectRatio(3f),

        ) {
            Text(
                text = "Imagen del producto",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Especificaciones:",

                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.labelLarge,
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(216, 217, 218)),
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(10.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            ) {
                Text(
                    color = Color.Black,
                    text = "Nombre:" /*poner la variable adentro, Nombre:${} */,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(10.dp),
                )

                Spacer(modifier = Modifier.height(15.dp))
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(10.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Precio:",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(10.dp),
                        color = Color.Black,
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Divider()

                    Text(
                        text = "Stock:",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(10.dp),
                        color = Color.Black,
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Divider()

                    Text(
                        text = "Categoria:",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(10.dp),
                        color = Color.Black,
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Divider()
                    Text(
                        text = "Nombre del Proovedor:",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(10.dp),
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider()
                }
            }

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
                    text = "Código QR",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
                )
                Icon(
                    Icons.Filled.Refresh,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(45.dp)
                        .align(Alignment.CenterHorizontally),
                )

                Spacer(modifier = Modifier.height(5.dp))
            }
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
                        "Ver al proovedor en el Mapa",
                        fontSize = 17.sp,
                        color = Color.White,
                    )
                }
            }
        }
    } // CIERRA EL PRIMER COLUMN
} // CIERRA LA FUNCION