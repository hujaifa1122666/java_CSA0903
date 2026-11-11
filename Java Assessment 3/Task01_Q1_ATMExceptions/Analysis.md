# Q1 – ATM exception design and testing

## Relevant Throwable hierarchy

```text
Object
└── Throwable
    ├── Error                         (serious JVM/system problem; normally not caught)
    └── Exception
        ├── IOException               (checked; card-reader file access)
        ├── RuntimeException          (unchecked)
        │   ├── InputMismatchException (invalid Scanner token)
        │   └── IllegalArgumentException (empty account / invalid argument)
        ├── InvalidWithdrawalException (checked; application rule)
        └── InsufficientFundsException (checked; application rule)
```

Catch order: specific checked exceptions first, then narrower runtime exceptions. A catch for `Exception` before these blocks makes the later blocks unreachable and causes a compile-time error. `finally` runs when control leaves the try/catch normally or exceptionally, except for abnormal JVM termination (for example, `Runtime.halt`).

## Test paths

Create a zero-byte file named `card-reader.txt` in the task folder to pass the card-reader check. Run the program repeatedly with:
1. Valid amount, e.g. 500.
2. Letters for amount, e.g. `abc`.
3. Negative amount, e.g. -100.
4. Amount above Rs 5000.
5. Rename/remove `card-reader.txt` to trigger IOException.

The program's finally message should appear for handled paths. Capture one intentionally uncaught stack trace separately by temporarily removing a catch block, then restore the program.

## Single catch discussion

A single `catch (Exception e)` can be acceptable at a top-level application boundary for logging and presenting a safe generic message. It loses precise recovery behaviour and can hide programming errors if used throughout the application. Never catch `Error` as routine transaction handling.

## Alternative without exceptions

Validate tokens with `Scanner.hasNextDouble()` / `hasNextInt()` and validate ranges before calling withdrawal logic; return a status enum or error code. Normal user mistakes are expected input paths, so explicit validation may be clearer. Exceptions have stack-trace construction cost and are expensive when thrown frequently; returning a code avoids stack trace creation. Both approaches use O(1) extra state per transaction; checking/withdrawal work is O(1).
