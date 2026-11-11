# CSA09 – Programming in Java – Assignment 3 (CO3)

**Student:** K. Abu Hujaifa  
**Register number:** 192472308  
**Course:** Programming in Java  
**Mode:** Pair work – two students  
**Suggested JDK:** Java 17 or later (record your actual `java -version` output)

## Repository and folder structure

Create your own repository named `CSA09-192472308-Assignment3`. This assignment requires each partner to execute every question in their own repository. Keep the joint report clear about Student A and Student B responsibilities.

- `Task01_Q1_ATMExceptions` – ATM exception handling and test paths
- `Task02_Q2_ExamExceptions` – custom exceptions, propagation and chaining
- `Task03_Q3_ResourceSafety` – manual close vs try-with-resources
- `Task04_Q4_ThreadLifecycle` – Thread, Runnable and thread lifecycle
- `Task05_Q5_SeatBooking` – race condition and synchronization
- `Task06_Q6_DeadlockAndCommunication` – lock ordering and producer-consumer
- `Task07_Q7_ConcurrentProcessor` – ExecutorService, Callable, Future and concurrent results
- `REPORT` – contribution record, test/measurement templates and analysis prompts

## Compile and run from the project root (PowerShell)

```powershell
cd Task01_Q1_ATMExceptions
javac ATM.java
java ATM
cd ..

cd Task02_Q2_ExamExceptions
javac StorageDemo.java
java StorageDemo
cd ..

cd Task03_Q3_ResourceSafety
javac SalesReportDemo.java
java SalesReportDemo
cd ..

cd Task04_Q4_ThreadLifecycle
javac ThreadLifecycleDemo.java
java ThreadLifecycleDemo
cd ..

cd Task05_Q5_SeatBooking
javac SeatBooking.java
java SeatBooking
cd ..

cd Task06_Q6_DeadlockAndCommunication
javac DeadlockAndTokenDemo.java
java DeadlockAndTokenDemo
cd ..

cd Task07_Q7_ConcurrentProcessor
javac ResultProcessor.java
java ResultProcessor
cd ..
```

Run benchmark/experiment classes where provided:

```powershell
cd Task05_Q5_SeatBooking
javac SeatBookingBenchmark.java
java SeatBookingBenchmark
cd ..

cd Task07_Q7_ConcurrentProcessor
javac PoolBenchmark.java
java PoolBenchmark
cd ..
```

## Before submission – required personal and pair work

1. Run every task on your own computer. Save terminal screenshots in that task's `screenshots/` folder.
2. For Q1, test valid input, non-numeric input, negative amount, insufficient balance and missing card-reader file.
3. For Q3, create a deliberately failing input and use VS Code breakpoints to inspect the manual-close leak. Record actual open-resource evidence; do not invent counts.
4. For Q4, execute at least 10 runs and record the completion order of stations. Try a priority change and daemon thread as required.
5. For Q5, run the unsynchronized and synchronized versions repeatedly. Record actual double-book counts and timing.
6. For Q6, run the deadlock demonstration only with a timeout/daemon threads as described in its analysis; do not leave a hung process. Verify the token total.
7. For Q7, run thread pools of 1, 2, 4 and 8 workers, inject missing/corrupt inputs, and record elapsed time and exact success/failure counts.
8. Fill `REPORT/ExperimentResults.md` with your own measurements. Timings are machine/JVM dependent; no fabricated results are included.
9. Complete `REPORT/IndividualContribution.md` with both partners' names/register numbers and their real contributions. Both partners must understand every question.
10. Commit progressively, at least one commit per task, as required by the assignment.

## Important

This ZIP is a complete coding starter with working example implementations and analysis prompts. It does not replace the required pair discussion, personal debugging, measurements, screenshots, or viva preparation. Verify all output on your own JDK.
