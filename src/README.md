# TREX EO/IR Interface Middleware System

## Overview

This project implements a middleware communication framework for the TREX EO/IR system based on the vendor-provided ICD (Interface Control Document).

The system is designed to:

* Receive commands from the EO/IR Interface
* Serialize commands into TREX packet format
* Route packets through a middleware layer
* Validate and log communication packets
* Forward commands to the Internal System
* Receive acknowledgements/responses
* Route responses back to the EO/IR Interface

The implementation follows an ICD-driven architecture, allowing new commands and protocol features to be added with minimal changes to the communication layer.

---

## Current Architecture

```text
Command Console
       |
       V
   EOIR Client
       |
   UDP :5000
       |
       V
 Middleware Server
       |
   UDP :6000
       |
       V
 Internal System
       |
   UDP :7000
       |
       V
 Middleware Server
       |
   UDP :8000
       |
       V
 EOIR Client
```

---

## Implemented Components

### Communication Layer

* UDP-based communication
* EOIR Client
* Middleware Server
* Internal System

### Protocol Layer

* TREX Packet Model
* Packet Serialization
* Packet Parsing
* Packet Validation
* Packet Logging

### Validation Features

* Header Validation
* Stop Byte Validation

### Logging Features

* RX Packet Logging
* TX Packet Logging
* Timestamped Log Entries

---

## Implemented GPM Commands

### 5.1 Reference Selection

Data Address:

```text
0x13000001
```

Supported Options:

| Value | Reference |
| ----- | --------- |
| 16    | PTU       |
| 17    | DMC       |

Status:

```text
Implemented
```

---

### 5.2 Azimuth / Elevation Command

Data Address:

```text
0x1300000C
```

Payload:

| Parameter | Type |
| --------- | ---- |
| Azimuth   | U32  |
| Elevation | U32  |

Status:

```text
Implemented
```

Example:

```text
Azimuth  = 90.0°
Elevation = 12.5°
```

Protocol Values:

```text
Azimuth  = 9000000
Elevation = 1250000
```

---

## Project Structure

```text
src
├── builder
├── logger
├── model
├── network
├── parser
├── serializer
├── service
└── ui
```

---

## Current Features Demonstrated

* End-to-End Command Flow
* Packet Serialization & Parsing
* Middleware Routing
* ACK Handling
* Command Console
* Packet Validation
* Packet Logging
* GPM Reference Command
* GPM Azimuth/Elevation Command

---

## Future Development

### Protocol Features

* Checksum Verification
* Response Packet Model
* Command Status Handling
* Sequence Number Tracking
* Message Type Support
* ACK/NACK Handling
* Heartbeat Packets
* Timestamp Support

### GPM Features

* Offset
* Bearing
* Landmark Location
* PTU Control
* Go To Location

### Application Features

* Spring Boot Integration
* REST APIs
* Real-Time Dashboard
* React Frontend
* Live Track Monitoring
* Command Execution Interface

---

## Current Status

The core communication framework and initial ICD command implementation have been completed successfully. The architecture has been designed to support future protocol expansion and GUI integration while maintaining modularity and scalability.

