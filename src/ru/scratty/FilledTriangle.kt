package ru.scratty

import java.awt.Color
import java.awt.Graphics

open class FilledTriangle(
    var p0: Vertex,
    var p1: Vertex,
    var p2: Vertex
): Figure() {

    var color: Color = Color.black

    override fun draw(graphics: Graphics) {
        graphics.color = color

        drawFilledTriangle(graphics, p0, p1, p2)
    }

    protected fun drawFilledTriangle(graphics: Graphics, v0: Vertex, v1: Vertex, v2: Vertex) {
        if (v0.y > v1.y) swap(v0, v1)
        if (v0.y > v2.y) swap(v0, v2)
        if (v1.y > v2.y) swap(v1, v2)

        val height = v2.y - v0.y
        for (i in 0 until height) {
            val secondHalf = i > (v1.y - v0.y) || v1.y == v0.y
            val segmentHeight = if (secondHalf) v2.y - v1.y else v1.y - v0.y

            val alpha = i.toFloat() / height
            val beta = (i.toFloat() - if (secondHalf) v1.y - v0.y else 0) / segmentHeight

            var a = v0 + (v2 - v0) * alpha
            var b = if (secondHalf) v1 + (v2 - v1) * beta else v0 + (v1 - v0) * beta

            if (a.x > b.x) {
                a = b.apply { b = a }
            }

            var j = a.x
            while (j < b.x) {
                drawPixel(graphics, j ,v0.y + i)
                j++
            }
        }
    }

}