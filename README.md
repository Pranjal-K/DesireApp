Link to APK:
https://drive.google.com/open?id=1eMOFCB1s-AHGm0kJejATjcDCgVzqEWbQ

![1](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-03.png)
![2](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-05.png)
![3](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-06.png)
![4](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-07.png)
![5](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-09.png)
![6](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-10.png)
![7](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-11.png)
![8](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-12.png)
![9](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-13.png)
![10](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-14.png)
![11](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-16%20-%201.png)
![12](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-16.png)
![13](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-17.png)
![14](https://github.com/Pranjal-K/DesireApp/blob/master/Documentation/Screenshot%20from%202019-04-02%2014-58-20.png)

It has smooth zoom-in, zoom-out animation when switched from one fragment to another.

Still in Active Development.

### Installation

* **Gradle**

	Add it in your root build.gradle at the end of repositories:
	```gradle
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	```

	Add the dependency in your app build.gradle
	```gradle
  dependencies {
	        compile 'com.github.shrikanth7698:Custom-Navigation-Drawer:v0.0.1'
	}
	```

* **Maven**

	Add the JitPack repository to your build file
	```gradle
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	```

	Add the dependency
	```gradle
  <dependency>
	    <groupId>com.github.shrikanth7698</groupId>
	    <artifactId>Custom-Navigation-Drawer</artifactId>
	    <version>v0.0.1</version>
	</dependency>
	```
  
### Usage

Drop the Custom Navigation Drawer in your XML layout as is shown below:
```xml
    <pranjal.customnavigationdrawer2.widget.SNavigationDrawer
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/navigationDrawer">
        <FrameLayout
            android:id="@+id/frameLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>

    </pranjal.customnavigationdrawer2.widget.SNavigationDrawer>
```
And then in your Activity or fragment
```java
        //Global Declaration
        SNavigationDrawer sNavigationDrawer;
        Class fragmentClass;
        public static Fragment fragment;
        
        //Inside onCreate()
        
        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        
        //Creating a list of menu Items
        
        List<MenuItem> menuItems = new ArrayList<>();
        
        //Use the MenuItem given by this library and not the default one.
        //First parameter is the title of the menu item and then the second parameter is the image which will be the background of the menu item.
        
        menuItems.add(new MenuItem("News",R.drawable.news_bg));
        menuItems.add(new MenuItem("Feed",R.drawable.feed_bg));
        menuItems.add(new MenuItem("Messages",R.drawable.message_bg));
        menuItems.add(new MenuItem("Music",R.drawable.music_bg));
        
        //then add them to navigation drawer
        
        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass =  NewsFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }
        
        

        //Listener to handle the menu item click. It returns the position of the menu item clicked. Based on that you can switch between the fragments.
        
        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);

                switch (position){
                    case 0:{
                        fragmentClass = NewsFragment.class;
                        break;
                    }
                    case 1:{
                        fragmentClass = FeedFragment.class;
                        break;
                    }
                    case 2:{
                        fragmentClass = MessagesFragment.class;
                        break;
                    }
                    case 3:{
                        fragmentClass = MusicFragment.class;
                        break;
                    }

                }
                
                //Listener for drawer events such as opening and closing.
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening(){

                    }

                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });
```

### Customization


```xml
    <pranjal.customnavigationdrawer2.widget.SNavigationDrawer
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:navigationDrawerBackgroundColor="#151515"
        app:appbarTitleTextColor="@android:color/white"
        app:HamMenuIconTintColor="@android:color/white"
        app:appbarColor="@android:color/black"
        app:primaryMenuItemTextColor="@android:color/white"
        app:secondaryMenuItemTextColor="@android:color/white"
        app:appbarTitleTextSize="7sp"
        app:primaryMenuItemTextSize="7sp"
        app:secondaryMenuItemTextSize="7sp"
        android:id="@+id/navigationDrawer">
        <FrameLayout
            android:id="@+id/frameLayout"
            android:background="@android:color/black"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>

    </pranjal.customnavigationdrawer2.widget.SNavigationDrawer>
      
```
