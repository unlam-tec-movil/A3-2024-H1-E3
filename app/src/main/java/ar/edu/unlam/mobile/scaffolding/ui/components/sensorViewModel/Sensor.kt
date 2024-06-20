package ar.edu.unlam.mobile.scaffolding.ui.components.sensorViewModel

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class Sensor(context: Context, private val onShake: () -> Unit) : SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private var lastShakeTime = 0L

    init {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val now = System.currentTimeMillis()
        if (now - lastShakeTime > 1000) { // Minimum 1 second between shakes
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val gX = x / SensorManager.GRAVITY_EARTH
            val gY = y / SensorManager.GRAVITY_EARTH
            val gZ = z / SensorManager.GRAVITY_EARTH

            // G-Force will be close to 1 when there is no movement.
            val gForce = Math.sqrt((gX * gX + gY * gY + gZ * gZ).toDouble())

            if (gForce > 2.5) { // Shake threshold
                lastShakeTime = now
                onShake()
            }
        }
    }

    override fun onAccuracyChanged(
        sensor: Sensor?,
        accuracy: Int,
    ) {
        // No implementation needed
    }

    fun unregister() {
        sensorManager.unregisterListener(this)
    }
}
