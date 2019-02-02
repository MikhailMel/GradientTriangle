package ru.scratty

import java.awt.Graphics

abstract class Figure {

    protected open fun drawPixel(graphics: Graphics, x: Int, y: Int) {
        graphics.drawLine(x, y, x, y)
    }

    abstract fun draw(graphics: Graphics)
}