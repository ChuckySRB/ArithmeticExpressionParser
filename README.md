
---

# Arithmetic Expression Parser

This Kotlin project implements a simple arithmetic expression parser based on the provided grammar. It allows parsing arithmetic expressions composed of integers, arithmetic operations (`+`, `-`, `*`), parentheses, and an element placeholder.

## Parser Logic
Since the Grammar is very simple the code was structured in a much simpler way. The parser does:
1) Lexical Analyses - the lexical analyses is the first step of the parser, each time a parser is moving it reads an lexical element of the code 
and checks it is one of the defined ones If not the code trows lexical exception.
2) Syntax Analyses - once the lexical element is identified parser is checking the grammar and parses the code element by element
 following the grammar rules. If the code structure is breaking the given grammar the code trows syntax exception.
3) Returns Expression - once the hole code has been parsed, the parser returns the code as well structured Expression class

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

## Code Structure
The project follows a structured approach with the following main components:

- src.parser package

  - **`LexicAndSyntax.kt`**
    - Here are defined Lexical Element Types ('+', '-', 'Int', '(', ...) and 
    Syntax Nodes (Expression, Operation, Number)
  - **`ExpressionParser.kt`**
    - Here is the main parser class that parser Lexical Elements, Expressions, Numbers, Operators and Skip Whitespace  
  - **`Expcetions.kt`**
    - Here are defined Exception types: LexicalException and SyntaxException

- test.parser
  - Unit tests for the parser

## Examples

- `123`: Represents a positive constant expression.
- `(-123)`: Represents a negative constant expression.
- `(-123 + 45)`: Represents a binary expression with addition.
- `(-123 + (45 * 6))`: Represents a nested binary expression with addition and multiplication.
- `(-123 + (element * 6))`: Represents a binary expression with an element placeholder.

## Contributing

Contributions are welcome! If you have any suggestions, improvements, or bug fixes, feel free to open an issue or create a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to adjust and expand this README according to your project's specific needs and structure.


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