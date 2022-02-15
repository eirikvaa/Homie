package com.homies.homie.domain.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails


class CoordinateTest {

    @Test
    fun `it is correctly initialised with a list of size 2`() {
        val unit = Coordinate(listOf(1f, 2f))
        assertEquals(1f, unit.latitude)
        assertEquals(2f, unit.longitude)
    }

    @Test
    fun `it throws if initialised with a list that is not of size 2`() {
        assertFails {
            Coordinate(listOf(1f, 2f, 3f))
        }
    }
}