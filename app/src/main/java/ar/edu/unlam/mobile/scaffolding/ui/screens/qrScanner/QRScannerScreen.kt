package ar.edu.unlam.mobile.scaffolding.ui.screens.qrScanner

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.stock.AgregarStockViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.stock.VenderProductosViewModel
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QRScannerScreen(
    controller: NavHostController,
    agregarStockViewModel: AgregarStockViewModel,
    venderProductosViewModel: VenderProductosViewModel,
) {
    val context = LocalContext.current
    val launcher: ActivityResultLauncher<ScanOptions> =
        rememberLauncherForActivityResult(
            contract = ScanContract(),
            onResult = {
                if (it.contents == null) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    controller.popBackStack()
                } else {
                    Toast.makeText(context, "Scanned: " + it.contents, Toast.LENGTH_LONG).show()
                    agregarStockViewModel.qr = it.contents
                    venderProductosViewModel.qr = it.contents
                    controller.popBackStack()
                }
            },
        )
    LaunchedEffect(key1 = true) {
        scanQR(launcher)
    }
}

private fun scanQR(launcher: ActivityResultLauncher<ScanOptions>) {
    val options = ScanOptions()
    options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
    options.setPrompt("Escanear QR")
    options.setCameraId(0) // Use a specific camera of the device
    options.setBeepEnabled(false)
    options.setBarcodeImageEnabled(false)

    launcher.launch(options)
}
