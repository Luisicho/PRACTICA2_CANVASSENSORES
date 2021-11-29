package mx.ittepic.tepic.lmhm.ladm_u4_practica2_canvassensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class Proximidad(l:Lienzo): SensorEventListener {
    var lienso = l
    var sensorManager = lienso.principal.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    var proximidad = 0
    var pausa = true
    init {
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
            SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onSensorChanged(p0: SensorEvent) {
        if (pausa){
            proximidad = p0.values[0].toInt()
            lienso.invalidate()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}
