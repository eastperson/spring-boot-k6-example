package com.eastperson.k6example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class K6ExampleApplication

fun main(args: Array<String>) {
    runApplication<K6ExampleApplication>(*args)
}
