package com.framework.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}/src/test/resources/config/dev.properties",
        "file:${user.dir}/src/test/resources/config/staging.properties",
        "file:${user.dir}/src/test/resources/config/uat.properties",
        "file:${user.dir}/src/test/resources/config/prod.properties"
})
public interface FrameworkConfig extends Config {
    @DefaultValue("staging")
    String environment();

    @Key("${environment}.browser")
    String browser();

    @Key("${environment}.timeout")
    int timeout();

    @Key("${environment}.screenshots")
    boolean screenshots();

    @Key("${environment}.videos")
    boolean videos();

    @Key("${environment}.screenshotDirectory")
    String screenshotDirectory();

    @Key("${environment}.videoDirectory")
    String videoDirectory();

    @Key("${environment}.tracingDirectory")
    String tracingDirectory();

    @Key("${environment}.url")
    String url();

    @Key("${environment}.username")
    String username();

    @Key("${environment}.password")
    String password();
}
