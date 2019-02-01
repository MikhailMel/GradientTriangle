package ru.scratty

data class Vertex(var x: Int = 0, var y: Int = 0, var z: Int = 0) {

    operator fun plus(v: Vertex): Vertex {
        return Vertex(x + v.x, y + v.y, z + v.z)
    }

    operator fun minus(v: Vertex): Vertex {
        return Vertex(x - v.x, y - v.y, z - v.z)
    }

    operator fun times(num: Float): Vertex {
        return Vertex((x * num).toInt(), (y * num).toInt(), (z * num).toInt())
    }
}

fun swap(v0: Vertex, v1: Vertex) {
    v0.x = v1.x.apply { v1.x = v0.x }
    v0.y = v1.y.apply { v1.y = v0.y }
    v0.z = v1.z.apply { v1.z = v0.z }
}