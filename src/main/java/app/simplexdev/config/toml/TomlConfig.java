package app.simplexdev.config.toml;

import app.simplexdev.config.Configuration;
import app.simplexdev.config.Section;
import app.simplexdev.data.ConfigType;
import app.simplexdev.parser.Parser;

import app.simplexdev.parser.TomlParser;
import com.moandjiezana.toml.Toml;
import java.util.List;
import java.util.Map;

public class TomlConfig implements Configuration
{

    private final Parser<? extends Configuration> configurationParser;
    private final String name;
    private final TomlConfiguration tomlConfiguration;

    public TomlConfig(TomlConfiguration tomlConfiguration) {
        this.configurationParser = new TomlParser();
        this.name = "config";
        this.tomlConfiguration = tomlConfiguration;
    }

    @Override
    public ConfigType getConfigurationType() {
        return ConfigType.TOML;
    }

    public TomlConfiguration getToml() {
        return tomlConfiguration;
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
        Object sectionObject = tomlConfiguration.get(path);
        if (sectionObject instanceof TomlConfiguration) {
            return new TomlConfig((TomlConfiguration) sectionObject);
        }
        return null;
    }

    @Override
    public Boolean getBoolean(String path) {
        return tomlConfiguration.getBoolean(path);
    }

    @Override
    public Integer getInteger(String path) {
        return tomlConfiguration.getLong(path).intValue();
    }

    @Override
    public Double getDouble(String path) {
        return tomlConfiguration.getDouble(path);
    }

    @Override
    public String getString(String path) {
        return tomlConfiguration.getString(path);
    }

    @Override
    public Long getLong(String path) {
        return tomlConfiguration.getLong(path);
    }

    @Override
    public Float getFloat(String path) {
        return tomlConfiguration.getDouble(path).floatValue();
    }

    @Override
    public Short getShort(String path) {
        return tomlConfiguration.getLong(path).shortValue();
    }

    @Override
    public Byte getByte(String path) {
        return tomlConfiguration.getLong(path).byteValue();
    }

    @Override
    public Object get(String path) {
        return tomlConfiguration.get(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return tomlConfiguration.getStringList(path);
    }

    @Override
    public <T> List<T> getList(String path, Class<T> type) {
        return tomlConfiguration.getList(path, type);
    }

    @Override
    public Map<String, Object> getMap(String path, Class<?> valueType) {
        return tomlConfiguration.getTable(path);
    }

    @Override
    public <T> T get(String path, Class<T> type) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(String path, Object value) {
        tomlConfiguration.set(path, value);
    }
}
