package mx.ittepic.tepic.lmhm.ladm_u4_practica2_canvassensores

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi

class Lienzo(p:MainActivity): View(p)  {
    val principal = p
    val hiloNieve = MovimientoNieve(this)
    val objeto = Circulo(this)
    var colisiono = false
    init {
        hiloNieve.start()
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val p = Paint()//Iniciar vairable Paint
        if (objeto.proximi.proximidad != 0){
            if (!colisiono){
                hiloNieve.pausar = true
                objeto.acelera.pausa= true
                //Dibujar fondo
                canvas.drawColor(Color.WHITE)
                p.color = Color.BLUE
                p.textSize = 70f
                canvas.drawText("Pausar el juego",100f,100f,p)
                canvas.drawText("con proximidad",100f,170f,p)

                canvas.drawText("<-- Mover a la izquierda",100f,400f,p)
                canvas.drawText("--> Mover a la derecha",100f,500f,p)
                canvas.drawText("Arriba o abajo para ",100f,600f,p)
                canvas.drawText("cambiar de tamaño",100f,670f,p)
                p.color = Color.RED
                canvas.drawText("¡¡¡Esquiva las esferas!!!",100f,300f,p)
                objeto.pintarCirculo(canvas)
                (0..50).forEach {
                    hiloNieve.nieve[it].pintarCopo(canvas)
                    if (hiloNieve.nieve[it].colision(objeto)){
                        hiloNieve.pausar = false
                        objeto.acelera.pausa= false
                        objeto.proximi.pausa = false
                        p.color = Color.RED
                        p.textSize = 70f
                        canvas.drawText("Chocaste con una esfera",100f,800f,p)
                        objeto.pintarCirculo(canvas)
                        (0..50).forEach {
                            hiloNieve.nieve[it].pintarCopo(canvas)
                        }
                    }
                }
            }
        }else{
            hiloNieve.pausar = false
            objeto.acelera.pausa= false
            //Dibujar fondo
            canvas.drawColor(Color.BLACK)
            p.color = Color.WHITE
            p.textSize = 70f
            canvas.drawText("Juego Pausado",100f,300f,p)
            objeto.pintarCirculo(canvas)
            (0..50).forEach {
                hiloNieve.nieve[it].pintarCopo(canvas)
            }
        }
    }

}
class MovimientoNieve(p:Lienzo):Thread(){
    val puntero = p
    var pausar = true
    val nieve = ArrayList<Copo>()
    init{
        (0..50).forEach{
            val copo = Copo()
            nieve.add(copo)
        }
    }
    override fun run(){
        super.run()
        while(true){
            if (pausar){
                (0..50).forEach {
                    nieve[it].moverCopo()
                }
                puntero.principal.runOnUiThread {
                    puntero.invalidate()
                }

            }
            sleep(200)
        }
    }
}