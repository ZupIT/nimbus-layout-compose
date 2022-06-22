# **Layout Nimbus Compose**

Nimbus is the codename (or actual name) for the next version of Beagle (Zup's Server Driven UI library).

Nimbus will be first released with support for SwiftUI and Compose, this repo holds the Compose implementation.

## **Requirements**

- Android Studio
- Jdk 11

## **Running the sample project**

1. Cloning the repo
2. open the nimbus-layout-compose folder using Android Studio
3. Select the sample module run on emulator or device.

## **Running the snapshots tests**
1. Create the emulator Nexus 5X with Api 26 (Google Apis), default resolution (1080x1920)
2. Regenerate screenshots for later verification git p```./gradlew executeScreenshotTests -Precord ```
3. Runs verification ```./gradlew executeScreenshotTests ```

## **License**

[**Apache License 2.0**](https://github.com/ZupIT/nimbus-layout-compose/blob/main/LICENSE.txt).

