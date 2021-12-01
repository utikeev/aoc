import java.io.File
import java.net.URI

internal object Resources {
    fun resourceAsText(fileName: String): String =
        File(fileName.toURI()).readText()

    fun resourceAsList(fileName: String): List<String> =
        File(fileName.toURI()).readLines()

    fun resourceAsListOfInt(fileName: String): List<Int> =
        resourceAsList(fileName).map { it.toInt() }

    fun resourceAsListOfLong(fileName: String): List<Long> =
        resourceAsList(fileName).map { it.toLong() }

    private fun String.toURI(): URI =
        Resources.javaClass.classLoader.getResource(this)?.toURI() ?: throw IllegalArgumentException("Cannot find Resource: $this")
}

fun <T> List<T>.rotateLeft(n: Int) = drop(n) + take(n)
fun <T> List<T>.rotateRight(n: Int) = takeLast(n) + dropLast(n)
