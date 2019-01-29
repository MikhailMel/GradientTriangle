package ru.scratty

import java.awt.Color
import java.awt.Graphics
import kotlin.math.abs

class Triangle(
    private val p0: Vertex,
    private val p1: Vertex,
    private val p2: Vertex
): Figure() {

    override fun draw(graphics: Graphics) {
        graphics.color = Color.black

        drawLine(graphics, p0.copy(), p1.copy())
        drawLine(graphics, p1.copy(), p2.copy())
        drawLine(graphics, p0.copy(), p2.copy())
    }

    private fun drawLine(graphics: Graphics, pos0: Vertex, pos1: Vertex) {
        var steep = false
        if (abs(pos0.x - pos1.x) < abs(pos0.y - pos1.y)) {
            steep = true
            pos0.x = pos0.y.also { pos0.y = pos0.x }
            pos1.x = pos1.y.also { pos1.y = pos1.x }
        }
        if (pos0.x > pos1.x) {
            pos0.x = pos1.x.also { pos1.x = pos0.x }
            pos0.y = pos1.y.also { pos1.y = pos0.y }
        }

        val dX = pos1.x - pos0.x
        val dY = pos1.y - pos0.y
        val dErr = abs(dY) * 2

        var x = pos0.x
        var y = pos0.y
        var err = 0.0f

        while (x <= pos1.x) {
            drawPixel(graphics, if (steep) Vertex(y, x) else Vertex(x, y))

            err += dErr
            if (err > dX) {
                y += if (pos1.y > pos0.y) 1 else -1
                err -= dX * 2
            }

            x++
        }
    }
}