package io.tpd.springbootcucumber.core.driver;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public enum OSType {

    WINDOWS("windows", ".exe"),
    MAC("macos", ""),
    LINUX("linux", "");

    String name;
    String executableExtention;

    @SneakyThrows
    public static OSType detect() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return WINDOWS;
        } else if (osName.contains("nix") || osName.contains("nux")) {
            return LINUX;
        } else if (osName.contains("mac")) {
            return MAC;
        } else {
            throw new Exception("Exception: Number cannot be negative");
        }
    }

}
