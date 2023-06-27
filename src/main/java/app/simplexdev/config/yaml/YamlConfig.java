package app.simplexdev.config.yaml;

import app.simplexdev.config.Configuration;
import app.simplexdev.config.Section;
import app.simplexdev.data.ConfigType;
import app.simplexdev.parser.Parser;

import app.simplexdev.parser.YamlParser;
import java.util.List;
import java.util.Map;

public class YamlConfig implements Configuration
{

    private final Parser<? extends Configuration> configurationParser;
    private final String name;
    private final YamlConfiguration yamlConfiguration;

    public YamlConfig(YamlConfiguration yamlConfiguration) {
        this.configurationParser = new YamlParser();
        this.name = "config";
        this.yamlConfiguration = yamlConfiguration;
    }

    @Override
    public ConfigType getConfigurationType() {
        return ConfigType.YAML;
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
        Object sectionObject = yamlConfiguration.get(path);
        if (sectionObject instanceof YamlConfiguration) {
            return new YamlConfig((YamlConfiguration) sectionObject);
        }
        return null;
    }

    @Override
    public Boolean getBoolean(String path) {
        return yamlConfiguration.getBoolean(path);
    }

    @Override
    public Integer getInteger(String path) {
        return yamlConfiguration.getInteger(path);
    }

    @Override
    public Double getDouble(String path) {
        return yamlConfiguration.getDouble(path);
    }

    @Override
    public String getString(String path) {
        return yamlConfiguration.getString(path);
    }

    @Override
    public Long getLong(String path) {
        return yamlConfiguration.getLong(path);
    }

    @Override
    public Float getFloat(String path) {
        return yamlConfiguration.getFloat(path);
    }

    @Override
    public Short getShort(String path) {
        return yamlConfiguration.getShort(path);
    }

    @Override
    public Byte getByte(String path) {
        return yamlConfiguration.getByte(path);
    }

    @Override
    public Object get(String path) {
        return yamlConfiguration.get(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return yamlConfiguration.getStringList(path);
    }

    @Override
    public <T> List<T> getList(String path, Class<T> type) {
        return yamlConfiguration.getList(path, type);
    }

    @Override
    public Map<String, Object> getMap(String path, Class<?> valueType) {
        return yamlConfiguration.getMap(path, valueType);
    }

    @Override
    public <T> T get(String path, Class<T> type) {
        return yamlConfiguration.get(path, type);
    }

    @Override
    public void set(String path, Object value) {
        yamlConfiguration.set(path, value);
    }
}
