plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.gt.search"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gt.search"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        val permission = project.findProperty("SECURE_SEARCH_PERMISSION") ?.toString()
            ?: throw GradleException("SECURE_SEARCH_PERMISSION not defined")
        manifestPlaceholders["secureSearchPermission"] = permission
        buildConfigField(
            "String",
            "SECURE_SEARCH_PERMISSION",
            "\"$permission\""
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            keyAlias = "appsync-alias"
            keyPassword = "appsync123"  
            storeFile = file("appsync.keystore") 
            storePassword = "appsync123"  
        }
        getByName("debug").apply {
            keyAlias = "appsync-debug-alias"
            keyPassword = "debug123"  
            storeFile = rootProject.file("appsync.debug.keystore") 
            storePassword = "debug123"  
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs["release"]
            isMinifyEnabled = false  
            isShrinkResources = false  
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            signingConfig = signingConfigs["debug"]
            isMinifyEnabled = false  
            isShrinkResources = false  
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(project(":common")) 
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}