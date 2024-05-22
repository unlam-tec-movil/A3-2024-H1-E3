package ar.edu.unlam.mobile.scaffolding

import android.app.Application
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.ProductoContainer
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.ProductoDataContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ScaffoldingApplication : Application() {
    lateinit var container: ProductoContainer

    override fun onCreate() {
        super.onCreate()
        container = ProductoDataContainer(this)
    }
}
