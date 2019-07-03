## Good-Filter
##### This is a simple Android library Widget for filtering data 
<img src="https://user-images.githubusercontent.com/6484414/60584655-4f64f500-9d7d-11e9-9a09-5234f211fc56.gif" width="400" height="750" />

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
