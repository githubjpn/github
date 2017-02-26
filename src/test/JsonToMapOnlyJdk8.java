package test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JsonToMapOnlyJdk8 {

    static final String SPACE2 = "  ";
    static final ScriptEngineManager sem = new ScriptEngineManager();
    static final ScriptEngine engine = sem.getEngineByName("javascript");
    static final Class<?> engineClass = engine.getClass();

    /**
     * Converting JSON To Map With Java 8 Without Dependencies。
     * http://www.adam-bien.com/roller/abien/entry/converting_json_to_map_with
     *
     * <p>
     * Starting with JDK 8u60+ the built-in Nashorn engine is capable
     * to convert Json content into java.util.Map.
     * No external dependencies are required for parsing:
     * </p>
     *
     * @param args 引数
     * @throws ScriptException 例外
     */
    public static void main(String[] args) throws ScriptException {

        String jsonString = "{ \"key1\" : \"val1\", \"key2\" : \"val2\", "
                + "\"key0\" : { \"key01\" : \"val01\", \"key02\" : "
                + "[ \"val02-2\", \"val02-3\", {\"key02-1\": \"val02-1\"}]}}";

        String script = "Java.asJSONCompatible(" + jsonString + ")";
        Object result = engine.eval(script);
        // assertThat(result, instanceOf(Map.class));
        Map<?, ?> contents = (Map<?, ?>) result;
        print(contents, 0);
        // System.out.println("--------------------");
        // System.out.println(contents.toString());

    }

    private static void print(Object obj, int indent) {

        if (obj instanceof Collection<?>) {
            printCollection(obj, indent);
        } else {
            printMap(obj, indent);
        }

    }

    private static void printCollection(Object obj, int indent) {

        final String indentSpace = getIndentSpace(indent);

        System.out.println(indentSpace + "[");
        ((List<?>) obj).forEach((value) -> {

            if (engineClass.isInstance(value) || value instanceof Collection<?>
                    || value instanceof Map<?, ?>) {
                print(value, indent + 2);

            } else {
                System.out.print(indentSpace);
                System.out.println(SPACE2 + (value == null ? "null" : value.toString()));
            }
        });
        System.out.println(indentSpace + "]");

    }

    private static void printMap(Object obj, int indent) {

        final String indentSpace = getIndentSpace(indent);

        System.out.println(indentSpace + "{");
        ((Map<?, ?>) obj).forEach((key, value) -> {
            System.out.print(indentSpace);

            final String keyString = (key == null ? "null" : key.toString());

            if (engineClass.isInstance(value) || value instanceof Collection<?>
                    || value instanceof Map<?, ?>) {
                System.out.println(SPACE2 + keyString + ": ");
                print(value, indent + 2);

            } else {
                System.out.println(
                        SPACE2 + keyString + ": " + (value == null ? "null" : value.toString()));
            }

        });
        System.out.println(indentSpace + "}");

    }

    private static String getIndentSpace(int indent) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append(" ");
        }
        return sb.toString();

    }

}
