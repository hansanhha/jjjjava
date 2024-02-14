package com.hansanhha.java;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.testing.Test;

public class JavaConventions implements Plugin<Project> {

    private static final String JUPITER = "org.junit.jupiter:junit-jupiter:5.9.3";
    private static final String PLATFORM = "org.junit.platform:junit-platform-launcher";

    @Override
    public void apply(Project project) {
        project.getPlugins().apply(JavaPlugin.class);
        project.getDependencies().add(JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME, JUPITER);
        project.getDependencies().add(JavaPlugin.TEST_RUNTIME_ONLY_CONFIGURATION_NAME, PLATFORM);

        project.getTasks().withType(Test.class).configureEach(Test::useJUnitPlatform);
    }
}
