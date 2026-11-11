# Q5 – Race conditions and synchronization

The critical section is the combined check (`booked[seat]`), state update (`booked[seat] = true`) and counter increment. Without synchronization, two threads can both observe `false` before either writes `true`, then both increment the count. The benchmark deliberately yields between check and write to make the race easier to observe; it is still scheduling-dependent and may not oversell on every run.

The completed program synchronizes the entire `book` method and should print `Seats sold: 10`. A synchronized block around the same critical section uses the same intrinsic monitor (`this`) but makes the lock scope explicit. Synchronizing on a different object would not protect shared state consistently.

For 20 screens, use one independent lock per screen/seat inventory, or a carefully designed per-seat lock strategy. Avoid one global static lock unless all screens truly share one global invariant; it unnecessarily serializes unrelated screens. Static synchronization locks the Class object, while instance synchronization locks one object.

For s seats, the boolean array uses O(s) space. Locking adds constant monitor state per lock in the conceptual model; per-seat locks would add O(s) lock objects. Booking lookup/update is O(1) per attempt, excluding contention. AtomicBooleanArray/compareAndSet, ReentrantLock, or ConcurrentHashMap can implement alternative atomic claims; all still require careful coordination with the booking counter.
