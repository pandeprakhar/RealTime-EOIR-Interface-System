# TREX EOIR Middleware Architecture

## Objective

This project acts as a middleware layer between a Host System and a TREX EOIR payload.

The middleware is responsible for:

* Receiving EOIR commands
* Parsing TREX protocol packets
* Forwarding commands to EOIR
* Receiving EOIR responses
* Validating packets
* Tracking sequence numbers
* Logging communication
* Providing future telemetry APIs

---

# Current Architecture

```text
                    ┌─────────────────┐
                    │ Command Console │
                    │  (Operator UI)  │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   Host System   │
                    │ Packet Sender   │
                    └────────┬────────┘
                             │ UDP:5000
                             ▼
                    ┌─────────────────┐
                    │ MiddlewareServer│
                    │ Routing Layer   │
                    └────────┬────────┘
                             │ UDP:6000
                             ▼
                    ┌─────────────────┐
                    │ EOIR Simulator  │
                    │ Command Handler │
                    └────────┬────────┘
                             │ ACK
                             ▼
                    ┌─────────────────┐
                    │ MiddlewareServer│
                    └────────┬────────┘
                             │ UDP:8000
                             ▼
                    ┌─────────────────┐
                    │   Host System   │
                    └─────────────────┘
```

---

# Component Responsibilities

## CommandConsole

Acts as the operator interface.

Responsibilities:

* Accept user commands
* Build TREX packets
* Trigger transmission through Host System

Examples:

* Reference
* Bearing
* Offset
* Landmark
* PTU
* Point

---

## HostSystem

Acts as the mission computer.

Responsibilities:

* Serialize TREX packets
* Send packets through UDP
* Receive EOIR responses
* Display command results

---

## MiddlewareServer

Acts as the communication bridge.

Responsibilities:

* Receive packets from Host System
* Parse TREX protocol
* Validate packet structure
* Log communication
* Forward packets to EOIR
* Forward EOIR responses back to Host System

Future Responsibilities:

* Telemetry routing
* REST API integration
* Geo Stream processing

---

## EOIRSimulator

Simulates TREX EOIR hardware.

Responsibilities:

* Receive TREX commands
* Decode payloads
* Execute simulated operations
* Return ACK responses

This component will eventually be replaced by actual EOIR hardware.

---

# TREX Protocol Flow

## Outgoing Command

```text
CommandConsole
      ↓
Build TREX Packet
      ↓
Serialize
      ↓
HostSystem
      ↓
MiddlewareServer
      ↓
EOIRSimulator
```

---

## Incoming Response

```text
EOIRSimulator
      ↓
ACK Response
      ↓
MiddlewareServer
      ↓
HostSystem
```

---

# Implemented Commands

## GPM Module

* 5.1 Reference
* 5.2 Azimuth Elevation
* 5.3 Offset
* 5.4 Bearing
* 5.5 Landmark
* 5.6 PTU
* 5.7 Point

---

# Future Architecture

```text
                React Dashboard
                        │
                        ▼
                Spring Boot APIs
                        │
                        ▼
                 MiddlewareServer
                 /              \
                /                \
               ▼                  ▼

        TREX EOIR         Geo Stream Parser
                                │
                                ▼
                        GeoStreamManager
                                │
                                ▼
                      Live Map Visualization
```

---

# Future Development

## Telemetry

* Camera Latitude
* Camera Longitude
* Target Latitude
* Target Longitude
* Target Altitude
* Bearing Yaw
* Bearing Elevation
* EO FOV
* IR FOV

## Backend

* Spring Boot Integration
* REST APIs
* Real-Time Telemetry Access

## Frontend

* Live Drone Tracking
* Route Visualization
* Telemetry Dashboard
* Historical Track Replay
* EOIR Status Monitoring
