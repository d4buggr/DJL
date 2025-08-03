package d3.buggr.config;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.serdes.*;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DJLConfig {

    private Class<? extends OkaeriConfig> ConfigClass;
    private OkaeriConfig ConfigInstance;

    private List<DJLSerializer<?>> serializers = new ArrayList<>();

    public <T extends OkaeriConfig> DJLConfig setConfigClass(Class<T> config) {
        this.ConfigClass = config;
        return this;
    }

    public <T> DJLConfig addSerializer(DJLSerializer<T> serializer) {

        if (serializer == null) {
            throw new IllegalArgumentException("Serializer is null.");
        }

        serializers.add(serializer);
        return this;
    }

    private OkaeriSerdesPack loadSerializers() {

        if (!serializers.isEmpty()) {

            return new OkaeriSerdesPack() {
                @Override
                public void register(@NotNull SerdesRegistry registry) {
                    serializers.forEach(registry::register);
                }
            };

        }

        return null;

    }

    public <T extends OkaeriConfig> DJLConfig setupConfig(String file){

        if (ConfigClass == null) {
            throw new RuntimeException("Please use setConfigClass method first to set the Class.");
        }

        if (OkaeriConfig.class.isAssignableFrom(ConfigClass)) {

                this.ConfigInstance = ConfigManager.create(ConfigClass, (cfg -> {

                    cfg.withBindFile(new File(file + ".yml"));
                    OkaeriSerdesPack serdesPack = loadSerializers();
                    if (serdesPack == null) {
                        cfg.withConfigurer(new YamlSnakeYamlConfigurer());
                    } else {
                        cfg.withConfigurer(new YamlSnakeYamlConfigurer(), serdesPack);
                    }
                    cfg.saveDefaults();
                    cfg.load(true);

                }));

                System.out.println("Config has loaded succesfully");
            return this;
        }

        throw new RuntimeException("Cannot assign okaeri-Config to class.");

    }

    public <T extends OkaeriConfig> T build() {
        return (T) this.ConfigInstance;
    }

    public OkaeriConfig getConfigInstance() {
        return this.ConfigInstance;
    }
}
