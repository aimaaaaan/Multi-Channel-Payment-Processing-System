# Payment Processing System — Design Patterns Demo

A focused Java demonstration of **Strategy, Decorator, Factory, and Template Method**
applied together in a single scenario.

## Scenario

A payment gateway that routes transactions through different channels
(Credit Card, Bank Transfer), applies a pluggable fee schedule,
and can have cross-cutting concerns (logging) added transparently.

## File Structure

```
src/
├── FeeStrategy.java              # STRATEGY — interface + FlatFee + PercentageFee
├── PaymentProcessor.java         # TEMPLATE METHOD — abstract base + CreditCard + BankTransfer
├── PaymentProcessorDecorator.java# DECORATOR — abstract base + LoggingDecorator
├── PaymentFactory.java           # FACTORY — creates processor + strategy pairs
└── Main.java                     # Demo (3 scenarios)
```

## How to Compile & Run

Requires **JDK 8+**.

```bash
javac -d out src/*.java
java -cp out Main
```

## Demo Scenarios

| # | Channel | Fee Strategy | Decorator | Note |
|---|---|---|---|---|
| 1 | Credit Card | Percentage 2.9% | Logging | Default from Factory |
| 2 | Bank Transfer | Flat $2.50 | Logging | Default from Factory |
| 3 | Credit Card | Flat $1.00 | None | Custom strategy injected |
