# Task 3 – Control Logic and Pseudocode

## Slot Allocation Pseudocode
```text
FOR each floor f
    FOR each slot s on floor f
        IF slot is free
            store vehicle number
            RETURN {f, s}
        END IF
    END FOR
END FOR
RETURN null
```

## Fee Calculation Pseudocode
```text
IF duration <= 0
    fee = 0
ELSE
    billableHours = ceiling(durationMinutes / 60)
    fee = billableHours * applicableHourlyRate
    apply daily maximum and other pricing rules
END IF
RETURN fee
```

Explain the precise fee slabs and assumptions used by your implementation. Include the free-slot-stack alternative and its complexity.
