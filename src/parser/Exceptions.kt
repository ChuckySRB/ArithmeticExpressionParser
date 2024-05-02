package parser

open class ArithmeticParserException(message: String) : RuntimeException(message)

class LexicalAnalysesException(index: Int, element: String, expected: String) :
    ArithmeticParserException("Unexpected element '$element' at index $index! Available symbol types: $expected")

class SyntaxAnalysesException(index: Int, element: String, expected: String) :
    ArithmeticParserException("Unexpected element '$element' at index $index! Expected: $expected")



