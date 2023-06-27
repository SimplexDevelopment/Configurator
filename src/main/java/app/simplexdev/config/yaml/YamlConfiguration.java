package app.simplexdev.config.yaml;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public final class YamlConfiguration
{

    private final Map<String, Object> data;

    public YamlConfiguration()
    {
        this.data = new HashMap<>();
    }

    public void load(String filePath) throws IOException
    {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8))
        {
            Yaml yaml = new Yaml();
            this.data.clear();
            this.data.putAll(yaml.load(reader));
        }
    }

    public void save(String filePath) throws IOException
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8))
        {
            Yaml yaml = new Yaml();
            yaml.dump(data, writer);
        }
    }

    public Boolean getBoolean(String path)
    {
        return get(path, Boolean.class);
    }

    public Integer getInteger(String path)
    {
        return get(path, Integer.class);
    }

    public Double getDouble(String path)
    {
        return get(path, Double.class);
    }

    public String getString(String path)
    {
        return get(path, String.class);
    }

    public Long getLong(String path)
    {
        return get(path, Long.class);
    }

    public Float getFloat(String path)
    {
        return get(path, Float.class);
    }

    public Short getShort(String path)
    {
        return get(path, Short.class);
    }

    public Byte getByte(String path)
    {
        return get(path, Byte.class);
    }

    public List<String> getStringList(String path)
    {
        return get(path, List.class);
    }

    public <T> List<T> getList(String path, Class<T> type)
    {
        List<T> list = new ArrayList<>();
        List<Object> rawList = get(path, List.class);
        if (rawList != null)
        {
            for (Object element : rawList)
            {
                if (type.isInstance(element))
                {
                    list.add(type.cast(element));
                }
            }
        }
        return list;
    }

    public Map<String, Object> getMap(String path, Class<?> valueType)
    {
        Map<String, Object> map = new HashMap<>();
        Map<?, ?> rawMap = get(path, Map.class);
        if (rawMap != null)
        {
            for (Map.Entry<?, ?> entry : rawMap.entrySet())
            {
                Object rawKey = entry.getKey();
                Object rawValue = entry.getValue();
                if (rawKey instanceof String key && valueType.isInstance(rawValue))
                {
                    Object value = valueType.cast(rawValue);
                    map.put(key, value);
                }
            }
        }
        return map;
    }

    public <T> T get(String path, Class<T> type)
    {
        Object value = get(path);
        if (type.isInstance(value))
        {
            return type.cast(value);
        }
        return null;
    }

    public void set(String path, Object value)
    {
        put(path, value);
    }

    public Object get(String path)
    {
        String[] parts = path.split("\\.");
        Object current = data;
        for (String part : parts)
        {
            if (current instanceof Map)
            {
                current = ((Map<?, ?>) current).get(part);
            }
            else
            {
                return null;
            }
        }
        return current;
    }

    @SuppressWarnings("unchecked")
    private void put(String path, Object value)
    {
        String[] parts = path.split("\\.");
        Map<String, Object> current = data;
        for (int i = 0; i < parts.length - 1; i++)
        {
            String part = parts[i];
            current = (Map<String, Object>) current.computeIfAbsent(part, k -> new HashMap<>());
        }
        current.put(parts[parts.length - 1], value);
    }
}
