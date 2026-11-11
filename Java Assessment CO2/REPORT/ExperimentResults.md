# Experimental results – fill using your own runs

**Machine:** ____________________  
**Operating system:** ____________________  
**JDK (`java -version`):** ____________________  
**Date/time:** ____________________  

Do not copy invented numbers. Paste the output from your own executions and explain any surprising result.

## Q1 – String construction

| Workload | Fields/ticket | Tickets | String average ns | StringBuilder average ns | StringBuffer average ns |
|---|---:|---:|---:|---:|---:|
| Small | 6 | 1 | | | |
| Medium | 20 | 1,000 | | | |
| Large | 50 | 10,000 | | | |

## Q2 – List operations

Record output from `ListBenchmark`. Repeat at least five times and report median/average for each operation and size.

| Size | Operation | ArrayList ns | LinkedList ns | ArrayDeque ns (supported operations only) |
|---:|---|---:|---:|---:|
| 1,000 | append | | | |
| 10,000 | append | | | |
| 50,000 | append | | | |
| 1,000 | front add/remove | | | |
| 10,000 | front add/remove | | | |
| 50,000 | front add/remove | | | |
| 1,000 | indexed access | | N/A | N/A |
| 10,000 | indexed access | | N/A | N/A |
| 50,000 | indexed access | | N/A | N/A |

## Q3 – HashSet contract

| Test | Prediction | Observed size | Explanation |
|---|---|---:|---|
| Same duplicate objects, corrected class | 2 | | |
| Broken class, deliberately varied hash codes | | | |
| Mutable-key experiment | | | |

## Q4 – Maps

| Medicines | Map | 100,000 lookup time ns | Code-order report ns | Insertion-order report ns |
|---:|---|---:|---:|---:|
| 1,000 | HashMap | | | |
| 1,000 | TreeMap | | | |
| 1,000 | LinkedHashMap | | | |
| 10,000 | HashMap | | | |
| 10,000 | TreeMap | | | |
| 10,000 | LinkedHashMap | | | |
| 50,000 | HashMap | | | |
| 50,000 | TreeMap | | | |
| 50,000 | LinkedHashMap | | | |

## Q5 – Leaderboard

Record the expected and actual order, and add your own comparator edge case.

## Q6 – Triage

Record the hand-calculated order and benchmark results for 100, 1,000 and 10,000 waiting patients.

## Q7 – Redesign

| Workload | Original String + | Redesigned StringBuilder | Improvement / observation |
|---|---:|---:|---|
| 50 fields × 100 tickets | | | |
| 50 fields × 1,000 tickets | | | |
| 50 fields × 10,000 tickets | | | |

### Conclusion

What did your measurements support? What did they not support? State one limitation of your experiment.
