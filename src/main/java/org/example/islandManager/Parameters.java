package org.example.islandManager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Parameters {
    private final boolean isMultithreaded;
    private final int x;
    private final int y;
    private final boolean isLoggingEnabled;

    public Parameters(boolean isMultithreaded, int x, int y, boolean isLoggingEnabled) {
        this.isMultithreaded = isMultithreaded;
        this.x = x;
        this.y = y;
        this.isLoggingEnabled = isLoggingEnabled;
    }

}
