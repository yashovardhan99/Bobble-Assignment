# Bobble Big Text Android App
## Overview
- A simple Android app built using the alpha release of Jetpack compose.
- Follows MVVM pattern upto a certain limit <sup>[note](#mvvm-pattern-followed)<sup>
- Uses room database for local data persistence.

### Screenshots
Dark Mode | Light Mode
--------- | ---------
![Dark Mode message bubble](App%20Screenshots/individual_bubble_dark.png)|![Light Mode message bubble](App%20Screenshots/individual_bubble_light.png)
![Dark Mode message list](App%20Screenshots/conv_list_dark.png)|![Light Mode message list](App%20Screenshots/conv_list_light.png)
![Dark Mode App](App%20Screenshots/app_ss_1_dark.jpg)|![Light Mode App](App%20Screenshots/app_ss_1_light.jpg)
![Dark Mode App 2](App%20Screenshots/app_ss_2_dark.jpg)|![Light Mode App 2](App%20Screenshots/app_ss_2_light.jpg)
## Included APK
- A release APK is included in the [releases](BobbleBigText/app/release/) folder.
- It is compatible with all devices on API 21 or above. 
- It uses your system's dark theme settings to present a dark or a light theme.

### Install APK on your device:-
- Download the APK on your android device
- Open it using any file manager
- If asked for permissions, please grant access to allow installation of the app
- Once installed, look for Bobble Big Text in your apps

## Building the app
### Requirements
- Android Studio 4.2 Canary 9 [Download](https://developer.android.com/studio/preview)

### Building the app
- Import the project in Android Studio
- Build project using gradle
(This may take some time as dependencies may need to be downloaded)

### Install on device
- Connect your app to your machine using adb
- Press the run button in android studio, selecting your device

## MVVM pattern followed
This app follows the [MVVM pattern](https://developer.android.com/jetpack/docs/guide#connect-viewmodel-repository) as recommended by Google.

However, this app skipped the repository phase and connected the Room database directly to the viewmodel. This can be modified later to support an external database.
