package app.simplexdev.parser;

import app.simplexdev.config.Section;
import java.io.FileInputStream;
import java.io.IOException;

public interface Parser<T extends Section>
{
    T parse(final FileInputStream p0) throws IOException;

    void save(final T p0, final String p1) throws IOException;

    String getFileExtension();

    String getFileName();

    <S> S read(final String p0, final Class<S> p1) throws IOException;

    <S> void write(final String p0, final S p1) throws IOException;
}
