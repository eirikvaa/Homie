package com.homies.homie

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}