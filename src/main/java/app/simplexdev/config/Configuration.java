package app.simplexdev.config;

import app.simplexdev.data.ConfigType;
import app.simplexdev.parser.Parser;
import java.util.Map;

public interface Configuration extends Section
{
    ConfigType getConfigurationType();

    Parser<? extends Configuration> getConfigurationParser();
}
