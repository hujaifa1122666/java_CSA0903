# Task 1 – Problem Analysis and Identification of Objects

## Inputs
- Vehicle registration number and category
- User category (student, staff, visitor)
- Pass details and validity
- Parking entry and exit timestamps
- Facility floor and slot configuration
- EV charging consumption, where applicable

## Outputs
- Allocated floor and slot
- Unique parking ticket
- Parking duration and fee
- Released slot after exit
- Daily collection and parking status

## Candidate Classes and Responsibilities
Complete this section with the purpose and justification of each class:
- `Vehicle`
- `ParkingSlot`
- `ParkingFloor`
- `ParkingFacility`
- `ParkingTicket`
- `ParkingUser`
- `ParkingPass`
- `PricingRule`

## Business Rules
- A slot cannot contain more than one vehicle.
- A ticket number must not be reused.
- A registration number cannot have two active tickets.
- Only a valid and suitable slot can be allocated.
- An occupied slot is released only through a valid exit.
- Fee and pass rules must be applied consistently.

## Assumptions
Document the assumptions used by your implementation, including fee slabs, pass validity, and slot suitability.
