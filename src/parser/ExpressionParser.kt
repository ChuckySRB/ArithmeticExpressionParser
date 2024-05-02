package parser

// Main class that parses the code. Returns it as an Expression() class
class ArithmeticParser(private val input: String) {
    private var index = 0

    // Main parse function. Run this at the start
    fun parse(): Expression {
        val expression = parseExpression()
        if (index < input.length) {
            throw SyntaxAnalysesException(index, input[index] + "", "")
        }
        return expression
    }

    // Parses the string expecting an Expression
    private fun parseExpression(): Expression {
        // Gets a first Lexical Element
        val element: LexicalElement = parseLexicalElement()
        return when (element.type){
            // Binary Expression
            LexicalType.LEFT_PARENTHESES -> {

                val left = parseExpression()
                val operation = parseOperation()
                val right = parseExpression()

                val curIndex = index
                val nextElement: LexicalElement = parseLexicalElement()
                // Check if the parentheses are closed
                if (nextElement.type != LexicalType.RIGHT_PARENTHESES) {
                    throw SyntaxAnalysesException(curIndex, nextElement.value as String, ")")
                }
                // Return Expression
                BinaryExpression(left, operation, right)
            }
            // Negative Constant Expression
            LexicalType.MINUS -> {
                // Get the Number Behind the Minus Sign
                val number: LexicalElement = parseLexicalElement()
                if (number.type != LexicalType.INTEGER) {
                    throw SyntaxAnalysesException(index, number.value as String, "Integer number")
                }
                // Return Expression
                NegativeConstantExpression(number.value as Number)
            }
            // Positive Constant Expression
            LexicalType.INTEGER -> PositiveConstantExpression(element.value as Number)

            // Element
            LexicalType.ELEMENT -> ElementPlaceholder()

            // Syntax error
            else -> throw SyntaxAnalysesException(index, element.value as String, "")
         }
    }

    // Reads one Language Element and Returns it
    private fun parseLexicalElement(): LexicalElement{
        skipWhitespace()
        if (index >= input.length)
            throw SyntaxAnalysesException(index, " ", "")
        return when {
            input[index] == '(' -> {
                index++
                LexicalElement(LexicalType.LEFT_PARENTHESES, "(")
            }
            input[index] == ')' -> {
                index++
                LexicalElement(LexicalType.RIGHT_PARENTHESES, ")")
            }
            input[index] == '+' -> {
                index++
                LexicalElement(LexicalType.PLUS, "+")
            }input[index] == '-' -> {
                index++
                LexicalElement(LexicalType.MINUS, "-")
            }input[index] == '*' -> {
                index++
                LexicalElement(LexicalType.MULTIPLY, "*")
            }
            input[index].isDigit() -> LexicalElement(LexicalType.INTEGER, value = parseNumber())
            input.startsWith("element", index) -> {
                index += "element".length
                LexicalElement(LexicalType.ELEMENT, "element")
            }
            else -> throw LexicalAnalysesException(index = index, element = input[index] + "", expected = LexicalType.entries.toString())
        }
    }

    // Parse a Number Integer
    private fun parseNumber(): Number {
        var value = 0
        while (index < input.length && input[index].isDigit()) {
            value = value * 10 + (input[index++] - '0')
        }
        return Number(value)
    }

    // Parse an Operator
    private fun parseOperation(): Operation {
        val operator = parseLexicalElement()
        return when (operator.type){
            LexicalType.PLUS -> AddOperation()
            LexicalType.MINUS -> SubOperation()
            LexicalType.MULTIPLY -> MultiplyOperation()
            else -> throw SyntaxAnalysesException(index = index, element = operator.value as String, expected = "Operator!")
        }
    }

    // Skip tabulators, newlines and whitespaces
    private fun skipWhitespace() {
        while (index < input.length && input[index].isWhitespace()) {
            index++
        }
    }
}

fun main() {
    val input = "(-123 +  \t (45 * \n (element - 6a)))"
    val parser = ArithmeticParser(input)
    val expression = parser.parse()
    println(expression)
}
