# Q7 – Diagnose, redesign and validate

## Selected limitation

This design challenge selects Q1's repeated `String` concatenation in a loop. The original approach creates a new immutable String for each append step and recopies the accumulated prefix. As the number and length of fields grow, repeated copying and temporary allocations can dominate ticket construction.

## Objective

For the same fields and number of tickets, reduce elapsed construction time and intermediate temporary string allocation while preserving the exact ticket contents.

## Revised design

Replace the repeated `String +` loop with a local `StringBuilder` per ticket. Each request/thread owns its builder; no shared mutable builder is required.

Pseudocode:

```text
buildTicket(fields):
    builder = new StringBuilder
    for each field:
        builder.append(field)
        builder.append('|')
    return builder.toString()
```

## Prediction

For n fields, repeated concatenation can copy O(n²) total characters for similarly sized fields; StringBuilder append is amortized O(n). The redesigned approach should show a larger advantage as field count and ticket count increase. Both need O(output length) space for the final ticket; StringBuilder additionally has a growable internal buffer, while the original creates intermediate String objects.

## Validation

`TicketRedesignBenchmark.java` uses 50 fields and ticket counts 100, 1,000 and 10,000. It prints the elapsed time for original and redesigned approaches. Run it repeatedly on the same machine, record median results in `REPORT/ExperimentResults.md`, and compare like-for-like runs.

## Defence and trade-offs

StringBuilder is appropriate for local construction. If tickets are very short, the difference may be negligible and the simpler expression may be acceptable. Builder capacity growth and the final `toString()` copy still cost memory/time. The benchmark is a microbenchmark and is affected by JVM warm-up, garbage collection, and system load. A production-quality benchmark should use JMH.
