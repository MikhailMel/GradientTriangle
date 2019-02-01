package ru.scratty

import java.awt.Color
import java.awt.Graphics
import kotlin.math.abs

class EmptyTriangle(
    var p0: Vertex,
    var p1: Vertex,
    var p2: Vertex
): Figure() {

    override fun draw(graphics: Graphics) {
        graphics.color = Color.black

        drawLine(graphics, p0, p1)
        drawLine(graphics, p1, p2)
        drawLine(graphics, p0, p2)
    }

    private fun drawLine(graphics: Graphics, pos0: Vertex, pos1: Vertex) {
        val v0 = pos0.copy()
        val v1 = pos1.copy()

        var steep = false
        if (abs(v0.x - v1.x) < abs(v0.y - v1.y)) {
            steep = true
            v0.x = v0.y.also { v0.y = v0.x }
            v1.x = v1.y.also { v1.y = v1.x }
        }
        if (v0.x > v1.x) {
            swap(v0, v1)
        }

        val dX = v1.x - v0.x
        val dY = v1.y - v0.y
        val dErr = abs(dY) * 2

        var x = v0.x
        var y = v0.y
        var err = 0.0f

        while (x <= v1.x) {
            if (steep) {
                drawPixel(graphics, y, x)
            } else {
                drawPixel(graphics, x, y)
            }

            err += dErr
            if (err > dX) {
                y += if (v1.y > v0.y) 1 else -1
                err -= dX * 2
            }

            x++
        }
    }
}