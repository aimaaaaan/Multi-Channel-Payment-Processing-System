// ─────────────────────────────────────────────────────────────────────────────
// DEMO — shows all four patterns working together
//
//  FACTORY   → PaymentFactory.create() builds the right processor
//  TEMPLATE  → PaymentProcessor.process() enforces the fixed workflow
//  STRATEGY  → FeeStrategy implementations swap fee algorithms
//  DECORATOR → LoggingDecorator wraps any processor transparently
// ─────────────────────────────────────────────────────────────────────────────

public class Main {

    public static void main(String[] args) {

        // ── Demo 1: Credit card with default 2.9% fee, wrapped in logger ─────
        System.out.println("=== Demo 1: Credit Card (2.9% fee) + Logging ===");
        PaymentProcessor cc = PaymentFactory.create("credit_card");     // Factory
        PaymentProcessor ccLogged = new LoggingDecorator(cc);           // Decorator
        ccLogged.process("Alice", 200.00);                              // Template + Strategy

        // ── Demo 2: Bank transfer with default flat $2.50 fee ─────────────────
        System.out.println("\n=== Demo 2: Bank Transfer (flat $2.50 fee) + Logging ===");
        PaymentProcessor bt = PaymentFactory.create("bank_transfer");   // Factory
        PaymentProcessor btLogged = new LoggingDecorator(bt);           // Decorator
        btLogged.process("Bob", 500.00);                                // Template + Strategy

        // ── Demo 3: Credit card with a custom flat fee (strategy swapped) ─────
        System.out.println("\n=== Demo 3: Credit Card with custom Flat $1 fee (no logger) ===");
        PaymentProcessor custom = new CreditCardProcessor(              // Strategy swapped
                                      new FlatFeeStrategy(1.00));
        custom.process("Carol", 350.00);                                // Template
    }
}
