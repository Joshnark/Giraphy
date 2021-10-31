package com.joshnark.presentation_layer.utils

import kotlin.random.Random

object RandomGenerator {

    private val random = Random(1482394)

    fun getRandomInt(until: Int) = random.nextInt(until)

}