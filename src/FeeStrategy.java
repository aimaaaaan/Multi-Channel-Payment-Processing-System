// ─────────────────────────────────────────────────────────────────────────────
// STRATEGY PATTERN
// FeeStrategy is the strategy interface.
// FlatFeeStrategy and PercentageFeeStrategy are the concrete strategies.
// ─────────────────────────────────────────────────────────────────────────────

interface FeeStrategy {
    double calculate(double amount);
    String name();
}

class FlatFeeStrategy implements FeeStrategy {
    private final double fee;
    FlatFeeStrategy(double fee) { this.fee = fee; }

    public double calculate(double amount) { return fee; }
    public String name() { return "Flat $" + fee; }
}

class PercentageFeeStrategy implements FeeStrategy {
    private final double rate; // e.g. 2.9 means 2.9%
    PercentageFeeStrategy(double rate) { this.rate = rate; }

    public double calculate(double amount) { return amount * rate / 100.0; }
    public String name() { return rate + "% of amount"; }
}
