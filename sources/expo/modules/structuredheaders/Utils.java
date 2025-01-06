package expo.modules.structuredheaders;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes2.dex */
public class Utils {
    protected static boolean isAlpha(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    protected static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    protected static boolean isLcAlpha(char c) {
        return c >= 'a' && c <= 'z';
    }

    private Utils() {
    }

    protected static String checkKey(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Key can not be null or empty");
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ((i == 0 && charAt != '*' && !isLcAlpha(charAt)) || (!isLcAlpha(charAt) && !isDigit(charAt) && charAt != '_' && charAt != '-' && charAt != '.' && charAt != '*')) {
                throw new IllegalArgumentException(String.format("Invalid character in key at position %d: '%c' (0x%04x)", Integer.valueOf(i), Character.valueOf(charAt), Integer.valueOf(charAt)));
            }
        }
        return str;
    }

    protected static Map<String, ListElement<? extends Object>> checkKeys(Map<String, ListElement<? extends Object>> map) {
        Iterator it = ((Map) Objects.requireNonNull(map, "value must not be null")).keySet().iterator();
        while (it.hasNext()) {
            checkKey((String) it.next());
        }
        return map;
    }
}
