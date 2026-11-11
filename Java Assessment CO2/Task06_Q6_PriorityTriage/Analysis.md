# Q6 – Priority queue, arrival order and fairness

A plain FIFO queue cannot serve a later critical patient before an earlier minor patient. The priority queue orders by severity ascending (1 is most severe) and then by an increasing arrival counter. The counter makes ties stable: among equal severity values, the smaller arrival value is served first.

Expected supplied-program output:
`Meena(S1) Kumar(S1) Lata(S2) Ravi(S3) John(S3) Asha(S5)`

Printing a PriorityQueue directly does not guarantee sorted treatment order; only its head is guaranteed to be the least element. Repeated `poll()` returns treatment order.

Complexity: PriorityQueue `offer` and `poll` are O(log n); `peek` is O(1); storage is O(n). A sorted LinkedList has O(n) insertion and O(1) removal from head. Five FIFO queues (one per severity) can admit and serve in O(1) per operation if severity is bounded 1–5, plus O(5) to find the next non-empty queue.

Starvation: continuous arrivals at severity 1–4 may indefinitely delay severity 5. A fair policy could promote a waiting patient one level after a defined wait threshold, or use an age-adjusted score. Such a policy must be clinically approved; do not silently override clinical severity. A city hospital with 5,000 daily patients is not inherently too large for O(log n), but deployment also needs concurrency, persistence, clinical governance, monitoring and integration tests.
