# Q5 – Generics and comparator-based leaderboard

The comparator sorts by three keys in priority order: points descending, goal difference descending, and team name ascending. `thenComparing` is evaluated only when earlier keys tie. This gives a deterministic total ordering for distinct team names. Bisons ranks above Panthers because both have 9 points and goal difference 4, then `Bisons` precedes `Panthers` alphabetically.

`Leaderboard<T>` stores arbitrary T values and accepts `Comparator<? super T>`, allowing a comparator of T or a supertype. `List<? extends T>` is a producer wildcard for adding values from a subtype list. The `ranking()` method sorts a defensive copy, leaving the internal list unchanged.

Expected ranking:
`[Eagles(9,6), Bisons(9,4), Panthers(9,4), Cobras(6,1)]`

## Alternatives

`Comparable<Team>` defines one natural ordering inside Team, which is useful when there is one obvious universal order. A Comparator is easier to reuse for players, colleges and alternate ranking rules without modifying those classes. Three stable sorts can implement multi-key order by sorting least-significant key first (name), then goal difference, then points; this costs roughly three O(n log n) sorts rather than one O(n log n) comparator sort. Sorting a copy uses O(n) additional list space; sorting in place can reduce list-copy space.
