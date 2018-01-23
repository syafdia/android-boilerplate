package com.github.syafdia.androidboilerplate.util

import org.junit.Test
import org.junit.Assert.*


class ObjUtilTest {

    companion object {
        data class Person(val firstName: String, val lastName: String, val middleName: String? = null)
    }

    @Test
    fun objToMap_MapCreatedSuccessfully() {
        val person = Person("Grigori", "Rasputin", "Yefimovich")
        val expectation = mapOf<String, Any>(
                "firstName" to "Grigori",
                "lastName" to "Rasputin",
                "middleName" to "Yefimovich"
        )

        assertEquals(expectation, ObjUtil.objToMap(person))
    }

    @Test
    fun objToMap_MapCreatedSuccessfullyWithOutNull() {
        val person = Person("Grigori", "Rasputin")
        val expectation = mapOf(
                "firstName" to "Grigori",
                "lastName" to "Rasputin"
        )

        assertEquals(expectation, ObjUtil.objToMap(person))
    }
}