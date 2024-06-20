package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.ui.components.MyTopBar
import coil.compose.AsyncImage
import com.google.android.gms.maps.model.LatLng

@Preview
@Composable
private fun MyPreview() {
    val navController: NavHostController = rememberNavController()
    val p =
        Producto(
            nombre = "Preview",
            precio = 100.0,
            stock = 100,
            categoria = "Previews",
            nombreProvedor = "PreviewSA",
            ubicacionProveedor = LatLng(0.0, 0.0),
            qr = "preview001",
            fotoUri = "",
        )

    DetalleProducto(navController, p)
}

@Composable
fun DetalleProducto(
    controller: NavHostController,
    producto: Producto?,
) {
    val ubicacionProveedor = producto?.ubicacionProveedor

    Scaffold(
        topBar = {
            MyTopBar(
                onNavigateBack = { controller.popBackStack() },
                title = producto!!.nombre,
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(10.dp)
                    .padding(end = 10.dp)
                    .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .align(Alignment.CenterHorizontally),
                model = producto!!.fotoUri,
                contentDescription = null,
                placeholder = painterResource(R.drawable.splashimg),
            )
            Text(
                text = "Nombre:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Precio:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 10.dp),
            )
            Text(
                text = "$" + producto.precio.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Stock:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 10.dp),
            )
            Text(
                text = producto.stock.toString() + " Unidades",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Categoria:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 10.dp),
            )
            Text(
                text = producto.categoria,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Codigo QR:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 10.dp),
            )
            Text(
                text = producto.qr,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Proveedor:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 10.dp),
            )
            Text(
                text = producto.nombreProvedor,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
            Button(
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                onClick = {
                    controller.navigate("mostrarUbicacion")
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
} // CIERRA LA FUNCION
