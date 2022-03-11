package com.bikatoo.dionysus.dionysus.utils;

import com.bikatoo.dionysus.dionysus.exception.GlobalException;
import org.springframework.lang.NonNull;

public class PreconditionUtils {

    public static void checkConditionAndThrow(boolean b, @NonNull GlobalException e) {
        if (!b) {
            throw e;
        }
    }

    public static void checkNonNullAndThrow(Object o, @NonNull  GlobalException e) {
        if (o == null) {
            throw e;
        }
    }

    public static void checkNoneNullAndThrow(@NonNull GlobalException e, Object... os) {
        if (os == null || os.length <= 0) {
            throw e;
        }

        for (Object o : os) {
            if (o == null) {
                throw e;
            }
        }
    }

    public static void checkStringNonBlankAndThrow(String str, @NonNull GlobalException e) {
        if (str == null || "".equals(str)) {
            throw e;
        }
    }
}
