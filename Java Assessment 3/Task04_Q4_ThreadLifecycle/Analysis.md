# Q4 – Thread creation and lifecycle

Two approaches are demonstrated:
- Extend `Thread` when defining a specialised thread type. This uses Java's single class inheritance.
- Implement `Runnable` when separating the task from the thread that executes it. This is more flexible and works with executors.

Calling `run()` directly is an ordinary method call on the current thread; it does not ask the JVM to create a new thread. `start()` schedules a new thread and invokes its `run()` method. `join()` makes the calling thread wait until the target thread terminates, so the final summary is printed after all stations complete.

Lifecycle states include NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING and TERMINATED. A station typically moves NEW → RUNNABLE → TIMED_WAITING (during sleep) → RUNNABLE → TERMINATED. Exact scheduling and printed completion order can vary.

Run at least ten times and record output order. Thread priorities are scheduling hints, not guarantees. A daemon thread does not keep the JVM alive; it may be stopped when all non-daemon threads finish.

Alternative: an ExecutorService fixed pool reuses p worker threads rather than creating one thread per station/task. For n equal-sized tasks, idealised processing time is approximately n/p task batches, but real speedup is limited by scheduling, I/O, contention and CPU cores. Thread stacks consume memory per active thread; a bounded pool limits that resource.
