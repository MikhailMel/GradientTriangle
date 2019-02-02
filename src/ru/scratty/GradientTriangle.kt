package ru.scratty

import java.awt.Color
import java.awt.Graphics
import kotlin.math.abs

class GradientTriangle(
    p0: Vertex,
    p1: Vertex,
    p2: Vertex,
    var c0: Color,
    var c1: Color,
    var c2: Color
): FilledTriangle(p0, p1, p2) {

    private var area: Int = 0

    override fun draw(graphics: Graphics) {
        area = edge(p0, p1, p2)

        drawFilledTriangle(graphics, p0, p1, p2)
    }

    override fun drawPixel(graphics: Graphics, x: Int, y: Int) {
        graphics.color = calculateColor(x, y)

        super.drawPixel(graphics, x, y)
    }

    private fun calculateColor(x: Int, y: Int): Color {
        val vertex = Vertex(x, y)

        val w0 = abs(edge(p2, p0, vertex) / area.toFloat())
        val w1 = abs(edge(p0, p1, vertex) / area.toFloat())
        val w2 = abs(edge(p1, p2, vertex) / area.toFloat())

        var r = w0 * c0.red + w1 * c1.red + w2 * c2.red
        var g = w0 * c0.green + w1 * c1.green + w2 * c2.green
        var b = w0 * c0.blue + w1 * c1.blue + w2 * c2.blue

        if (r > 255) r = 255f
        if (g > 255) g = 255f
        if (b > 255) b = 255f

        return Color(r.toInt(), g.toInt(), b.toInt())
    }

    private fun edge(v0: Vertex, v1: Vertex, v2: Vertex) = (v2.x - v0.x) * (v1.y - v0.y) - (v2.y - v0.y) * (v1.x - v0.x)
}