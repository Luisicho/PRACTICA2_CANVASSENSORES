package mx.ittepic.tepic.lmhm.ladm_u4_practica2_canvassensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.View

class Acelerometro(l:Lienzo): SensorEventListener {
    var lienso = l
    var sensorManager = lienso.principal.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    var X = 0f
    var Y = 0f
    var Z = 0f
    var pausa = true
    init {
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onSensorChanged(p0: SensorEvent) {
        if (pausa){
            X = p0.values[0].toFloat()
            Y = p0.values[1].toFloat()
            Z = p0.values[2].toFloat()
            lienso.invalidate()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}