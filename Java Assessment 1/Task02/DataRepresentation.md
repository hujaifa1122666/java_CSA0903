# Task 2 – Data Representation

## Chosen Types
| Field | Suggested Java type | Reason |
|---|---|---|
| Floor index | `int` | Discrete index |
| Slot index | `int` | Discrete index |
| Registration number | `String` | Alphanumeric identifier |
| Entry/exit time | `LocalDateTime` | Date and time representation |
| Duration | `long` or `int` minutes | Whole-minute duration |
| Fee | `double` for assignment demo; `BigDecimal` for currency-grade precision | Avoid/handle rounding appropriately |

## Floor and Slot Representation
Use a jagged array or a list of floor objects when floors have different capacities.

## Casting and Integer Division
For started-hour billing, avoid `minutes / 60` alone because integer division truncates. Use:
`(minutes + 59) / 60` for non-negative integer minutes.
Explain validation and precision.

## Input
Describe using `Scanner` for interactive console input or command-line arguments for scripted runs.

## Alternative Representations
Compare jagged arrays, a flattened one-dimensional array, and `ArrayList<String[]>`, including access and memory implications.
