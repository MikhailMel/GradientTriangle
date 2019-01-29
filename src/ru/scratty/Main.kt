package ru.scratty

import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.JPanel

class Main {
    companion object {

        private const val WINDOW_WIDTH = 400
        private const val WINDOW_HEIGHT = 400

        @JvmStatic
        fun main(args: Array<String>) {
            val jFrame = JFrame().apply {
                defaultCloseOperation = EXIT_ON_CLOSE
                setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
            }

            val triangle = Triangle(Vertex(50, 50), Vertex(100, 72), Vertex(67, 150))

            val renderPanel = object: JPanel() {
                override fun paintComponent(g: Graphics?) {
                    triangle.draw(g!!)
                }
            }

            jFrame.add(renderPanel)
            jFrame.isVisible = true
        }
    }
}