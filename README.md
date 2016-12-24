## Go Ubiquitous <img style="position: center;" src="https://github.com/alex-gru/Nanodegree-P6/blob/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" width="50"> 

***Included Udacity course: [Android Ubiquitous Computing](https://www.udacity.com/course/android-ubiquitous-computing--ud875-nd)***

We live in a world, where everything gets more and more connected. Since it's beginning around 2008, Android has evolved to the generation of "Internet Of Things". It all started with traditional handheld phones, then tablets, Android TVs, Google Cast/Chromecast, even cars are armed with Android Auto, and last but not least, Android Wear. 

Android Wear feels, from the developer's perspective, almost the same as developing for a "standard" handheld device. The interesting part is the communication between the handheld and the wearable device. 

In this project, the famous "Sunshine" weather app, which is the result of the supporting courses [Developing Android Apps](https://www.udacity.com/course/developing-android-apps--ud853) and [Advanced Android App Development](https://www.udacity.com/course/advanced-android-app-development--ud855), should be extended to integrate Wear functionality. The starting code is merged from [this repo, as instructed.](https://github.com/udacity/Advanced_Android_Development/tree/7.05_Pretty_Wallpaper_Time)

Based on a mockup provided, a watchface for an Android Watch is designed. The communication between handheld and wearable is realized, by using a WearableListenerService. The GoogleApiClient provides listener on both sides to react to data transfers. For transmitting data, so called "data items" are used, which are delivery-guaranteed data chunks.

The watchface is designed for both round and square watches. Every Android Wear device operates in two modes, "active" and "ambient". Whenever the user touches the watch face, the watch changes to "active". In this mode, the full design is shown and the screen is redrawn with a high framerate. Ambient mode is automatically started after some seconds, if the user does not actively use the device.
In this mode the screen is redrawn only once per minute and it is important to use low-bit designs to prevent potential display burn-ins with some display technologies. So in the case of "Sunshine", the weather icon, which has large areas of color, is hidden in ambient mode. 

<table>
  <td>
    <img style="position: center;" src="https://github.com/alex-gru/Nanodegree-P6/blob/master/static/screenshots/P6 - Go Ubiquitous_phone_1_framed.png" width="300">
  </td>
  <td>
      <img style="position: center;" src="https://github.com/alex-gru/Nanodegree-P6/blob/master/static/screenshots/P6 - Go Ubiquitous_wear_1_framed_round.png" width="250">
    <img style="position: center;" src="https://github.com/alex-gru/Nanodegree-P6/blob/master/static/screenshots/P6 - Go Ubiquitous_wear_2_framed_round.png" width="250"><br>
      <img style="position: center;" src="https://github.com/alex-gru/Nanodegree-P6/blob/master/static/screenshots/P6 - Go Ubiquitous_wear_1_framed_square.png" width="250">
    <img style="position: center;" src="https://github.com/alex-gru/Nanodegree-P6/blob/master/static/screenshots/P6 - Go Ubiquitous_wear_2_framed_square.png" width="250">
  </td>
</tr>
</table>
