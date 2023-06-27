package app.simplexdev.parser;

import app.simplexdev.config.yaml.YamlConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class YamlParser implements Parser<YamlConfig> {

    @Override
    public YamlConfig parse(FileInputStream fileInputStream) throws IOException {
        Yaml yaml = new Yaml();
        try (InputStreamReader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8)) {
            Object parsedData = yaml.load(reader);
            if (parsedData instanceof YamlConfig) {
                return (YamlConfig) parsedData;
            } else {
                throw new IOException("Invalid YAML data. Unable to parse into YamlConfig.");
            }
        }
    }

    @Override
    public void save(YamlConfig yamlConfig, String filePath) throws IOException {
        Yaml yaml = new Yaml();
        String yamlString = yaml.dump(yamlConfig);
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8)) {
            writer.write(yamlString);
        }
    }

    @Override
    public String getFileExtension() {
        return ".yaml";
    }

    @Override
    public String getFileName() {
        return "config";
    }

    @Override
    public <S> S read(String filePath, Class<S> type) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Yaml yaml = new Yaml();
            return yaml.loadAs(fileInputStream, type);
        }
    }

    @Override
    public <S> void write(String filePath, S object) throws IOException {
        Yaml yaml = new Yaml();
        String yamlString = yaml.dump(object);
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8)) {
            writer.write(yamlString);
        }
    }
}
