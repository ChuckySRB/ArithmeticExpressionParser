import org.junit.Assert.assertEquals
import org.junit.Test
import parser.*

class ArithmeticParserTest {

    @Test
    fun testPositiveConstantExpressionParsing() {
        val input = "123"
        val parser = ArithmeticParser(input)
        val expression = parser.parse()
        assertEquals(PositiveConstantExpression(Number(123)), expression)
    }

    @Test
    fun testNegativeConstantExpressionParsing() {
        val input = "-123"
        val parser = ArithmeticParser(input)
        val expression = parser.parse()
        assertEquals(NegativeConstantExpression(Number(123)), expression)
    }

    @Test
    fun testBinaryExpressionParsing() {
        val input = "(-123 + 45)"
        val parser = ArithmeticParser(input)
        val expression = parser.parse()
        assertEquals(BinaryExpression(NegativeConstantExpression(Number(123)), AddOperation(), PositiveConstantExpression(Number(45))), expression)
    }

    @Test
    fun testNestedBinaryExpressionParsing() {
        val input = "(-123 + (45 * 6))"
        val parser = ArithmeticParser(input)
        val expression = parser.parse()
        assertEquals(BinaryExpression(NegativeConstantExpression(Number(123)), AddOperation(), BinaryExpression(PositiveConstantExpression(Number(45)), MultiplyOperation(), PositiveConstantExpression(Number(6)))), expression)
    }

    @Test
    fun testElementPlaceholderParsing() {
        val input = "(-123 - (element * 6))"
        val parser = ArithmeticParser(input)
        val expression = parser.parse()
        assertEquals(BinaryExpression(NegativeConstantExpression(Number(123)), SubOperation(), BinaryExpression(ElementPlaceholder(), MultiplyOperation(), PositiveConstantExpression(Number(6)))), expression)
    }

    @Test
    fun testInvalidExpression() {
        val input = "(-123 + 45 * 6)"
        val parser = ArithmeticParser(input)
        try {
            parser.parse()
        } catch (e: SyntaxAnalysesException) {
            assertEquals("Unexpected element '*' at index 10! Expected: )", e.message)
        }
    }

    @Test
    fun testInvalidLexicalCharacter() {
        val input = "(-123 + (45 * a6))"
        val parser = ArithmeticParser(input)
        try {
            parser.parse()
        } catch (e: LexicalAnalysesException) {
            assertEquals("Unexpected element 'a' at index 14! Available symbol types: [INTEGER, LEFT_PARENTHESES, RIGHT_PARENTHESES, MINUS, PLUS, MULTIPLY, ELEMENT]", e.message)
        }
    }
}
