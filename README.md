> Important: this project is currently on halt. The latest version is in Beta because it needs more testing. Use it at your own risk. Having said this, Nimbus is still probably the best open source library for implementing server driven ui with Jetpack Compose and Swift. This project is not archived because there is a good chance it will get some investment later this year (2023). If you need to talk to the developers, please send an e-mail to tiago.franca@zup.com.br.

# [**Nimbus Layout Compose**](https://github.com/ZupIT/nimbus-docs/) &middot; [![GitHub license](https://img.shields.io/badge/license-Apache%202.0-blue)](https://github.com/ZupIT/nimbus-layout-compose/blob/main/LICENSE.txt) [![maven version](https://img.shields.io/maven-central/v/br.com.zup.nimbus/nimbus-layout-compose)](https://search.maven.org/artifact/br.com.zup.nimbus/nimbus-layout-compose)

Nimbus Layout Compose is a library for [Nimbus Compose](https://github.com/ZupIT/nimbus-compose) that implements a set of layout components.

To know more about Nimbus SDUI as a whole, please check our [main documentation](https://github.com/ZupIT/nimbus-docs/blob/main/readme.md).

To get started with Nimbus Compose, please check the [documentation for Nimbus Compose](https://github.com/ZupIT/nimbus-docs/blob/main/compose/index.md).

To know more about the Layout Components, please check [the documentation for Nimbus Layout](https://github.com/ZupIT/nimbus-docs/blob/main/layout/index.md).

# Development stage
Nimbus Compose Layout is currently in beta.

# Useful links
- [Introductory article](https://medium.com/p/9a0d95686fd9/): blog post introducing Nimbus SDUI.
- [Documentation](https://github.com/ZupIT/nimbus-docs): the documentation for both the frontend and backend libraries. This is not in a website format yet, but you can read everything through GitHub.
- [Nimbus](https://github.com/ZupIT/nimbus): the common code between Nimbus SwiftUI and Nimbus Compose. This has been built using Kotlin Multiplatform Mobile (KMM).
- [Nimbus Compose](https://github.com/ZupIT/nimbus-compose): all modules necessary to run Nimbus in a Jetpack Compose project.
- [Nimbus SwiftUI](https://github.com/ZupIT/nimbus-swiftui): all modules necessary to run Nimbus in a SwiftUI project.
- [Nimbus Compose Layout](https://github.com/ZupIT/nimbus-layout-compose): layout components for Nimbus Compose.
- [Nimbus SwiftUI Layout](https://github.com/ZupIT/nimbus-layout-swiftui): layout components for Nimbus SwiftUI.
- [Nimbus Backend TS](https://github.com/ZupIT/nimbus-backend-ts): modules for the backend in Typescript.

## Testing (for developers of this lib)
1. Create the emulator Nexus 5X with Api 26 (Google Apis) and default resolution (1080x1920).
2. Regenerate screenshots: ```./gradlew executeScreenshotTests -Precord ```
3. Run tests: ```./gradlew executeScreenshotTests ```

## **License**
[**Apache License 2.0**](https://github.com/ZupIT/nimbus-layout-compose/blob/main/LICENSE.txt).
