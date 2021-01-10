# Fyt HW OneKey
App to modify the hardware buttons on the FYT (Joying/FunRover/etc.) units.<br>
This app does not need root!<br>
It should work on all FYT units running on Android 5.1.1 (Sofia), 6.0.1 (Sofia), 8.0 (PX5), 8.1 (SC9853i) and 10.0 (UIS7862).

![logo](https://github.com/hvdwolf/FytHWOneKey/blob/master/images/logo.png)
## Downloads
The apk can be downloaded from the [Releases](https://github.com/hvdwolf/FytHWOneKey/releases) page.

## How does it work?
*(Upon install the package installer will probably mention that it can not check the app, so it thinks that it might not be safe to install.That is of course for you to decide ;)<br>Upon install the app buttons are configured for the apps that are normally started by the button)*<br>
1. Open the "Fyt HW OneKey" App and configure the preferred app by its package name.

**General Configuration screen**<br>
![configurationscreen](https://github.com/hvdwolf/FytHWOneKey/blob/master/images/configurescreen.jpg)
<br>
**Specify the apk by package name**<br>
*(Here in the "Configure the Media Button")*<br>
![specifyappbypackagename](https://github.com/hvdwolf/FytHWOneKey/blob/master/images/specifyappbypackagename.jpg)
<br><br>
2. Exit the app. This is important otherwise the key capture does not work correctly!<br><br>
3. Press the hardware button on your unit.<br><br>
4. The first time you press the hardware button, for example the Media button, it will present you with 2 options: Complete action using "Music Player" or "FYT HW OneKey Media button". If you select the second option, you will get the option "Just Once" and "Always".<br><br>
**First time push of hardware button**<br>
![selectonceoralways](https://github.com/hvdwolf/FytHWOneKey/blob/master/images/selectoncealways.jpg)
<br>If it functions correctly you can select the next time for "Always".<br>
To undo this "standard action/opening", go to Settings -> App Settings. Select the app which now has that default action/opening and reset the "app standard (opening) values" (has a slightly different name on the several Android versions and implementations).
<br><br>
## Deviations from Standard Functionality
**Configure the EQ button**<br>
If you have a unit with hardware buttons, this EQ button is pretty useless for everyday operation. So I made it possible to assign a "Media Key Event" to it, if you don't want to use it for assigning an app to it (default option). This means you can assign next/previous/play/pause/toggle-playpause to it. Especially on a car without steering wheel keys and with hardware buttons on the unit this is really helpful (that is: to me in my car with my unit)
<br>**The ACC_OFF and ACC_ON broadcast events**<br>
*Experimental and partly untested !!*<br>
This "should" allow for actions to be performed when the contact is switched on or off.<br> Acc off should work for media player action, wifi off action (not necessary) and assigned package name.<br> Acc On does certainly not work on Android 8.x as it is not allowed anymore to use that as broadcast event if not a system signed app. On Android 6.0.1, I have no idea.
*ToDo: Check if the USB broadcasts can be used. Upon switching on the unit, the usb devices are connected again. This can be monitored. Of course it requires a usb device connected: whatever usb device*<br><br>

___
**The list installed apps screen**<br>
*(To be able to select your app by its internal package name)*<br>
![selectonceoralways](https://github.com/hvdwolf/FytHWOneKey/blob/master/images/listinstalledapps.jpg)
<br>You can select and copy the "Package Name" into the Settings field of the button you want to re-assign.

