## Good-Filter
##### This is a simple Android library Widget for filtering data 

##### SingleFilterWigdet Horizontal
<img src="https://user-images.githubusercontent.com/6484414/60584655-4f64f500-9d7d-11e9-9a09-5234f211fc56.gif" width="400" height="750" />

##### SingleFilterWigdet Vertical
<img src="https://user-images.githubusercontent.com/6484414/60586080-cc459e00-9d80-11e9-8987-0562f242a9c0.gif" width="400" height="750" />

#### How to
**Step 1** Add the JitPack repository to your build file
```gradle
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
**Step 2** Add the dependency
```gradle
   dependencies {
	        implementation 'com.github.Karikari:good-filter:0.0.2'
	}
```
**Add the View to your layout**
```xml
   <com.karikari.goodfilter.SingleFilterWidget
        android:id="@+id/pills"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:orientation="horizontal"
        app:textSize="18sp"
        app:selected_text_color="@color/colorAccent"/>
```
**Customize widget**

```xml
   The widget can be customize by setting the attribute

   app:active_background="@drawable/active_bg"

   res/drawable/active_bg
   <?xml version="1.0" encoding="utf-8"?>
   <shape xmlns:android="http://schemas.android.com/apk/res/android"
       	  android:shape="rectangle">
   	  <solid android:color="@color/white" />
   	  <stroke android:width="1dp" android:color="@color/border_color"/>
    	  <padding  android:left="10dp" android:right="10dp" android:top="5dp" android:bottom="5dp"/>
    	  <corners android:radius="50dp"/>
   </shape>
   
   The style can also change when an item is selected by using this attribute
   app:selected_background="@drawable/selected_bg"
   res/drawable/active_bg
   <?xml version="1.0" encoding="utf-8"?>
   <shape xmlns:android="http://schemas.android.com/apk/res/android"
       	  android:shape="rectangle">
   	  <solid android:color="@color/white" />
   	  <stroke android:width="1dp" android:color="@color/selected_color"/>
    	  <padding  android:left="10dp" android:right="10dp" android:top="5dp" android:bottom="5dp"/>
    	  <corners android:radius="50dp"/>
   </shape>

  **Available Attributes which can be use to customize the widget**
  app:orientation="horizontal"
  app:textSize="18sp" 
  app:text_color="@color/black_olive" 
  app:selected_text_color="@color/colorAccent"
  app:active_background="@drawable/active_bg
  app:font_family="@string/roboto"  // Save the fonts in the assets folder and pass the fontname
  app:selected_background="@drawable/selected_bg"
  
```
