package ru.scratty

import java.awt.Graphics

abstract class Figure {

    protected fun drawPixel(graphics: Graphics, point: Vertex) {
        graphics.drawLine(point.x, point.y, point.x, point.y)
    }

    abstract fun draw(graphics: Graphics)
}