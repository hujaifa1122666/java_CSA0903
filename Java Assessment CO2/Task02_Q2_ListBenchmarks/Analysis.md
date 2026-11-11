# Q2 – ArrayList, LinkedList and ArrayDeque

## Expected behaviour and complexity

| Operation | ArrayList | LinkedList | ArrayDeque |
|---|---:|---:|---:|
| Add at end | O(1) amortized | O(1) | O(1) amortized |
| Add at front | O(n) due to shifting | O(1) | O(1) amortized |
| Insert in middle | O(n) search/shift | O(n) to reach node, O(1) link once there | Not supported by indexed API |
| Remove at front | O(n) shifting | O(1) | O(1) |
| Get k-th | O(1) | O(n) | No indexed access |
| Full iteration | O(n), cache-friendly | O(n), pointer traversal | O(n) |

ArrayList stores references in a resizable array; LinkedList stores linked nodes with element and neighbour references; ArrayDeque stores elements in a resizable circular array.

## Recommendation

ArrayList is suitable for a playlist when users frequently jump to an index, iterate, and append, while front/middle edits are relatively rare. Stop recommending it when the dominant workload becomes repeated front insertion/removal. For an “Up Next” queue with additions at both ends and removal from the front, ArrayDeque is a better fit. For arbitrary middle insertion, neither ArrayDeque nor LinkedList magically makes locating the position constant time.

## Experiment

The benchmark measures append, front add/remove, middle indexed get and full iteration at 1,000, 10,000 and 50,000 elements. ArrayDeque is tested for deque operations and iteration; it has no get(index). Timings are illustrative observations, not guarantees. Repeat the program and record median results.

## Plain array alternative

A fixed array with manual shifting has O(1) indexed access, O(n) insertion/removal at the front or middle, and O(n) iteration. It requires capacity management and explicit shifting; ArrayList already provides this machinery.
