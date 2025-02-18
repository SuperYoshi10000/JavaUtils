package local.ytk.util.json;

import com.google.gson.*;
import local.ytk.util.annotation.Static;

@Static
public class JsonUtils {
    public static final Gson GSON = new Gson();
    public static Object getAsObject(JsonElement element) {
        return switch (element) {
            case JsonNull n -> null;
            case JsonArray a -> a.asList();
            case JsonObject o -> o.asMap();
            case JsonPrimitive p when p.isBoolean() -> p.getAsBoolean();
            case JsonPrimitive p when p.isNumber() -> p.getAsNumber();
            case JsonPrimitive p when p.isString() -> p.getAsString();
            default -> element.toString();
        };
    }
    
    public static String serialize(JsonElement e) {
        return GSON.toJson(e);
    }
    
    public static JsonElement parse(String s) {
        return GSON.fromJson(s, JsonElement.class);
    }
}
