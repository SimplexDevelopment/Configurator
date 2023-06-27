package app.simplexdev.config.toml;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TomlConfiguration
{

    private Toml toml;

    public TomlConfiguration()
    {
        this.toml = new Toml();
    }

    public TomlConfiguration(final Toml toml) {
        this.toml = toml;
    }

    public void load(String filePath) throws IOException
    {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8))
        {
            toml.read(reader);
        }
    }

    public void save(String filePath) throws IOException
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8))
        {
            writer.write(toml.toString());
        }
    }

    public Boolean getBoolean(String path)
    {
        return toml.getBoolean(path);
    }

    public Integer getInteger(String path)
    {
        return toml.getLong(path).intValue();
    }

    public Double getDouble(String path)
    {
        return toml.getDouble(path);
    }

    public String getString(String path)
    {
        return toml.getString(path);
    }

    public Long getLong(String path)
    {
        return toml.getLong(path);
    }

    public Float getFloat(String path)
    {
        return toml.getDouble(path).floatValue();
    }

    public Short getShort(String path)
    {
        return toml.getLong(path).shortValue();
    }

    public Byte getByte(String path)
    {
        return toml.getLong(path).byteValue();
    }

    public Object get(String path) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    public List<String> getStringList(String path)
    {
        return toml.getList(path);
    }

    public <T> List<T> getList(String path, Class<T> type)
    {
        return toml.getList(path);
    }

    public Map<String, Object> getTable(String path)
    {
        return toml.getTable(path).toMap();
    }

    public <T> T get(String path, Class<T> type) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    public void set(String path, Object value) throws IOException
    {
        Map<String, Object> tomlData = toml.toMap();
        setRecursive(tomlData, path, value);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TomlWriter tomlWriter = new TomlWriter.Builder().build();
        tomlWriter.write(tomlData, outputStream);

        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        toml = new Toml().read(inputStream);

    }

    @SuppressWarnings("unchecked")
    private void setRecursive(Map<String, Object> data, String path, Object value)
    {
        String[] parts = path.split("\\.");

        Map<String, Object> current = data;
        for (int i = 0; i < parts.length - 1; i++)
        {
            String part = parts[i];
            if (!current.containsKey(part) || !(current.get(part) instanceof Map))
            {
                current.put(part, new HashMap<>());
            }
            current = (Map<String, Object>) current.get(part);
        }
        current.put(parts[parts.length - 1], value);
    }
}
