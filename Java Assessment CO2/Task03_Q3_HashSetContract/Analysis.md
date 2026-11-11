# Q3 – hashCode–equals contract

## Contract

If `a.equals(b)` is true, `a.hashCode()` must equal `b.hashCode()`. Unequal objects may share a hash code. HashSet first uses the hash to locate a bucket and then uses equality checks among candidates in that bucket. If equal objects return unrelated hashes, the set can place them in different buckets and fail to recognise the duplicate.

## Experiments

1. Same reference inserted twice: HashSet recognises the same object and stores one.
2. Two distinct BrokenStudent objects with the same registration number: equality says they are equal, but inherited identity-based hash codes may differ. The set can contain both. Exact identity hash values are JVM-dependent.
3. MutableStudent: changing the field used by hashCode after insertion means the object remains in the old bucket while lookup computes a new hash. `contains` will typically fail.

The corrected Student uses an immutable `final` registration number and `return regNo.hashCode()`. Expected completed-code output: `Unique students: 2`.

## Alternative structures

- `TreeSet<Student>` with a comparator on registration number: O(log n) add/search; comparator returning zero defines duplicates.
- `HashMap<String, Student>` keyed by registration number: average O(1) put/get; `putIfAbsent` prevents replacement of an existing record.
- HashSet: average O(1) add/contains under a well-distributed hash function; O(n) worst case. All structures use O(n) storage for n unique students.

Never mutate fields used in equality/hash code while an object is stored in a hash collection.
