## Install
1. Use mvn command in your terminal to install .aar and .pom files like below:
```bash
$ mvn install:install-file -Dfile=/path/to/aar -DpomFile=/path/to/pom
```


2. Add below code to your top-level ```build.gradle``` file:
```groovy
allprojects {
   repositories {
       google()
       jcenter()
       maven {
           url "/home/<username>/.m2/repository" //Default path to maven’s local repository
       }
   }
}
```
The value of ```url``` inside ```maven{}``` is the default path to maven's local repository. 
Use [this guide](https://www.baeldung.com/maven-local-repository) to find the location on your machine.


3. Add SDK's dependency to your app-level ```build.gradle``` file:
```groovey
dependencies {
    implementation "co.nilin.kycsdk:KYC:1.0-beta04-kycdemo"
}
```
This SDK uses androidx libraries. If you still use the legacy support libraries in your app, you may encounter conflict dependencies. 
It is recommended to migrate to the new androidx. ([androidx migration guide](https://developer.android.com/jetpack/androidx/migrate))


## Usage
Use ```RequestBuilder()``` class to build the request in your app's activity or fragment. 
```RequestBuilder``` class provides you an instance of ```Intent``` which you can use to call SDK's activity and start the process:
```java
import co.nilin.kycsdk.kyc.api.KYCDataHolder;
import co.nilin.kycsdk.kyc.api.RequestBuilder;
import co.nilin.kycsdk.kyc.api.Result;
 
@Override
protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_main);
   start.setOnClickListener(v -> {
       Intent intent = new RequestBuilder(this)
               .withCard(false)  //Start card capture page
               .withFaceVideo(3000)  //Start face video page with 3 seconds video time
               .withFaceImage()  //Start face image page
               .withFaceCaptcha()  //Start face captcha page
               .withSignature()  //Start signature image page
               .toolbar(Color.WHITE, "Title")  //Set a toolbar for activity with color and title
               .textColor(Color.GRAY)  //Set text color of UI components inside activity
               .build();
       startActivityForResult(intent, 33);
   });
}
```

When the process is finished, the result can be obtained using ```KYCDataHolder.getInstance()```.
Resault of each step is available as soon as the step is finished:
```java
@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
   super.onActivityResult(requestCode, resultCode, data);
   if (requestCode == 33)
       Result result = KYCDataHolder.getResult();
}
```
