// ─────────────────────────────────────────────────────────────────────────────
// TEMPLATE METHOD PATTERN
// PaymentProcessor defines the fixed algorithm skeleton.
// CreditCardProcessor and BankTransferProcessor fill in the concrete steps.
// ─────────────────────────────────────────────────────────────────────────────

abstract class PaymentProcessor {

    protected FeeStrategy feeStrategy;

    PaymentProcessor(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    // Template method — defines the fixed sequence of steps
    public void process(String payer, double amount) {
        System.out.println("\n[" + channelName() + "] Processing payment for " + payer);
        validate(payer, amount);
        double fee = feeStrategy.calculate(amount);
        System.out.printf("  Fee (%s): $%.2f%n", feeStrategy.name(), fee);
        charge(payer, amount, fee);
        notify(payer);
    }

    // Abstract hooks — each subclass provides channel-specific behaviour
    protected abstract void validate(String payer, double amount);
    protected abstract void charge(String payer, double amount, double fee);
    protected abstract void notify(String payer);
    protected abstract String channelName();
}

// ── Concrete processor: Credit Card ──────────────────────────────────────────
class CreditCardProcessor extends PaymentProcessor {

    CreditCardProcessor(FeeStrategy feeStrategy) {
        super(feeStrategy);
    }

    protected void validate(String payer, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        System.out.println("  Validating card for: " + payer + " ... OK");
    }

    protected void charge(String payer, double amount, double fee) {
        System.out.printf("  Charged $%.2f + $%.2f fee to card.%n", amount, fee);
    }

    protected void notify(String payer) {
        System.out.println("  Receipt emailed to " + payer);
    }

    protected String channelName() { return "CreditCard"; }
}

// ── Concrete processor: Bank Transfer ────────────────────────────────────────
class BankTransferProcessor extends PaymentProcessor {

    BankTransferProcessor(FeeStrategy feeStrategy) {
        super(feeStrategy);
    }

    protected void validate(String payer, double amount) {
        if (amount < 1.0) throw new IllegalArgumentException("Minimum transfer is $1");
        System.out.println("  Validating bank account for: " + payer + " ... OK");
    }

    protected void charge(String payer, double amount, double fee) {
        System.out.printf("  ACH transfer of $%.2f + $%.2f fee initiated.%n", amount, fee);
    }

    protected void notify(String payer) {
        System.out.println("  Confirmation SMS sent to " + payer);
    }

    protected String channelName() { return "BankTransfer"; }
}
