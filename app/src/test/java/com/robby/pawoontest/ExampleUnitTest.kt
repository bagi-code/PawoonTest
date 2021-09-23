package com.robby.pawoontest

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Menjawab pertanyaan Basic pemrograman
 * 1. Create a function to rotate the array A to the right by K steps.
 * 2. Create a function to rotate the matrix (2D array) M clockwise by 90 degrees.
 */
class ExampleUnitTest {

    @Test
    fun runningTest() {
        // soal 1
        assertEquals(mutableListOf<Int>(4, 5, 1, 2, 3), fristQuestion(2, arrayOf(1, 2, 3, 4, 5)))
        assertEquals(mutableListOf<Int>(20, 11, 12, 13, 14, 15, 16, 17, 18, 19), fristQuestion(1001, arrayOf(11, 12, 13, 14, 15, 16, 17, 18, 19, 20)))

        // soal 2
        assertEquals(arrayOf( intArrayOf(3, 1), intArrayOf(4, 2)), secondQuestion(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)), 2))
        assertEquals(arrayOf( intArrayOf(23,19,15,11), intArrayOf(24,20,16,12), intArrayOf(25,21,17,13), intArrayOf(26,22,18,14)),
            secondQuestion(arrayOf(intArrayOf(11,12,13,14), intArrayOf(15,16,17,18), intArrayOf(19,20,21, 22), intArrayOf(23,24,25,26)),
                4))
    }

    fun fristQuestion(repeat : Int, inputArray: Array<Int>) : MutableList<Int> {
        var temp = inputArray.toMutableList()
        var size = inputArray.size
        var inputTemp = repeat

        if (repeat  > size) { inputTemp = repeat%size }

        for (i in 1..inputTemp) {
            temp.add(0, temp[size-1])
            temp.removeAt(size)
        }
        return temp
    }

    private fun secondQuestion(inputArray: Array<IntArray>, N: Int): Array<IntArray> {
        for (i in 0 until N / 2) {
            for (j in i until N - i - 1) {
                val temp = inputArray[i][j]
                inputArray[i][j] = inputArray[N - 1 - j][i]
                inputArray[N - 1 - j][i] = inputArray[N - 1 - i][N - 1 - j]
                inputArray[N - 1 - i][N - 1 - j] = inputArray[j][N - 1 - i]
                inputArray[j][N - 1 - i] = temp
            }
        }
        return inputArray
    }
}