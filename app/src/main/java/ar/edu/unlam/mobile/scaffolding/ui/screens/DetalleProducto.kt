package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun DetalleProducto(){


    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    )
    {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0,100,100)),
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .fillMaxWidth(),

            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp,)
        ) {
            IconButton(
                modifier = Modifier
                    .padding(5.dp),
                onClick = {
                    /* navController.navigate(route = ScreenNav.Home.route)*/
                }
            )
            {
                Icon(
                    painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(45.dp)
                )
            }

        }

        Card( colors = CardDefaults.cardColors(
            containerColor = Color.White),
            modifier = Modifier
                .padding(10.dp)

        ){

        }
    }

}//CIERRA LA FUNCION