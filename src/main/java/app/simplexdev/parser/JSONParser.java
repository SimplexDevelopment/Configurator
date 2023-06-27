package app.simplexdev.parser;

import app.simplexdev.config.json.JsonConfig;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.google.gson.JsonParser;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JSONParser implements Parser<JsonConfig> {
    @Override
    public JsonConfig parse(FileInputStream fileInputStream) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            return new JsonConfig(jsonObject);
        }
    }

    @Override
    public void save(JsonConfig jsonConfig, String filePath) throws IOException {
        JsonObject jsonObject = jsonConfig.getJsonObject();
        String jsonString = jsonObject.toString();
        byte[] bytes = jsonString.getBytes(StandardCharsets.UTF_8);
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(bytes);
        }
    }

    @Override
    public String getFileExtension() {
        return ".json";
    }

    @Override
    public String getFileName() {
        return "config";
    }

    @Override
    public <S> S read(String filePath, Class<S> type) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, type);
        }
    }

    @Override
    public <S> void write(String filePath, S object) throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(object);
        byte[] bytes = jsonString.getBytes(StandardCharsets.UTF_8);
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(bytes);
        }
    }
}
