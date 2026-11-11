# Q2 – User-defined exceptions and propagation

## Exception design

```text
Exception
└── ExamException (checked)
    ├── ExamWindowClosedException
    ├── StudentNotRegisteredException
    └── SubmissionStorageException (wraps IOException)
```

The domain failures are checked because the controller must deliberately handle rejected submissions. An unchecked exception may be appropriate for programmer misuse or violated internal invariants, not ordinary business outcomes that callers are expected to handle.

## Propagation

Controller → ExamService → Storage. Storage translates low-level `IOException` into `SubmissionStorageException` with the original exception as its cause. The controller is the boundary that presents a safe message. The UI should not expose internal file-system details to an end user in a real deployment; log the cause securely.

Expected first lines:
```text
Saved answer01.pdf
Submission accepted.
Error: Could not store answer02.pdf
Cause: Disk quota exceeded
```

The program also demonstrates exam-closed and not-registered scenarios. The complete chained trace includes both the domain exception and the original IOException.

If the cause is not passed to the constructor, `getCause()` returns null; the original `Disk quota exceeded` message and its stack trace are lost. Chaining retains a reference to the cause and its stack trace, increasing memory held while the exception remains reachable.

If these custom exceptions extended RuntimeException, `throws` declarations would not be required and callers would not be forced by the compiler to catch or declare them. Explicit handling would still be good design.

Exception creation and stack-trace capture are more expensive than returning a status code; normal successful submission is O(1) in this simplified example, while exception cost depends on stack depth. Space for a chained exception is O(depth of captured stack traces) plus the cause chain.
