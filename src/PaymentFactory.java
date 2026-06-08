// ─────────────────────────────────────────────────────────────────────────────
// FACTORY PATTERN
// PaymentFactory decides which PaymentProcessor to create based on a type
// string, hiding construction details from the client.
// ─────────────────────────────────────────────────────────────────────────────

class PaymentFactory {

    // Default fee strategies paired with each channel:
    //   credit_card   → PercentageFeeStrategy (2.9%)
    //   bank_transfer → FlatFeeStrategy ($2.50)
    public static PaymentProcessor create(String type) {
        switch (type.toLowerCase()) {
            case "credit_card":
                return new CreditCardProcessor(new PercentageFeeStrategy(2.9));
            case "bank_transfer":
                return new BankTransferProcessor(new FlatFeeStrategy(2.50));
            default:
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}
