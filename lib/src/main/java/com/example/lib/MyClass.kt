package com.example.lib

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun a() {
    println(1)
    delay(100)
    println(2)
    delay(100)
    println(3)
    delay(100)
    println(4)
    delay(100)
    println(5)
    delay(100)
    println(6)
    delay(100)
    println(7)
    delay(100)
}

fun main() = runBlocking {
    a()
}