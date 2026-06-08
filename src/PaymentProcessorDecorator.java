// ─────────────────────────────────────────────────────────────────────────────
// DECORATOR PATTERN
// PaymentProcessorDecorator is the abstract base decorator.
// LoggingDecorator adds audit logging around every payment transparently.
// ─────────────────────────────────────────────────────────────────────────────

abstract class PaymentProcessorDecorator extends PaymentProcessor {

    protected final PaymentProcessor wrapped;

    PaymentProcessorDecorator(PaymentProcessor wrapped) {
        super(wrapped.feeStrategy);
        this.wrapped = wrapped;
    }

    // Forward the template call to the wrapped processor by default
    public void process(String payer, double amount) {
        wrapped.process(payer, amount);
    }

    // These are required by the abstract class but never called directly
    // because decorators intercept at the process() level.
    protected void validate(String payer, double amount) { wrapped.validate(payer, amount); }
    protected void charge(String payer, double amount, double fee) { wrapped.charge(payer, amount, fee); }
    protected void notify(String payer) { wrapped.notify(payer); }
    protected String channelName() { return wrapped.channelName(); }
}

// ── Concrete decorator: Logging ───────────────────────────────────────────────
class LoggingDecorator extends PaymentProcessorDecorator {

    LoggingDecorator(PaymentProcessor wrapped) {
        super(wrapped);
    }

    @Override
    public void process(String payer, double amount) {
        System.out.println(">> [LOG] START payment | payer=" + payer
                + " | amount=$" + amount + " | channel=" + wrapped.channelName());
        wrapped.process(payer, amount);
        System.out.println(">> [LOG] END payment for " + payer + " — done.");
    }
}
