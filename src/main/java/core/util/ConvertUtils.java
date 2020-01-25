package core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

import java.io.IOException;

public abstract class ConvertUtils {

    @NonNull
    public static <T> T convertJsonToPojo(@NonNull String json, Class<T> target) {
        try {
            return new ObjectMapper().readValue(json, target);
        } catch (IOException e) {
            throw new RuntimeException("deserialization failed\n" + e);
        }
    }

}
