
###GoCoder SDK Data Event Integration

This folder contains sample code demonstrating integration between Wowza Streaming Engine media server software and the Wowza GoCoder SDK for iOS and Android.

For information about extending Wowza Streaming Engine with custom modules, see https://www.wowza.com/docs/how-to-extend-wowza-streaming-engine-using-java.

For this example, you must add the following to the `<Modules>` section of `Application.xml` file in the application's `conf` folder (e.g. `/Library/WowzaStreamingEngine/conf/live/Application.xml`):

```
<Module>
	<Name>ModuleGoCoderSDKSample</Name>
	<Description>ModuleGoCoderSDKSample</Description>
	<Class>com.wowza.gocoder.sdk.module.sample.ModuleGoCoderSDKSample</Class>
</Module>
```
Restart Wowza Streaming Engine for this change to take effect.

Use the provided `build.xml` file to build and deploy the server module with Apache `ant` (http://ant.apache.org/).
