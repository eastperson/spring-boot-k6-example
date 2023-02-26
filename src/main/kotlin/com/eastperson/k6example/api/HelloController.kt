package com.eastperson.k6example.api

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController(
    private val meterRegistry: MeterRegistry
) {
    private val getCounter: Counter = meterRegistry.counter("my.get.counter")
    private val postCounter: Counter = meterRegistry.counter("my.post.counter")
    private val putCounter: Counter = meterRegistry.counter("my.put.counter")
    private val deleteCounter: Counter = meterRegistry.counter("my.delete.counter")

    @GetMapping
    fun get(name: String?): String {
        getCounter.increment()
        return "get, ${name}!!"
    }

    @PostMapping
    fun post(): String {
        postCounter.increment()
        return "post!!"
    }

    @PutMapping("/{id}")
    fun put(@PathVariable id: String): String {
        putCounter.increment()
        return "put, ${id}!!"
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): String {
        deleteCounter.increment()
        return "delete, ${id}!!"
    }
}