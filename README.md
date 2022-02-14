# Fyt HW OneKey
App to modify the hardware buttons on FYT units (Atoto, Ebilaen, Ekiy, Farcar, Funrover, Idoing, Isudar, Joying, Junsun, Mekede, NaviFly, Sinosmart, T'eyes, Winca, Zhan, etcera) units.<br>
This app does not need root!<br>
It should work on all FYT units running on Android 5.1.1 (Sofia), 6.0.1 (Sofia), 8.0 (PX5), 8.1 (SC9853i) and Android 10.0 (uis7862 and uis8581) (and fake Android 11).

![logo](https://github.com/hvdwolf/FytHWOneKey/blob/master/images/logo.png)
## Downloads
The apk can be downloaded from the [Releases](https://github.com/hvdwolf/FytHWOneKey/releases) page.

## How does it work?
*(Upon install the package installer will probably mention that it can not check the app, so it thinks that it might not be safe to install.That is of course for you to decide ;)<br>Upon install the app buttons are configured for the apps that are normally started by the button)*<br><br>

**Currently there are 3 options to "start something" with the "Call method"**
 - by package name
 - by intent (package name / intent)
 - by system call (terminal command(s) or script)

### 1. Open the "FytHWOneKey" app.<br>
![main configurescreen](https://github.com/hvdwolf/FytHWOneKey/blob/master/images/configurescreen.jpg)<br><br>
Select for example the BAND option<br>
![configurescreen 2](https://github.com/hvdwolf/FytHWOneKey/blob/master/images/configurescreen-2.jpg)<br><br>
And click the "Call method"<br>
![configurescreen 3](https://github.com/hvdwolf/FytHWOneKey/blob/master/images/configurescreen-3.jpg)<br><br><br>
**by package name:** This is NOT the name of the apk but the internal package name. 
How do you get this "package name"?
 - Use the "List all Installed apps" option in the main screen of the settings. It shows the app icon, the app name and the package name. This package name is what you need. You can select it and copy & paste it into the field.
 - Search in play.google.com for the app you want to start. Say you want to start the navigation app "Magic Earth" and you have selected that one in the play store (in a browser, not the android app). In the address bar you will then see "https://play.google.com/store/apps/details?id=com.generalmagic.magicearth". The bold part behind the "id=" is your package name.

**by intent:** Every app has a "launch" intent to start the app. Some apps can also be started with other intents to immediately start a specific function. Google Search can be started with the launch intent, but you can also start it with the Google Voice search option intent. The Google (Search) package name is "com.google.android.googlequicksearchbox", the specific intent for google voice search is "com.google.android.googlequicksearchbox.VoiceSearchActivity". The combined "String to be used" is therefore "com.google.android.googlequicksearchbox/com.google.android.googlequicksearchbox.VoiceSearchActivity".
Another example is the Joying Bluetooth apk having intents for the dialer (default launch), the call receiver, the bluetooth streaming, the pairing and a few more.

**by system call:** A system call can be a direct (linux) command or a shell script or a binary (to do something).
= Commands can be a single command as in:
 1. "ls -l > /sdcard/some_file.txt" to capture a directory listing to a file
 2. "input keyevent 3" => Go to the Home screen (of the default launcher)
 3. "am start -a android.intent.action.MAIN -c android.intent.category.HOME" => Go to the Home screen (of the default launcher)
 4. "input keyevent 127" => pause active media player (any media player)
 5. "input key event 126" => (re)start last used media player.
 6. "am start com.syu.radio/com.syu.radio.Launch" => Start the radio app with the default launch intent (or better use "by package name": com.syu.radio; Or use "by intent": com.syu.radio/com.syu.radio.Launch)
 
 
### 2. Exit the app. This is important otherwise the key capture does not work correctly!<br><br>
### 3. Press the hardware button on your unit.<br><br>
### 4. The first time you press the hardware button, for example the Media button, it will present you with 2 options: Complete action using "Music Player" or "FYT HW OneKey Media button". If you select the second option, you will get the option "Just Once" and "Always".<br><br>
**First time push of hardware button**<br>
![selectonceoralways](https://github.com/hvdwolf/FytHWOneKey/blob/master/images/selectoncealways.jpg)
<br>If it functions correctly you can select the next time for "Always".<br>
To undo this "standard action/opening", go to Settings -> App Settings. Select the app which now has that default action/opening and reset the "app standard (opening) values" (has a slightly different name on the several Android versions and implementations).
<br><br>
## Deviations from Standard Functionality
**Configure the EQ button**<br>
If you have a unit with hardware buttons, this EQ button is pretty useless for everyday operation. So I made it possible to assign a "Media Key Event" to it, if you don't want to use it for assigning an app to it (default option). This means you can assign next/previous/play/pause/toggle-playpause to it. Especially on a car without steering wheel keys and with hardware buttons on the unit this is really helpful (that is: to me in my car with my unit)
<br>**The ACC_OFF and ACC_ON broadcast events (Only for Android 7 or Android lower versions)**<br>
*Experimental and partly untested !!*<br>
This "should" allow for actions to be performed when the contact is switched on or off.<br> Acc off should work for media player action, wifi off action (not necessary) and assigned package name.<br> Acc On does certainly not work on Android 8.x and higher as it is not allowed anymore to use that as broadcast event if not a system signed app.
<br><br>

___
**The list installed apps screen**<br>
*(To be able to select your app by its internal package name)*<br>
![selectonceoralways](https://github.com/hvdwolf/FytHWOneKey/blob/master/images/listinstalledapps.jpg)
<br>You can select and copy the "Package Name" into the Settings field of the button you want to re-assign.

