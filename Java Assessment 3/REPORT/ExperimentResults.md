# Assignment 3 – Experimental Results (fill with your own observations)

**Computer / CPU:** ____________________  
**RAM:** ____________________  
**Operating system:** ____________________  
**JDK version:** ____________________  
**Date:** ____________________

Do not enter guessed timings. Repeat experiments and report the actual values observed on your machine.

## Q1 – ATM test matrix

| Test | Input / condition | Expected path | Actual catch block | finally executed? | Result |
|---|---|---|---|---|---|
| 1 | Valid account and amount | Success | | | |
| 2 | Non-numeric amount | Input mismatch | | | |
| 3 | Negative amount | Invalid amount | | | |
| 4 | Amount exceeds balance | Insufficient funds | | | |
| 5 | Missing card-reader file | IOException | | | |

## Q3 – Resource safety

| Version | Input failure point | Resources opened | Resources closed | Report complete? |
|---|---|---:|---:|---|
| Manual close | | | | |
| try-with-resources | | | | |

## Q4 – Thread output order (10 runs)

| Run | Station completion order | Final total correct? |
|---:|---|---|
| 1 | | |
| 2 | | |
| 3 | | |
| 4 | | |
| 5 | | |
| 6 | | |
| 7 | | |
| 8 | | |
| 9 | | |
| 10 | | |

## Q5 – Seat booking

| Version | Run | Threads | Seats sold | Double bookings / oversell |
|---|---:|---:|---:|---:|
| Unsynchronized | 1 | 100 | | |
| Synchronized method | 1 | 100 | | |
| Synchronized block | 1 | 100 | | |

Record 10 runs for each version. For timing, compare 10,000 attempts under the same conditions.

## Q6 – Token counter

| Test | Producer count | Consumer count | Capacity observed | Sum |
|---|---:|---:|---:|---:|
| Correct while loops | | | | |
| if instead of while | | | | |

## Q7 – Executor pool benchmark

| Pool size | Input files | Successes | Failures | Total time (ms) |
|---:|---:|---:|---:|---:|
| 1 | 10,000 | | | |
| 2 | 10,000 | | | |
| 4 | 10,000 | | | |
| 8 | 10,000 | | | |

Also test a small hand-checkable set and record its expected and actual success/failure counts.
