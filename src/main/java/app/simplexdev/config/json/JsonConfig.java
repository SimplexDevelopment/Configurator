package app.simplexdev.config.json;

import app.simplexdev.config.Configuration;
import app.simplexdev.config.Section;
import app.simplexdev.data.ConfigType;
import app.simplexdev.parser.JSONParser;
import app.simplexdev.parser.Parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonConfig implements Configuration
{

    private final JSONParser configurationParser;
    private final String name;
    private final JsonObject jsonObject;

    public JsonConfig(JsonObject jsonObject) {
        this.configurationParser = new JSONParser();
        this.name = "config";
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    @Override
    public ConfigType getConfigurationType() {
        return ConfigType.JSON;
    }

    @Override
    public Parser<? extends Configuration> getConfigurationParser() {
        return configurationParser;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Section getSection(String path) {
        JsonObject sectionObject = jsonObject.getAsJsonObject(path);
        if (sectionObject != null) {
            return new JsonConfig(sectionObject);
        }
        return null;
    }

    @Override
    public Boolean getBoolean(String path) {
        return jsonObject.getAsJsonPrimitive(path).getAsBoolean();
    }

    @Override
    public Integer getInteger(String path) {
        return jsonObject.getAsJsonPrimitive(path).getAsInt();
    }

    @Override
    public Double getDouble(String path) {
        return jsonObject.getAsJsonPrimitive(path).getAsDouble();
    }

    @Override
    public String getString(String path) {
        return jsonObject.getAsJsonPrimitive(path).getAsString();
    }

    @Override
    public Long getLong(String path) {
        return jsonObject.getAsJsonPrimitive(path).getAsLong();
    }

    @Override
    public Float getFloat(String path) {
        return jsonObject.getAsJsonPrimitive(path).getAsFloat();
    }

    @Override
    public Short getShort(String path) {
        return jsonObject.getAsJsonPrimitive(path).getAsShort();
    }

    @Override
    public Byte getByte(String path) {
        return jsonObject.getAsJsonPrimitive(path).getAsByte();
    }

    @Override
    public Object get(String path) {
        return jsonObject.get(path);
    }

    @Override
    public List<String> getStringList(String path) {
        JsonArray jsonArray = jsonObject.getAsJsonArray(path);
        if (jsonArray != null) {
            List<String> stringList = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                stringList.add(element.getAsString());
            }
            return stringList;
        }
        return null;
    }

    @Override
    public <T> List<T> getList(String path, Class<T> type) {
        JsonArray jsonArray = jsonObject.getAsJsonArray(path);
        if (jsonArray != null) {
            List<T> list = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                list.add(new Gson().fromJson(element, type));
            }
            return list;
        }
        return null;
    }

    @Override
    public Map<String, Object> getMap(String path, Class<?> valueType) {
        JsonObject mapObject = jsonObject.getAsJsonObject(path);
        if (mapObject != null) {
            Map<String, Object> map = new HashMap<>();
            for (Map.Entry<String, JsonElement> entry : mapObject.entrySet()) {
                String key = new Gson().fromJson(entry.getKey(), String.class);
                Object value = new Gson().fromJson(entry.getValue(), valueType);
                map.put(key, value);
            }
            return map;
        }
        return null;
    }

    @Override
    public <T> T get(String path, Class<T> type) {
        return new Gson().fromJson(jsonObject.get(path), type);
    }

    @Override
    public void set(String path, Object value) {
        jsonObject.add(path, new Gson().toJsonTree(value));
    }
}
