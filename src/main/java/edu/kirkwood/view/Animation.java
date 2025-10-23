package edu.kirkwood.view;

public class Animation implements Runnable {
    private final String message;
    private volatile boolean running = true;
    private final String[] states = {". ", ".. ", "... "};
    private int currentStateIndex = 0;

    public Animation(String message) {
        this.message = message;
    }

    public void stopAnimation() {
        this.running = false;
    }

    @Override
    public void run() {
        while(running) {
            System.out.print("\r" + message + states[currentStateIndex]);
            currentStateIndex = (currentStateIndex + 1) % states.length;
            try {
                Thread.sleep(400); // Pauses animation for 4/10 second
            } catch (InterruptedException e) {
                this.running = false;
                Thread.currentThread().interrupt();
            }
        }
        String cleanup = " ".repeat(message.length() + states.length);
        System.out.println("\r" + cleanup + "\r");
    }
}
