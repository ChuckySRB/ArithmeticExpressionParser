package parser

open class ArithmeticParserException(message: String) : RuntimeException(message)

class LexicalAnalysesException(index: Int, character: Char, expected: String) :
    ArithmeticParserException("Unexpected character '$character' at index $index! Available symbol types: $expected")

class SyntaxAnalysesException(index: Int, character: Char, expected: String) :
    ArithmeticParserException("Unexpected character '$character' at index $index! Expected: $expected")



