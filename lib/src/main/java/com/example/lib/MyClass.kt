package com.example.lib

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking { // this: CoroutineScope
    launch {
        println("1")
        delay(200L)
        println("Task from runBlocking")
    }

    coroutineScope { // 创建一个协程作用域
        launch {
            println("2")
            delay(500L)
            println("Task from nested launch")
        }
        println("3")
        delay(100L)
        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
        println("4")
    }
    println("5")
    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
}