package com.simon.pattern.base

import java.util.concurrent.atomic.AtomicBoolean

class SingleEvent<out T>(private val value: T) {

    var handled: AtomicBoolean = AtomicBoolean(false)
        private set

    fun getValueForEvent(): T? = if (handled.get()) {
        null
    } else {
        handled.set(true)
        value
    }

    fun peek(): T = value
}
