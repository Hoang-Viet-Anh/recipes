package recipes.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public Gson getGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }
}
