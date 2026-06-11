package org.example;

public class EvanteLogger {
    private boolean enabled;
    public EvanteLogger(boolean enabled) {
        this.enabled = enabled;
    }
    public synchronized void log(String message) {
        if (enabled) {
            System.out.println(message);
        }
    }
}
