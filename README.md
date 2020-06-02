# Connectivity

Connectivity is written in Kotlin using Android Architecture Component to easily capture network connectivity events, i.e. online/offline. This library is simple to use and have:

  - ConnectivityView to show online/offline status in your XML
  - Register callback to get connection change events
  - Simple to use

## Installation

##### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```sh
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
	
##### Step 2. Add the dependency
Add it in build.gradle of your app:
```sh
dependencies {
	   implementation 'com.github.GeekySingh:Connectivity:1.0'
}
```

##### Step 3. Press the gradle sync button


## Usage

Initialize Connectivity library by using following code in your Application class:

```sh
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // initialize connectivity library
        Connectivity.bind(this)
    }
}
```

And define your MyApplication class in AndroidManifest.xml file

```sh
<application
        android:name=".MyApplication"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:theme="@style/AppTheme">
        ...
        ...
        ...
</application>
```

Now, to use ConnectivityView in your view, use below code:

```sh
    <com.gappscorp.connectivity.ConnectivityView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
	app:offlineText="@string/your_offline_message"
        app:onlineText="@string/your_online_message" />
```

Use app:offlineText and app:onlineText attributes in case if you wish to provide your own message.

And you are DONE. This view will automatically capture network events and will appear when there is no network connection. Once you got Internet connection back, it will show that you are online and will hide itself.
##### ConnectivityView is lifecycle aware view, so it will automatically register/unregister network event listener when this view is attached/detached.



## Capture Network Events

In case, if you want to capture network events and do some work (show/hide/disable UI components), use below code:

#### Implement ConnectionListener

```sh
class MainActivity : AppCompatActivity(), ConnectionListener {
    override fun onConnectivityChanged(connected: Boolean) {
        // connected is true for online and false for offline
    }
}
```

#### Register Network Events

```sh
override fun onResume() {
        super.onResume()
        Connectivity.addConnectionListener(this)
}
```

#### Unregister Network Events

```sh
override fun onPause() {
        super.onPause()
        Connectivity.removeConnectionListener(this)
}
```


#### Screenshot

![](https://github.com/GeekySingh/Connectivity/blob/master/screenshots/connectivity_change.gif)


### Projects using Connectivity
 - #### [BeFilmy](https://play.google.com/store/apps/details?id=com.gappscorp.befilmy)



### Development

Want to contribute? Great!
Create a pull request and we will reveiew and merge.


## License
This library is published under Apache License version 2.0 which you can see [here](https://github.com/GeekySingh/Connectivity/blob/master/LICENSE).
