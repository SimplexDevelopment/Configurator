package app.simplexdev.config;

import java.util.List;
import java.util.Map;

public interface Section
{
    String getName();

    Section getSection(final String path);

    Boolean getBoolean(final String path);

    Integer getInteger(final String path);

    Double getDouble(final String path);

    String getString(final String path);

    Long getLong(final String path);

    Float getFloat(final String path);

    Short getShort(final String path);

    Byte getByte(final String path);

    Object get(final String path);

    List<String> getStringList(final String path);

    <T> List<T> getList(final String path, final Class<T> type);

    Map<String, Object> getMap(String path, Class<?> valueType);

    <T> T get(final String path, final Class<T> type);

    void set(final String path, final Object value);
}
