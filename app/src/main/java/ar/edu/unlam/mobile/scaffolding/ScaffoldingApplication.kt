package ar.edu.unlam.mobile.scaffolding

import android.app.Application
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.UsuarioContainer
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.UsuarioDataContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ScaffoldingApplication : Application() {
    lateinit var container: UsuarioContainer

    override fun onCreate() {
        super.onCreate()
        container = UsuarioDataContainer(this)
    }
}
