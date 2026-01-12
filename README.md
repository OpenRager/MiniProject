# Mini Project: Implementing a Mobile Communication System

Course: Object-Oriented Programming 1 (2nd Year Computer Engineering)
Professor: K. Nasri

This project models a simplified mobile communication system using core OOP principles. It focuses on class design, relationships, and enforcing real-world constraints such as network coverage, capacity limits, and mobility during calls.

## Project Overview

- Core Entities: `Phone`, `SimCard`, `Antenna`, `Network`, `Point`
- Core Tasks:
  - Make/receive calls if rules are satisfied
  - Enforce antenna capacity and coverage
  - Disallow isolated antennas (must overlap with at least one other)
  - Support movement during calls with handoff; disconnect when out of coverage

## System Requirements (Implemented)

1. Make a Call (Phone → Antenna via Network)
   - Phone has battery power
   - SIM is activated and has sufficient credit (≥ 10)
   - Phone is within range of at least one antenna
   - Connects to the nearest in-range antenna that has free capacity
   - Credit is deducted only for outgoing calls (10 units per call attempt)
   - Disconnect Rule: If the phone moves out of range (and no handoff is possible), the call immediately ends

2. Receive a Call
   - Phone must have battery and be in range of an antenna with available capacity
   - Incoming calls are rejected if the phone is already in a call
   - Caller and receiver can be attached to different antennas

3. Network Constraint
   - Only antennas connected to at least one other antenna (overlapping coverage) can be added
   - The first antenna is allowed (bootstrapping the network); subsequent antennas must overlap coverage with an existing one

## Class Design

- `Point`
  - Immutable 2D point with `distanceTo(Point)`.

- `SimCard`
  - Attributes: phone number, credit balance, activation status
  - Methods: `deductCredit()`, `checkCredit()` (returns true if credit ≥ 10)

- `Antenna`
  - Attributes: `location`, `coverageRadius`, `capacityLimit`, `activeComms`
  - Methods:
    - `phoneinRange(Phone)`: phone within coverage
    - `incrementActiveCalls()` / `decrementActiveCalls()`
    - `canAcceptNewCall()`: true if `activeComms < capacityLimit`

- `Network`
  - Maintains a collection of antennas
  - `addAntennas(Antenna)`: rejects duplicates and isolated antennas (except first)
  - `findNearestAntenna(Phone)`: returns nearest antenna that both covers the phone and has capacity

- `Phone`
  - Attributes: `batteryLevel`, `location`, `simCard`, `inCall`, `connectedAntenna`
  - Methods:
    - `canAcceptNewCall(Network)`: validates all preconditions (battery, SIM, credit, coverage & capacity, not already in call)
    - `makeCall(Network)`: starts a call and charges SIM (outgoing only)
    - `receiveCall(Network)`: starts a call if capacity and battery allow, rejects if already in call
    - `endCall()`: ends the current call and frees capacity
    - `setLocation(Point, Network)`: supports moving calls; performs handoff to another antenna when possible, otherwise disconnects

## How It Works

- The system chooses the nearest antenna in range that also has free capacity.
- Movement during an active call triggers a check:
  - If still covered by the current antenna, nothing changes
  - Else, it attempts handoff to the nearest in-range antenna with capacity
  - If none is available, the call disconnects immediately

## Demo Scenarios

Use the main program in [src/CommunicationsSystem.java](src/CommunicationsSystem.java) (and [src/Main.java](src/Main.java) that delegates to it) to simulate common scenarios:

- Outgoing call with sufficient battery and credit
- Movement within coverage (handoff between overlapping antennas)
- Reject incoming call when already on a call
- Capacity saturation at an antenna
- Disconnect when moving out of all coverage, then retry

## Project Structure

```
MiniProject.iml
src/
  Antenna.java
  CommunicationsSystem.java
  Network.java
  Phone.java
  Point.java
  SimCard.java
```


## Notes

- Code includes constructors, getters, setters and toString()
- Logic is commented
- The network prevents isolated antennas (except the first one to be initiated)
- Calls are capacity-aware and location-aware


## Authors / Contributors

- Mohammed El Amine BOUADJADJA
- Anes HEDLI
- Nidhal BECHNA
- Abd El Bari ADJAL



