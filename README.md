This is a program to test orangetvgo.apk

To the tests I used orange-tv-go.apk version 3.29
Emulated device: Pixel3XL, Android 10,
Instruction how to use it on Windows:
1. Install newest jdk and set environment variable Path to jdk.
2. Install IDE. I recommend Intellij.
3. Install git.
4. Install nodeJS.
5. Install Android Studio.
6. Install Appium.
7. Install Maven.
8. Create emulator of device. I used: Pixel 3XL with Andoid 10 
9. Copy/import repository.
10. Install .apk on emulated device.

How to run tests:
Very important is to have good internet connection. If you are using hotspot from your phone you can heve emulator issues. 
App can ask at the beginig if you really want to use mobile transmission(transfer) and this bother your test scenario. Keep it in mind.
1. Start your appium server. Make sure to have:
   Remote pathset like: /wd/hub
   Host: 0.0.0.0
   Port: 4723
2. Open Android Studio and Run your device.
3. Open imported project in your IDE. In file TestsOrangeTvGo --> in setup method set File app path to apk on your disc.
4. Make sure that capabilities are configurated to your device.
5. Check if you have pom.xml and in dependencies you have maven and testng
6. Open TestsOrangeTvGo and run the programm.
7. Tests should be started. Enjoy your coffe :)
   

