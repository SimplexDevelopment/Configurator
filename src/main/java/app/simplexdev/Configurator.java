package app.simplexdev;

import app.simplexdev.config.Configuration;
import app.simplexdev.data.ConfigType;
import app.simplexdev.data.Resource;
import app.simplexdev.parser.JSONParser;
import app.simplexdev.parser.PropertiesParser;
import app.simplexdev.parser.TomlParser;
import app.simplexdev.parser.YamlParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Configurator
{
    private static final Logger LOGGER = Logger.getLogger(Configurator.class.getSimpleName());

    private Configuration configuration = null;

    public Configurator(final String name, final File location, final ConfigType type)
    {
        this(name, location, type, null);
    }

    public Configurator(final String name, final File location, final ConfigType type, final Resource defaultResource)
    {
        this(name, location, type, defaultResource, false);
    }

    public Configurator(final String name, final File location, final ConfigType type, final Resource defaultResource,
                        final boolean copyDefaults)
    {
        if (copyDefaults)
        {
            copyDefaults(name, location, type, defaultResource);
        }
        else
        {
            processNormally(name, location, type, defaultResource);
        }
    }

    private void processNormally(String name, File location, ConfigType type, Resource defaultResource)
    {
        if (location.mkdirs())
            getLogger().info("Created directory " + location.getAbsolutePath());

        final File config = new File(location, name + type.getExtension());
        try
        {
            if (config.createNewFile() && defaultResource != null)
            {
                try (final FileOutputStream fos = new FileOutputStream(config))
                {
                    fos.write(defaultResource.getContent());
                }

                getLogger().info("Created file " + config.getAbsolutePath());
            }
        }
        catch (final IOException ex)
        {
            getLogger().severe("Failed to create file " + config.getAbsolutePath());
            ex.printStackTrace();
        }

        try (final FileInputStream fis = new FileInputStream(config))
        {
            switch (type)
            {
                case JSON -> this.configuration = new JSONParser().parse(fis);
                case YAML -> this.configuration = new YamlParser().parse(fis);
                case PROPERTIES -> this.configuration = new PropertiesParser().parse(fis);
                case TOML -> this.configuration = new TomlParser().parse(fis);
                default -> throw new IllegalArgumentException("Invalid config type.");
            }
        }
        catch (IOException e)
        {
            getLogger().severe("Failed to read file " + config.getAbsolutePath());
            e.printStackTrace();
        }
    }

    private void copyDefaults(String name, File location, ConfigType type, Resource defaultResource)
    {
        if (defaultResource == null)
            throw new IllegalArgumentException("Default resource cannot be null.");

        if (!defaultResource.getName().split("\\.")[0].equalsIgnoreCase(name))
            throw new IllegalArgumentException(
                "The name of the default resource must be the same as the name of the configuration file.");

        if (location.mkdirs())
            getLogger().info("Created directory " + location.getAbsolutePath());

        final File config = new File(location, name + type.getExtension());
        try
        {
            if (config.createNewFile())
                getLogger().info("Created file " + config.getAbsolutePath());
        }
        catch (final IOException ex)
        {
            getLogger().severe("Failed to create file " + config.getAbsolutePath());
            ex.printStackTrace();
        }

        try (final FileOutputStream fos = new FileOutputStream(config))
        {
            fos.write(defaultResource.getContent());
        }
        catch (final IOException ex)
        {
            getLogger().severe("Failed to write to file " + config.getAbsolutePath());
            ex.printStackTrace();
        }

        try (final FileInputStream fis = new FileInputStream(config))
        {
            switch (type)
            {
                case JSON -> this.configuration = new JSONParser().parse(fis);
                case YAML -> this.configuration = new YamlParser().parse(fis);
                case PROPERTIES -> this.configuration = new PropertiesParser().parse(fis);
                case TOML -> this.configuration = new TomlParser().parse(fis);
                default -> throw new IllegalArgumentException("Invalid config type.");
            }
        }
        catch (IOException e)
        {
            getLogger().severe("Failed to read file " + config.getAbsolutePath());
            e.printStackTrace();
        }
    }

    @NotNull
    public static Logger getLogger()
    {
        return LOGGER;
    }

    @Nullable
    public Configuration getConfig()
    {
        return this.configuration;
    }
}
