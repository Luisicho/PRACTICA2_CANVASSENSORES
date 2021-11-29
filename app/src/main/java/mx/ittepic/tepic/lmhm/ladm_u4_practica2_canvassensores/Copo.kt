package mx.ittepic.tepic.lmhm.ladm_u4_practica2_canvassensores

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Copo {
    var x = 0f
    var y = 500f
    var tam = 0f
    init {
        x = (Math.random()*1000+20).toFloat()
        tam = ((Math.random()*5)+10).toFloat()
    }
    fun moverCopo(){
        y += tam
        if (y>2200) y = 0f
    }
    fun pintarCopo(c: Canvas){
        val p = Paint()
        p.color = Color.RED
        c.drawCircle(x,y,tam, p)
    }
    fun estaEnArea(toqueX:Float, toqueY:Float) : Boolean{
        var x2 = x+tam
        var y2 = y+tam

        if (toqueX in x-tam..x2){
            if (toqueY in y-tam..y2){
                return true
            }
        }

        /*
        x,y         x2,y
        ------------
        |           |
        ------------
        x,y2        x2,y2
         */

        return false
    }
    fun colision(objetob:Circulo) : Boolean{
        var x2 = x+tam
        var y2 = y+tam

        if (objetob.estaEnArea(x2,y2)) return true
        if (objetob.estaEnArea(x-tam,y-tam)) return true
        if (objetob.estaEnArea(x2,y-tam)) return true
        if (objetob.estaEnArea(x-tam,y2)) return true

        return false
    }
}