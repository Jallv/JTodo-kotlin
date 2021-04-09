package com.example.lib

data class Person(val name: String, val age: Int)

fun test(f: Person.() -> Unit = {}) {
    Person("Jim", 1).apply(f)
}

fun main() {
    test {
        println("name=$name,age=$age")
    }
}