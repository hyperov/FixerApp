package com.fixer.app

/**
 * return true if annagram, false otherwise
 */
fun checkForAnnagrams(first: String, second: String): Boolean {
    if (first.length != second.length) return false
    val chars = HashMap<Char, Int>()
    first.toCharArray().forEach { c ->
        chars.set(c, chars.getOrPut(c, { 0 }) + 1)
    }
    second.toCharArray().forEach { c ->
        if (chars.get(c) == null || chars.get(c)!! == 0) return false
        else {
            chars.set(c, chars.get(c)!! - 1)
        }
    }

    return true
}


fun findNextFibonacciNumberRecursive(nthNumber: Int): Int {
    return if (nthNumber == 1 || nthNumber == 2) 1
    else findNextFibonacciNumberRecursive(nthNumber - 1) +
            findNextFibonacciNumberRecursive(nthNumber - 2)
}

fun findNextFibonacciNumberIterative(nthNumber: Int): Int {
    if (nthNumber == 1 || nthNumber == 2) return 1
    var numList = arrayListOf(1, 1)

    for (i in 3..nthNumber) {
        val newNum = numList[numList.size - 1] + numList[numList.size - 2]
        numList.add(newNum)
    }
    return numList[numList.size - 1]
}