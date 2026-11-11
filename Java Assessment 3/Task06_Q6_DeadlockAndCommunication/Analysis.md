# Q6 – Deadlock and inter-thread communication

## Deadlock

The four Coffman conditions are mutual exclusion, hold-and-wait, no pre-emption and circular wait. A classic unsafe transfer can deadlock when thread 1 locks A then waits for B while thread 2 locks B then waits for A. To reproduce it, create a separate demonstration with two threads, reversed lock acquisition, and a barrier/latch between the first and second lock. Use daemon threads or a short test process timeout so the experiment cannot hang your VS Code session.

The implemented transfer prevents circular wait by acquiring wallet locks in a globally consistent name order. Both transfers request locks in the same order, so a cycle cannot form.

## Producer-consumer

The counter is a circular buffer of capacity five. `put` waits while full; `take` waits while empty. Both use `while`, not `if`, because a thread can wake spuriously or another thread can consume/change the condition before it reacquires the monitor. `notifyAll()` wakes all waiters so the correct condition can be rechecked. `wait()` releases the monitor while waiting; `sleep()` does not release a monitor held by the sleeping thread.

Expected result: `Sum of tokens collected: 210 (expected 210)` and final counter size 0. The buffer count always stays between 0 and 5 when methods are correct.

Replacing wait/notify with `ArrayBlockingQueue<Integer>(5)` is less error-prone for a standard bounded producer-consumer queue. `put`/`take` block safely. A timed `tryLock` can prevent indefinite waiting in transfer code but requires retry/rollback policy. Queue put/take are O(1) typical operations; storage is O(capacity). A transfer touches two accounts and is O(1) work plus lock contention.
