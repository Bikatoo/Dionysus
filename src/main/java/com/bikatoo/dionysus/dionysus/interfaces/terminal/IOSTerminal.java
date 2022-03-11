package com.bikatoo.dionysus.dionysus.interfaces.terminal;

public class IOSTerminal implements Terminal{

    private static final String name = "iOS";

    @Override
    public String getName() {
        return name;
    }
}
