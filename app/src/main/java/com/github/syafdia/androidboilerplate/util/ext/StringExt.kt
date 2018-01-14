package com.github.syafdia.androidboilerplate.util.ext

/**
 * Replace string variable with supplied value
 * Example:
 *      val target = "Foo @{1} baz @{2}"
 *      target.replaceVar("bar", "qux") // foo bar baz qux
 */
fun String.replaceVar(vararg stringValues: String): String {
    var result = this

    stringValues.forEachIndexed { i, v ->  run{
        val targetVar = "@{${i + 1}}"
        result = result.replace(targetVar, v)
    }}

    return result
}