package mx.ittepic.tepic.lmhm.ladm_u4_practica2_canvassensores

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.widget.Toast

class Circulo(l:Lienzo) {
    var x = 0f
    var y = 1800f
    var tam = 5f
    var lienso = l
    var acelera = Acelerometro(lienso)
    var proximi = Proximidad(lienso)

    fun pintarCirculo(c: Canvas){
        x = (acelera.X!!.toFloat()*35*-1)+ 550f
        if (acelera.Z!!>0){
            tam = (acelera.Z!!.toFloat()*10) + 10f
        }
        val p = Paint()
        //Dibujar circulo
        p.style = Paint.Style.FILL
        p.color = Color.GREEN
        val path = android.graphics.Path()
        //Cuadrado
        path.moveTo(x+tam, y+tam)
        path.lineTo(x+tam, y-tam)
        path.lineTo(x-tam, y-tam)
        path.lineTo(x-tam, y+tam)
        c.drawPath(path,p)
        /*
        p.textSize = 50f
        c.drawText("x:${x} y:${y} z:${tam}",100f,100f,p)
        c.drawText("potencia:${proximi.proximidad} ",100f,150f,p)*/
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

    fun colision(objetob:Copo) : Boolean{
        var x2 = x+tam
        var y2 = y+tam

        if (objetob.estaEnArea(x2,y2)) return true
        if (objetob.estaEnArea(x-tam,y-tam)) return true
        if (objetob.estaEnArea(x2,y-tam)) return true
        if (objetob.estaEnArea(x-tam,y2)) return true

        return false
    }
}