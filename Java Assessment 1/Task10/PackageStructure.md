# Task 10 – Packages and Encapsulation

Suggested package layout for the integrated application:
```text
parking/
├── model/       # Vehicle, ParkingSlot, ParkingFloor, ParkingTicket, passes
├── pricing/     # PricingRule and implementations
├── service/     # ParkingService / ParkingFacility operations
└── app/         # Main application entry point
```

Explain why fields are private, what public methods expose, and how setters/constructors validate values. Include wrapper types and autoboxing risks such as comparing `Integer` objects using `==`.
