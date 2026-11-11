# Q3 – Resource safety and debugging

The demo creates two sample store files and compares a manual-close reader with a try-with-resources reader. The manual version closes only after the read loop. If an exception occurs during reading, the close statement is skipped and the file handle can leak. The try-with-resources version closes each reader automatically when its block exits, including exceptional exits.

## Required debugger experiment

1. Set a breakpoint on `while ((line = reader.readLine()) != null)` in `readManually`.
2. Use a deliberately invalid/unreadable input or modify the code temporarily to throw an IOException after opening the reader.
3. Step over the read operation and observe that the manual `reader.close()` line is skipped.
4. Repeat in `readStoreFiles`; execution leaves the try block and Java closes the reader.
5. Record the actual resource counts/observations from your debugger or operating-system handle inspection. Do not claim exact counts unless measured.

## Suppressed exceptions

If the try body throws one exception and `close()` also throws, the body exception remains primary and the close exception is attached as a suppressed exception. Inspect with `getSuppressed()`.

Try-with-resources is not sufficient by itself if the program has incorrect transaction logic, partial writes, no retry/recovery strategy, corrupted input, insufficient disk space, or resources not declared in the resource header.

For f files and total input size N, processing is O(N) time. The shown sequential version holds at most one input reader at a time, plus the output writer while writing. A careless implementation that opens all f files before processing can hold O(f) handles. Not closing resources can eventually exhaust the process file-handle limit.
