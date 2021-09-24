# Calculator on Kotlin

The purpose of this task is to practice writing Kotlin code.

### Input Parameter: 
String as mathematical expression - `2+2`, `2*5/3`, `1-3*(5/2)`
<br>Expression can contain integers instead of floating point numbers

### Output:
String with result - `2`, `3.3333`, `-6.5`
<br> Precision = 4 digits after decimal point

### Requirements:
- support + - `1+2`
- Support * / `3/5`   
- support ( ) `(2+2)*3`
- support unary operations + - `-1-2`, `-(+2*2)` (Optional)
- implement with Kotlin features:
    - Null safety
    - String templates
    - Operator Overloading 
    - Lambdas
    - Extensions 
    - Data classes
- test coverage >85%
- use testing framework [Kotest](https://kotest.io/) or [Spek](https://www.spekframework.org/)
