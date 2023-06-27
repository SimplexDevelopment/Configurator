package app.simplexdev.parser;

import app.simplexdev.config.toml.TomlConfig;
import app.simplexdev.config.toml.TomlConfiguration;
import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class TomlParser implements Parser<TomlConfig> {

    @Override
    public TomlConfig parse(FileInputStream input) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
            Toml toml = new Toml().read(reader);
            TomlConfiguration configuration = new TomlConfiguration(toml);
            return new TomlConfig(configuration);
        }
    }

    @Override
    public void save(TomlConfig config, String filePath) throws IOException {
        try (FileOutputStream output = new FileOutputStream(filePath);
             OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.UTF_8)) {
            TomlWriter tomlWriter = new TomlWriter.Builder().build();
            String tomlString = tomlWriter.write(config.getToml());
            writer.write(tomlString);
        }
    }

    @Override
    public String getFileExtension() {
        return ".toml";
    }

    @Override
    public String getFileName() {
        return "Toml";
    }

    @Override
    public <S> S read(String filePath, Class<S> type) throws IOException {
        try (FileInputStream input = new FileInputStream(filePath);
             InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
            Toml toml = new Toml().read(reader);
            return toml.to(type);
        }
    }

    @Override
    public <S> void write(String filePath, S object) throws IOException {
        try (FileOutputStream output = new FileOutputStream(filePath);
             OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.UTF_8)) {
            TomlWriter tomlWriter = new TomlWriter.Builder().build();
            tomlWriter.write(object, writer);
        }
    }
}
