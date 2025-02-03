package dev.yyuh.animesuchtutils.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class AnimesuchtUtilsClient implements ClientModInitializer {

    public static boolean highlightRed = false;
    public static boolean highlightPink = false;
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("animesucht_utils.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static class ModConfiguration {
        boolean highlightRed = false;
        boolean highlightPink = false;
    }

    @Override
    public void onInitializeClient() {
        loadConfiguration();
    }

    public static void saveConfiguration() {
        try {
            ModConfiguration configuration = new ModConfiguration();
            configuration.highlightPink = highlightPink;
            configuration.highlightRed = highlightRed;
            try (FileWriter writer = new FileWriter(CONFIG_PATH.toFile())) {
                GSON.toJson(configuration, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadConfiguration() {
        try {
            if(CONFIG_PATH.toFile().exists()) {
                ModConfiguration configuration = GSON.fromJson(new FileReader(CONFIG_PATH.toFile()), ModConfiguration.class);
                highlightPink = configuration.highlightPink;
                highlightRed = configuration.highlightRed;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
