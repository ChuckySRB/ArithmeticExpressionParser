
---

# Arithmetic Expression Parser

This Kotlin project implements a simple arithmetic expression parser based on the provided grammar. It allows parsing arithmetic expressions composed of integers, arithmetic operations (`+`, `-`, `*`), parentheses, and an element placeholder.

## Features

- Parses arithmetic expressions according to the provided grammar.
- Supports integers, addition, subtraction, multiplication, and parentheses.
- Handles negative constant expressions and element placeholders.

## Grammar

The parser is based on the following grammar:

```
<digit>            ::= "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
<number>           ::= <digit> | <digit> <number>
<operation>        ::= "+" | "-" | "*"
<constant-expression> ::= "-" <number> | <number>
<binary-expression>::= "(" <expression> <operation> <expression> ")"
<expression>       ::= "element" | <constant-expression> | <binary-expression>
```

## Usage

To use the parser, simply instantiate an `ArithmeticParser` object with the arithmetic expression as input, then call the `parse()` method:

```kotlin
val input = "(-123 + (45 * 6))"
val parser = ArithmeticParser(input)
val expression = parser.parse()
println(expression)
```

## Examples

- `123`: Represents a positive constant expression.
- `(-123)`: Represents a negative constant expression.
- `(-123 + 45)`: Represents a binary expression with addition.
- `(-123 + (45 * 6))`: Represents a nested binary expression with addition and multiplication.
- `(-123 + (element * 6))`: Represents a binary expression with an element placeholder.

## Tests

The project includes unit tests to verify the functionality of the parser. These tests cover various scenarios including parsing valid expressions, handling of invalid expressions, and lexical character errors.

## Exceptions

The project defines custom exception classes for both lexical and syntax analysis errors, providing detailed information about unexpected characters and expected symbols during parsing.


---