# CSA09 – Programming in Java – Assignment 2 (CO2)

**Student:** K. Abu Hujaifa  
**Register number:** 192472308  
**Course:** Programming in Java  
**Java version used for this pack:** Java 17+ (record the exact output of `java -version` on your computer)  
**Repository name required by the assignment:** `CSA09-192472308-Assignment2`

This project contains Q1–Q7 source code, explanations, alternative designs, complexity analysis, and experiment runners. It is a working starter implementation for you to compile, run, inspect, and defend. **Do not submit fabricated measurements or screenshots:** run the programs on your own computer and fill the experiment CSV/Markdown files with your actual results.

## Folder structure

- `Task01_Q1_StringBenchmarks` – String, StringBuilder and StringBuffer experiments
- `Task02_Q2_ListBenchmarks` – ArrayList, LinkedList and ArrayDeque experiments
- `Task03_Q3_HashSetContract` – equals/hashCode experiments and corrected Attendance program
- `Task04_Q4_MapComparison` – HashMap, TreeMap and LinkedHashMap inventory comparison
- `Task05_Q5_GenericsLeaderboard` – generic leaderboard and comparator
- `Task06_Q6_PriorityTriage` – stable emergency triage priority queue
- `Task07_Q7_DesignChallenge` – original vs redesigned ticket generation
- `REPORT` – experiment log templates and design explanations

## Compile and run

Open PowerShell in this project folder. Compile each task separately because each folder is an independent Java program.

```powershell
cd Task01_Q1_StringBenchmarks
javac StringBenchmark.java
java StringBenchmark
cd ..

cd Task02_Q2_ListBenchmarks
javac ListBenchmark.java
java ListBenchmark
cd ..

cd Task03_Q3_HashSetContract
javac Attendance.java HashSetExperiments.java
java Attendance
java HashSetExperiments
cd ..

cd Task04_Q4_MapComparison
javac InventoryBenchmark.java
java InventoryBenchmark
cd ..

cd Task05_Q5_GenericsLeaderboard
javac LeaderboardDemo.java
java LeaderboardDemo
cd ..

cd Task06_Q6_PriorityTriage
javac Triage.java
java Triage
cd ..

cd Task07_Q7_DesignChallenge
javac TicketRedesignBenchmark.java
java TicketRedesignBenchmark
cd ..
```

If `javac` is not recognized, configure the JDK `bin` directory in PATH or use the VS Code terminal after restarting it.

## Required personal work before submission

1. Run every program and save terminal screenshots in each task's `screenshots/` folder.
2. Run benchmark programs at least 5 times on your own laptop. Record the median/average and exact workload in `REPORT/ExperimentResults.md`.
3. Record your Java version, operating system, CPU/RAM if known, and date.
4. Read each `Analysis.md`; revise the explanations in your own words and add your measured evidence.
5. Complete Q7 using the measured results from one earlier task. The provided Q7 demonstrates the Q1 String-building redesign; update its evidence after your own runs.
6. Create a GitHub repository named `CSA09-192472308-Assignment2`, commit progressively (one commit per task), and add its link to your report.

## Important measurement note

Microbenchmarks are affected by JIT compilation, garbage collection, CPU load, and warm-up. A single run is not reliable. The programs perform repeated runs and print timings as observations, not universal performance guarantees. Compare trends, not tiny differences.
