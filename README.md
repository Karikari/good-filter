## Good-Filter
##### This is a simple Android library Widget for filtering data

[![](https://jitpack.io/v/Karikari/good-filter.svg)](https://jitpack.io/#Karikari/good-filter)

### SingleFilterWigdet

| Horizontal View        | Vertical View           |
| ------------- |:-------------:| 
| <img src="https://user-images.githubusercontent.com/6484414/60584655-4f64f500-9d7d-11e9-9a09-5234f211fc56.gif" width="400" height="750" />     | <img src="https://user-images.githubusercontent.com/6484414/60586080-cc459e00-9d80-11e9-8987-0562f242a9c0.gif" width="400" height="750" /> |

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
	        implementation 'com.github.Karikari:good-filter:0.0.5'
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
   	  <solid android:color="#FFFFFF" />
   	  <stroke android:width="1dp" android:color="#949BA0"/>
    	  <padding  android:left="10dp" android:right="10dp" android:top="5dp" android:bottom="5dp"/>
    	  <corners android:radius="50dp"/>
   </shape>
   
   The style can also change when an item is selected by using this attribute
   app:selected_background="@drawable/selected_bg"
   res/drawable/selected_bg
   <?xml version="1.0" encoding="utf-8"?>
   <shape xmlns:android="http://schemas.android.com/apk/res/android"
       	  android:shape="rectangle">
   	  <solid android:color="#FFFFFF" />
   	  <stroke android:width="1dp" android:color="#008577"/>
    	  <padding  android:left="10dp" android:right="10dp" android:top="5dp" android:bottom="5dp"/>
    	  <corners android:radius="50dp"/>
   </shape>

  **Available Attributes which can be use to customize the widget**
  app:orientation="horizontal"
  app:textSize="18sp" 
  app:text_color="#423E37" 
  app:selected_text_color="#D81B60"
  app:active_background="@drawable/active_bg
  app:font_family="@string/roboto"  // Save the fonts in the assets folder and pass the fontname
  app:selected_background="@drawable/selected_bg"
```
**Usage**
```java
   SingleFilterWidget singleFilter = findViewById(R.id.pills);
   List<String> items = new ArrayList<>();
   items.add("All");
   items.add("Related");
   items.add("Water");
   items.add("Phones");
   items.add("Coke Bottles");
   items.add("Table and Chair");
   items.add("Love and Sex");
   items.add("Hate and Money");
   items.add("Mercy with Juctice");
   items.add("Football");
   items.add("Legendary");
   items.add("CAF");
   items.add("All be lies");
   
    singleFilter.setItems(items);
    singleFilter.setSelected_valued("All"); //Set a default value. if needed
    singleFilter.setOnFilterChangeListener(new SingleFilterWidget.FilterChangeListener() {
         @Override
         public void onFiltered(String v) {
                
         }
    });
```
### MultiFilterWidget

| Horizontal View        | Vertical View           |
| ------------- |:-------------:| 
| <img src="https://user-images.githubusercontent.com/6484414/60653677-09ba3200-9e3a-11e9-91ec-a4883969d3aa.gif" width="400" height="750" />     | <img src="https://user-images.githubusercontent.com/6484414/60653702-176fb780-9e3a-11e9-94f9-e1dd9b3c7bc0.gif" width="400" height="750" /> |

**Add view to Layout**
```xml
   <com.karikari.goodfilter.MultiFilterWidget
        android:id="@+id/multi_filtter"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:orientation="horizontal"
        app:textSize="18sp" />
```

### MultiFilterWidget with Icons

| Horizontal View        | Vertical View           |
| ------------- |:-------------:| 
| <img src="https://user-images.githubusercontent.com/6484414/60655133-c4e3ca80-9e3c-11e9-84de-d5c9d549a12f.gif" width="400" height="750" />     | <img src="https://user-images.githubusercontent.com/6484414/60655132-c44b3400-9e3c-11e9-9a49-a47e03147a4a.gif" width="400" height="750" /> |

**Add view to Layout**
```xml
   <com.karikari.goodfilter.MultiFilterWidget
        android:id="@+id/multi_filtter"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:orientation="horizontal"
        app:active_icon="@drawable/star_black_hollow"
        app:selected_icon="@drawable/star_black"
        app:textSize="18sp" />

The attribute is the same as the SingleFilterWidget
```
**Usage**

```java
   MultiFilterWidget multiFilterWidget = findViewById(R.id.multi_filtter);
   List<String> items = new ArrayList<>();
   items.add("All");
   items.add("Related");
   items.add("Water");
   items.add("Phones");
   items.add("Coke Bottles");
   items.add("Table and Chair");
   items.add("Love and Sex");
   items.add("Hate and Money");
   items.add("Mercy with Juctice");
   items.add("Football");
   items.add("Legendary");
   items.add("CAF");
   items.add("All be lies");
   
   multiFilterWidget.setItems(items);
   multiFilterWidget.setOnFilterChangeListener(new MultiFilterWidget.FilterChangeListener() {
        @Override
        public void onFiltered(String v) {
             //Log.d(TAG, v);
        }

        @Override
        public void onFiltered(List<String> items) {

        }
  });
```
