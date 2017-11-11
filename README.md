# Completed Assessment Test for Teamvoy

## Description

Create your app with next functionality:
* As a user I can view list of photos with info about their owners and amount of likes
* As a user I can sort this list of photos by latest, oldest, popular
* As a user I can like/unlike photo
* As a user I can view a random photo
Fetch all necessary info from the remote server by the url: https://unsplash.com/developers

### Screenshots

![login screen](/art/screen1.png) ![main screen](/art/screen2.png) ![user info screen](/art/screen3.png) ![random photo screen](/art/screen4.png)

### Third-party libraries used in the app

Dependencies in Gradle

```groovy
dependencies {
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support:recyclerview-v7:25.3.1'
    compile 'com.android.support:cardview-v7:25.3.1'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.android.support:design:25.3.1'
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'com.github.markomilos:paginate:0.5.1'
}
```
