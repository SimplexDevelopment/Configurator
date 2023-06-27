package app.simplexdev.data;

public enum ConfigType
{
    YAML(".yml"),
    JSON(".json"),
    PROPERTIES(".properties"),
    TOML(".toml");

    private final String extension;

    ConfigType(final String extension) {
        this.extension = extension;
    }

    public final String getExtension() {
        return this.extension;
    }
}
