# Q4 – Choosing a Map for pharmacy inventory

| Map | put/get | Iteration order | Best fit |
|---|---|---|---|
| HashMap | O(1) average; O(n) worst case | No guaranteed order | Fast key lookup |
| TreeMap | O(log n) | Sorted by key | Code-order report |
| LinkedHashMap | O(1) average | Insertion order by default | Added-order report |

All three store O(n) entries. TreeMap maintains a sorted tree and pays O(log n) per basic lookup/update. HashMap and LinkedHashMap use hashing; LinkedHashMap additionally maintains linked ordering information.

The benchmark creates 1,000, 10,000 and 50,000 medicines, performs 100,000 lookups, and times reports. Code-order report time includes sorting keys for HashMap/LinkedHashMap; TreeMap's iteration is already ordered, although the common benchmark sorts all maps to compare the explicit report operation. Insertion-order output is naturally provided by LinkedHashMap. HashMap does not promise insertion order.

## Deployment choice

If lookup speed dominates, HashMap is a reasonable primary index; produce reports by sorting keys when needed. If both reports are frequent and memory allows, maintain two indexes or choose a primary map based on workload. TreeMap naturally supports code-order reports; LinkedHashMap naturally supports insertion-order reports. State your acceptable single-lookup latency (for example, 5 ms) and compare it with measured values on your machine.

Hashtable is a legacy synchronized map. Modern code generally uses HashMap/ConcurrentHashMap and explicit concurrency design.

## Sorted array alternative

A sorted array supports O(log n) lookup using `Arrays.binarySearch`, O(n) insertion due to shifting, O(n) storage, and O(n) code-order report. It can be compact and cache-friendly but is less convenient for frequent updates.
