package app.simplexdev.parser;

import app.simplexdev.config.Configuration;
import app.simplexdev.config.properties.PropertiesConfig;
import java.io.FileInputStream;
import java.io.IOException;

public class PropertiesParser implements Parser<PropertiesConfig>
{
    @Override
    public PropertiesConfig parse(final FileInputStream p0)
    {
        return null;
    }

    @Override
    public void save(final PropertiesConfig p0, final String p1)
    {
    }

    @Override
    public String getFileExtension()
    {
        return null;
    }

    @Override
    public String getFileName()
    {
        return null;
    }

    @Override
    public <S> S read(String p0, Class<S> p1) throws IOException
    {
        return null;
    }

    @Override
    public <S> void write(String p0, S p1) throws IOException
    {

    }
}
