package com.webtests.utils

import org.apache.commons.lang3.RandomStringUtils
import org.kohsuke.randname.RandomNameGenerator
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

private val gen = RandomNameGenerator()
private val random = ThreadLocalRandom.current()
private const val firstCyrillicLetterCode = 0x410
private const val cyrillicRange = 64

private const val firstHieroglypLetterCode = 0x26000
private const val hieroglypRange = 6999

fun generateName(): String {
    return gen.next()
}

fun generateUserLogin(): String {
    return "user_${gen.next()}"
}

fun generateLongUserName(length: Int): String {
    return RandomStringUtils.randomAlphabetic(length)
}

fun generateUserFullName(): String {
    return "${gen.next()} ${gen.next()}"
}

fun generateEmail(): String {
    return "${gen.next()}${Random.nextInt()}@${gen.next().replace("_", "")}.cooom"
}

fun generateJabber(): String {
    return "${gen.next()}${Random.nextInt()}@jabubber.cooom"
}

fun getHieroglyphString(): String {
    return (0..10).joinToString("") { random.nextInt(firstHieroglypLetterCode, firstHieroglypLetterCode + hieroglypRange).toChar().toString() }
}

fun getCyrillicString(): String {
    return (0..10).joinToString("") { random.nextInt(firstCyrillicLetterCode, firstCyrillicLetterCode + cyrillicRange).toChar().toString() }
}
