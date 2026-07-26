package org.example.islandManager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Parameters {
    private boolean isMultithreaded;
    private int x;
    private int y;
    private boolean isLoggingEnabled;

    public Parameters(boolean isMultithreaded, int x, int y, boolean isLoggingEnabled) {
        this.isMultithreaded = isMultithreaded;
        this.x = x;
        this.y = y;
        this.isLoggingEnabled = isLoggingEnabled;
    }

}
