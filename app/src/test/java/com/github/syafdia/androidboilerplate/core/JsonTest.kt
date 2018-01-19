package com.github.syafdia.androidboilerplate.core

import com.github.salomonbrys.kotson.get
import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.FieldNamingPolicy
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class JsonTest {

    companion object {
        data class Person(val firstName: String, val lastName: String)
    }

    @Test
    fun jsonParseAs_ObjectCreatedSuccessfully() {
        val personJsonSc = "{\"first_name\":\"Badu\",\"last_name\":\"Budi\"}"
        val person1 = Person("Badu", "Budi")
        val person2 = Json.parseAs(Person::class.java, personJsonSc, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

        assertEquals(person1.firstName, person2!!.firstName)
        assertEquals(person1.lastName, person2!!.lastName)
    }

    @Test
    fun jsonParseAs_ObjectNotCreatedBecauseJsonIsNotSnakeCase() {
        val personJsonCc = "{\"firstName\":\"Badu\",\"lastName\":\"Budi\"}"
        val person1 = Json.parseAs(Person::class.java, personJsonCc, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

        assertEquals(null, person1!!.firstName)
        assertEquals(null, person1!!.lastName)
    }

    @Test
    fun jsonParseAs_ObjectIsNullBecauseJsonIsNotValid() {
        val person1 = Json.parseAs(Person::class.java, "Not Json")

        assertEquals(null, person1)
    }

    @Test
    fun parseAsJsonObject_ObjectCreatedSuccessfully() {
        val fooStr = """
            {
                "foo": {
                    "bar": {
                        "baz": 1,
                        "tar": "qux",
                        "lols": [1, 2, 3, 4]
                    }
                }
            }
            """

        val objFoo = Json.parseAsJsonObject(fooStr)

        assertEquals(jsonArray(1, 2, 3, 4), objFoo?.get("foo")?.get("bar")?.get("lols")?.asJsonArray)
        assertEquals(1, objFoo?.get("foo")?.get("bar")?.get("baz")?.asInt)
        assertEquals("qux", objFoo?.get("foo")?.get("bar")?.get("tar")?.asString)
        assertEquals(null, objFoo?.get("qux"))
    }

    @Test
    fun parseAsListOf_ListOfObjectCreatedSuccessfully() {
        val personsStr = """
            [
                {"first_name":"Budi","last_name":"B"},
                {"first_name":"Doni","last_name":"D"},
                {"first_name":"Poni","last_name":"P"}
            ]
            """
        val persons = Json.parseAsListOf(Person::class.java, personsStr, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        val expectations = listOf(
                Person("Budi", "B"),
                Person("Doni", "D"),
                Person("Poni", "P")
        )

        assertArrayEquals(expectations.toTypedArray(), persons.toTypedArray())
    }

    fun parseAsJsonArray_JsonArrayOfObjectCreatedSuccessfully() {
        val personsStr = """
            [
                {"first_name":"Budi","last_name":"B"},
                {"first_name":"Doni","last_name":"D"},
                {"first_name":"Poni","last_name":"P"}
            ]
            """
        val persons = Json.parseAsJsonArray(personsStr)
        val expectations = jsonArray(
                jsonObject(Pair("first_name", "Budi"), Pair("last_name", "B")),
                jsonObject(Pair("first_name", "Doni"), Pair("last_name", "D")),
                jsonObject(Pair("first_name", "Poni"), Pair("last_name", "P"))
        )

        assertArrayEquals(expectations.toList().toTypedArray(), persons.toList().toTypedArray())
    }
}