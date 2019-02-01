package ru.scratty

import java.awt.Color
import java.awt.Graphics

class FilledTriangle(
    var p0: Vertex,
    var p1: Vertex,
    var p2: Vertex,
    var color: Color
): Figure() {

    override fun draw(graphics: Graphics) {
        graphics.color = color

        if (p0.y > p1.y) swap(p0, p1)
        if (p0.y > p2.y) swap(p0, p2)
        if (p1.y > p2.y) swap(p1, p2)

        val height = p2.y - p0.y
        for (i in 0 until height) {
            val secondHalf = i > (p1.y - p0.y) || p1.y == p0.y
            val segmentHeight = if (secondHalf) p2.y - p1.y else p1.y - p0.y

            val alpha = i.toFloat() / height
            val beta = (i.toFloat() - if (secondHalf) p1.y - p0.y else 0) / segmentHeight

            var a = p0 + (p2 - p0) * alpha
            var b = if (secondHalf) p1 + (p2 - p1) * beta else p0 + (p1 - p0) * beta

            if (a.x > b.x) {
                a = b.apply { b = a }
            }

            var j = a.x
            while (j < b.x) {
                drawPixel(graphics, j ,p0.y + i)
                j++
            }
        }
    }

}