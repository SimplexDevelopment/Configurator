package app.simplexdev.config.properties;

import app.simplexdev.config.Configuration;
import app.simplexdev.config.Section;
import app.simplexdev.data.ConfigType;
import app.simplexdev.parser.PropertiesParser;
import java.util.List;
import java.util.Map;

public class PropertiesConfig implements Configuration
{
    @Override
    public ConfigType getConfigurationType()
    {
        return null;
    }

    @Override
    public PropertiesParser<PropertiesConfig> getConfigurationParser()
    {
        return null;
    }

    @Override
    public String getName()
    {
        return null;
    }

    @Override
    public Section getSection(String path)
    {
        return null;
    }

    @Override
    public Boolean getBoolean(String path)
    {
        return null;
    }

    @Override
    public Integer getInteger(String path)
    {
        return null;
    }

    @Override
    public Double getDouble(String path)
    {
        return null;
    }

    @Override
    public String getString(String path)
    {
        return null;
    }

    @Override
    public Long getLong(String path)
    {
        return null;
    }

    @Override
    public Float getFloat(String path)
    {
        return null;
    }

    @Override
    public Short getShort(String path)
    {
        return null;
    }

    @Override
    public Byte getByte(String path)
    {
        return null;
    }

    @Override
    public Object get(String path)
    {
        return null;
    }

    @Override
    public List<String> getStringList(String path)
    {
        return null;
    }

    @Override
    public <T> List<T> getList(String path, Class<T> type)
    {
        return null;
    }

    @Override
    public Map<String, Object> getMap(String path, Class<?> valueType)
    {
        return null;
    }

    @Override
    public <T> T get(String path, Class<T> type)
    {
        return null;
    }

    @Override
    public void set(String path, Object value)
    {

    }
}
