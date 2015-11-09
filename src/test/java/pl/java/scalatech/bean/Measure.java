package pl.java.scalatech.bean;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
@RequiredArgsConstructor
@ToString
public class Measure {
    @Getter
    private final @NonNull String name;
    @Getter
    private final @NonNull Type type;

    
    
}
