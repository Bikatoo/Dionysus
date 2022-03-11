package com.bikatoo.dionysus.dionysus.interfaces.terminal;

import com.bikatoo.dionysus.dionysus.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class Terminals {

    private static final String terminal_package_path = "com.bikatoo.dionysus.dionysus.interfaces.terminal";

    private static final Set<Class<?>> terminal_implementations = ClassUtils.getImplementationsInPackage(terminal_package_path, Terminal.class);

    private static final Set<String> terminal_implementation_names = terminal_implementations.stream().map(c -> (String) ClassUtils.getFieldInClass("name", c)).filter(Objects::nonNull).collect(Collectors.toSet());

    public static Set<String> getTerminals() {
        return terminal_implementation_names;
    }

    public static boolean verifyTerminal(String name) {
        return terminal_implementation_names.contains(name);
    }
}
