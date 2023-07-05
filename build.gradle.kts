import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar


plugins {
    idea
    id("io.spring.dependency-management")
    id("org.springframework.boot") apply false
    id("com.github.johnrengelman.shadow")
}

idea {
    project {
        languageLevel = IdeaLanguageLevel(17)
    }
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

allprojects {
    group = "ru.otus"

    repositories {
        mavenLocal()
        mavenCentral()
    }

    var guava = "11.0.2"

    apply(plugin = "io.spring.dependency-management")
    dependencyManagement {
        dependencies {
            imports {
                mavenBom(BOM_COORDINATES)
            }
            dependency("com.google.guava:guava:$guava")
        }
    }
}

subprojects {
    plugins.apply(JavaPlugin::class.java)
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.addAll(listOf("-Xlint:all,-serial,-processing", "-Werror"))
    }

    tasks.withType<ShadowJar> {
        archiveBaseName.set("shadowJar_hw01_gradle")
        archiveVersion.set("SNAPSHOT-0.0.1")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.Main"))
        }
    }
}