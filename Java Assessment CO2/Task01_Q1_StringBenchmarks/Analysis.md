# Q1 – String, StringBuilder or StringBuffer?

## Prediction

`String` is immutable. In a loop, `result = result + field` creates a new combined string each time and copies the previous contents. For n similarly sized fields, cumulative copying can approach O(n²) character work. `StringBuilder` and `StringBuffer` mutate an internal character buffer and normally need O(n) total append work, ignoring occasional buffer growth/copying.

- `String`: simple and safe for immutable values, but poor for repeated loop concatenation.
- `StringBuilder`: preferred for a local ticket being built by one thread; not synchronized.
- `StringBuffer`: synchronized methods; useful when a single mutable buffer is genuinely shared, but synchronization does not automatically make a multi-call workflow atomic.

## Workloads

The Java program uses 6 fields × 1 ticket, 20 fields × 1,000 tickets, and 50 fields × 10,000 tickets. It repeats each workload five times and prints elapsed nanoseconds for the complete workload. Run it several times and use the median of program outputs if possible.

## Design decision

Use `StringBuilder` for each ticket because each ticket is normally built by one request/thread. For a shared booking log, prefer a thread-safe logging facility or synchronize the complete append operation; do not share one mutable builder among threads. `StringBuffer` provides synchronized individual methods but a logging framework is usually a better production design.

## Alternative

`String.join("|", fields)` or `StringJoiner` can build delimited text cleanly. They are readable when fields already exist as strings. `String.format` is convenient for formatted output but may add formatting overhead. Compare them empirically if extending the experiment.

## Complexity

Let n be the number of fields and L the average field length. Repeated concatenation can copy approximately L(1+2+...+n) = O(Ln²) characters. Builder append is amortized O(Ln) total character work, with occasional buffer expansion. Both produce a final output string requiring O(Ln) space; repeated concatenation creates extra temporary strings and allocations.
