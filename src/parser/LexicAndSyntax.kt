package parser

// Lexical Elements Definition

enum class LexicalType{
    // Data Types
    INTEGER,

    // Parentheses
    LEFT_PARENTHESES,
    RIGHT_PARENTHESES,

    // Arithmetic Operations
    MINUS,
    PLUS,
    MULTIPLY,

    // Element Placeholder
    ELEMENT
}

data class LexicalElement(val type: LexicalType, val value: Any)

// Syntax Class Nodes

// Operations

sealed class Operation

data class AddOperation(val operator: String = "+"): Operation()
data class SubOperation(val operator: String = "-"): Operation()
data class MultiplyOperation(val operator: String = "*"): Operation()

// Integer number

data class Number(val value: Int)

// Expressions

sealed class Expression

sealed class ConstantExpression: Expression()
data class PositiveConstantExpression(val number: Number) : ConstantExpression()
data class NegativeConstantExpression(val number: Number) : ConstantExpression()

data class BinaryExpression(val left: Expression, val operation: Operation, val right: Expression) : Expression()

data class ElementPlaceholder(val value: String = "element"): Expression() // Placeholder for future elements? It was in the task description

