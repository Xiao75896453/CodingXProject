# CodingXProject
## :game_die: 各個檔案的功能： 
### :balloon: Java files sorted with package 
> Activity的java檔，檔名的最後有用Activity命名；會對應到layout資料夾裡的xml檔。其餘的java檔就是一般的class。
> 
**1. MainFunctionsWithBottomBar:**
* HomePageActivity：裝所有fragment的activity
* AddOrViewDataFragment
* SetAlarmFragment （會去叫5. DailyDrugInfoAndAlarmSetting的檔案）
* PeriodDrugFragment
```
    點選navigation bar上不同的選項，會在hompage上顯示不同的fragment
```
* DetailDrugFragment：
   * 點選periodDrugFragment中寫著"全部"的button，會將homePageActivity上的fragment換成DetailDrugFragment
   * 點選fragment上不同的card，會切換到該藥品相關的webView
   
**2. DataRecord:**
   * CustomNumberPicker??可能沒有必要（感覺內建的numberpicker應該也要可以調整大小）
   * DataRecord_BloodPressure_SBP_Activity（收縮壓）
   * DataRecord_BloodPressure_DBP_Activity（舒張壓）
   * DataRecord_Heartbeat_Activity（脈搏）
    
    SBP → DBP → Heartbeat(一次記錄完)
     
* DataRecord_BloodSugar_Activity（血糖）

**3. DetailDrugMessage:**
   * DrugCard：
每筆藥物都是一個card，連到xml檔activity_drug_card。DrugCard中包含id、兩個圖片（裸錠、含外包裝的兩種圖片）、用藥敘述、連到成大該藥品頁面的網址。
    * DrugListAdapter
    * DrugsInfoActivity（現在沒有用到，原本把它寫成一個Activity，後來把它改成fragment→在DetailDrugInfoFragment）
    * DetailDrugActivity：顯示藥品網站頁面的webView（也可以考慮改成呼叫其他手機程式開啟網頁，例如chrome）

**4. LoginRegister:**
 * LoginActivity
 * LoginRequest
 * RegisterActivity
 * RegisterRequest
 * UserAreaActivity
>用php的檔案連接mySQL資料庫，app端只有紀錄user的name, userName, password資料蠻少的可以改掉
    用到volleyAPI，在loginRequest、RegisterRequest裡面放網址的地方。如果要用"自己這一台筆電的模擬器"去連本地的資料庫，要用10.0.2.2:+portNumber+php file的路徑，才不會出現內外網/虛擬或實體IP的問題。用真機或用其他台電腦的模擬器連到本地的資料庫的話，就用ip+portNumber就可以了
只試過用http。https還沒找到方法，好像會失敗。
（app端的資料庫只有寫一點點，可以換掉，要搬的東西不多）
 
**5. DailyDrugInfoAndAlarmSetting:設定吃藥提醒的鬧鐘時間**
   * AlarmReceiver
   * ItemAdapter

**6. Remind:**
  * RemindDrugGetActivity（提醒領藥）
  * RemindRecordActivity（提醒記錄身體數據）
  * RemindTakeMedActivity（提醒用藥）
    * RemindTakeMedCard：
            要吃的藥的圖片，目前是用recycler view去做（橫向2*2的GridView)。
            每個card（每個圖片）有一個boolean值去紀錄是否被點過（點過的話會在圖片上面加陰影filter）。
            長按圖片的話，會彈出dialog window，裡面顯示該card所包含的圖片（但比例會跑掉一些，可以的話可以調整一下）
    * RemindTakeMedCardAdapter：

**7. others:**
   * MainActivity: 為了方便修改放的畫面，方便跳到各個activity
   * SplashActivity: app一開始跑出來的logo頁面
***
### :balloon: Xml layout files:
**1. 登入、註冊畫面：**
* activity_login
* activity_register
* activity_user_area：成功登入後會閃userName和Welcome的畫面

**2. 記錄數據的目前有4個activity：**
  * activity_data_record_blood_pressure_sbp：紀錄收縮壓
  * activity_data_record_blood_pressure_dbp：紀錄舒張壓
  * activity_data_record_heartbeat：紀錄脈搏
  * activity_data_record_blood_sugar：紀錄血糖
  
**3. activity_home_page:homePage的xml檔會顯示下面的4.fragment裡的其中一個**
**4. fragments:**
* fragment_period_drug:顯示各個時段藥物的畫面
* fragment_data_review:檢視數據的畫面
* fragment_set_alarm:設定鬧鐘提醒的畫面
* fragment_drugs_info: 全部藥物清單的畫面
    * activity_drug_card : 藥物清單裡面每個Card的layout
    * activity_detail_drug_info: 點個個card進去後顯示的webView頁面

**5. 其他layout files：**
  * activity_splash：app一打開閃logo的頁面
  * large_drug_pic：跳出提醒用藥頁面長按藥物圖片時會出現地放大圖（這個xml是被裝在跳出來的dialogWindow裡面）
  
**6. res -> Menu:**
  * bottom_nav_menu:home_page下方那條navigation bar的layout file
  
**7. res -> anim:**
  * fade_in:一開始logo畫面以及歡迎使用者畫面的閃爍


**8. res下其他檔案 :**
* ic_view_period_drug, ic_alarm, ic_body_record: navigationbar的圖示
* my_button: 所有button目前套用的background格式
***
### :balloon: 沒用到的Class、xml檔: 
//原本寫的人說可以刪的話就刪掉吧
（目前app上可以跑的已經移到fragment了）
**1. java Class**
* DataReview.java  //一烜

**2. xml檔**
* activity_data_review //一烜
***

### :balloon: Login.php, Register.php:
// $con = mysqli_connect("localhost", "root", "", "loginregister" ); 要改成新的資料庫的 $con
**1. Login.php**
```
<?php
    $con = mysqli_connect("localhost", "root", "", "loginregister" );

    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name, $username, $password);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["name"] = $name;
        $response["username"] = $username;
        $response["password"] = $password;
    }

    echo json_encode($response);
?>

```
**2. Register.php**
```
<?php
    /*$con = mysqli_connect("mysql.cs.ccu.edu.tw", "lcf102u", "lf2netdavid", "lcf102u_test");*/
    $con = mysqli_connect("localhost", "root", "", "loginregister" );
    //check connection
    if (!$con) {
        printf("Connect failed: %s\n", mysqli_connect_error());
        exit();
    }

    $name     = $_POST["name"];
    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "INSERT INTO user (name, username, password) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sss", $name, $username, $password);
    mysqli_stmt_execute($statement);
    printf("%d Row inserted.\n", mysqli_stmt_affected_rows($statement));

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>

```

## :game_die:  目前比較明顯的洞：   
**1. 缺一堆從資料庫抓資料進來的method:**
* 各個period的drugList(固定每天00:00重抓?)
* 抓近期紀錄的數據進來(可能用sharePreference存一定時間一內的數據在手機裡)
 
**2. PeriodDrug從資料庫抓資料進來的的method，放進5個arrayList，之後看哪個使用者點擊哪個時段用recyclerView或ListView 之類的顯示出來**
（就可以不用不用放隱藏不用的List）
**3. 缺UserInfo的class?裡面大概會放一些user相關的att. 和獲得資料的method**
* 紀錄已經量測的次數(血壓/血糖)→判斷是否要再跳出提醒量測身體數據的Activity

**4. 登入後若沒有登出不用重新登入的功能**
**5. 部分資料用sharePreference存起來:** 
* 鬧鐘時間
* 藥品圖片?(因為目前只有糖尿病)
* 紀錄當天已經紀錄身體數據的次數，若已滿則不用再繼續提醒，未滿的話在服藥時間除了吃藥提醒，也在吃藥提醒之後跳出紀錄身體數據的提醒頁面。 
→ 定一個時間，每天的該時間要重新歸零已記錄身數據的次數。
```
 (之後如果做更多或許能根據患者資料庫中紀錄的病症，存相關疾病的藥品圖片進來就好，不用存全部的圖片)
```
**6. fragment的onBackStack問題（要解決按返回鍵畫片出錯的問題）**
**7. 鬧鐘還很不齊全:**
+ 只有部分電腦的模擬器可以跳出鬧鐘（真機目前都不行）
+ 只有第一個鬧鐘會響，後面幾個不會
+ 鬧鐘再提醒（延遲提醒）的功能  //目前還沒有設定延遲時間的entrance 

**8. 現在連到其他網頁在manifest file裡寫有用clear-text，但網路上查到這樣好像是比較不安全的作法**
**9. 設定領藥時間更新（還沒確定要怎麼家時間），下面是幾個想過的做法**
*  做一個手動設定的頁面
*  找個適當的時間，定時更新資料庫中儲存的領藥時間資料
*  從資料庫裡面抓時間下來，存在手機裡，另外也提供一個button，有連線時可以按button更新看看時間有沒有異動

## :game_die: 一些用語
* AC餐前 PC餐後
https://pharmacy.wikia.org/zh-tw/wiki/%E8%99%95%E6%96%B9%E7%94%A8%E8%AA%9E
* 收縮壓SBP(不在100～140，危險) 
舒張壓DBP

## :game_die:  參考過的教學（Android app）
* 連接本地資料庫(用xammp): 
    * `http://10.0.2.2:80/loginRegister/Login.php  (自己的模擬器)
    http://192.168.210.7:80/loginRegister/Login.php  (真機和別人的模擬器)`
        * 要記得加port上去 (http是沒有經security的、https是有經security的)
        * 80是http的、443是https的 
![](https://i.imgur.com/DpOaXJy.png =350x)
 
    * https://stackoverflow.com/questions/25759161/connect-to-the-xampp-localhost-database-with-the-real-device
    * https://stackoverflow.com/questions/5806220/how-to-connect-to-my-http-localhost-web-server-from-android-emulator-in-eclips/5806384#5806384
    * https://developer.android.com/studio/run/emulator-networking.html
    * 讓外部的人也可以進到本地xammp開的dashboard和phpmyadmin:
(apache的config到httpd.config修改require local→require all granted) 
https://stackoverflow.com/questions/21161908/new-xampp-security-concept-access-forbidden-error-403-windows-7-phpmyadmin
        * 打192.168.xx.xx到dashboard
        * 打192.168.xx.xx/phpmyadmin到存放資料的電腦的資料庫
* 系列文章：(感覺很讚)(轉場animation、Design Pattern...等等)
https://medium.com/@evanchen76
* 支持不同的屏幕尺寸:
https://developer.android.com/training/multiscreen/screensizes?hl=zh-cn
* login register:
https://www.youtube.com/watch?v=QxffHgiJ64M&list=PLe60o7ed8E-TztoF2K3y4VdDgT6APZ0ka
* json 連接android php mysql:
    * 如何使用JSON连接Android和PHP Mysql数据库(2012年資料) https://blog.csdn.net/welovesunflower/article/details/7776438
    * How to connect Android app with MySQL database and parse data using JSON and PHP:
http://techoverload.net/android-app-mysql-database/
    * [Android Studio] 連結MySQL - 資料用PHP網頁JSON格式POST抓取
        * http://devildemon20100423.blogspot.com/2018/02/android-studio-mysql-phpjson.html
        * https://www.youtube.com/watch?v=EZshEey2feg
        * https://www.youtube.com/watch?v=6a4fktXb1Gk&feature=youtu.be
        * http://devildemon20100423.blogspot.com/2018/04/adobe-muse-cc-byethostftp.html
* adapters:
https://abhiandroid.com/ui/adapter?fbclid=IwAR3uqdNyVEESS9KWtGaxvAVNLkDIGgxY8JXPa1CDtZT5xMw18e9HMbOKK04
* ViewHolder:
 https://www.cnblogs.com/prescheng/p/5002708.html
* recyclerView介紹：
https://medium.com/@evanhou/%E6%B7%BA%E8%AB%87android-recyclerview-375f9c0eea58
* recycler view & card view:
https://spicyboyd.blogspot.com/2018/03/app-recyclerview-cardview.html
* recyclerView與LayoutManager:
https://www.jianshu.com/p/4b80381e6e80
* Android Web view:
https://developer.android.com/guide/webapps/webview#java
* clearText traffic error in Android9 pie:
https://medium.com/@son.rommer/fix-cleartext-traffic-error-in-android-9-pie-2f4e9e2235e6
* Android Webview自適應螢幕大小:
https://www.itread01.com/content/1548715177.html
https://blog.csdn.net/s
https://blog.csdn.net/xiayu98020214/article/details/50240691uwenlai/article/details/78294459
https://www.itread01.com/p/111985.html
* 抓取手機現在時間: 
https://bibby1101.pixnet.net/blog/post/44759449-%E3%80%8Aandroid%E3%80%8B%E6%8A%93%E5%8F%96%E6%89%8B%E6%A9%9F%E4%B8%8A%E6%99%82%E9%96%93%E8%B3%87%E8%A8%8A
* timePickerDialog:
(浮動時間視窗)
(要用目前activity的名稱.this //用getApplicationContext會出錯)
https://givemepass.blogspot.com/2016/11/timepicker.html
* Fragment教學： ==//ViewPager管理吃掉的資源好像頗重要==
    * ==Android Fragment完全解析，关于碎片你所需知道的一切:==
https://blog.csdn.net/guolin_blog/article/details/8881711
    * ==Android中如何为Fragment中的按钮添加监听事件:==
https://blog.csdn.net/bingjianIT/article/details/51473115
    * ==『Fragment』- 於程式碼中動態操作 Fragment (動態載入):==
https://xnfood.com.tw/fragment02/
    * ==listview - 在 fragment 中，ListView: (接adapter資料進來)==
http://hant.ask.helplib.com/others/post_2234687
    * ==Fragment與Activity間的跳轉:==
https://blog.51cto.com/qht1003077897/1714453
    * ==Fragment系列教學 (--之不推薦fragment)：==
http://nikeru8.blogspot.com/2016/06/fragment-activity-fragment-activity.html
    * Interface』- 利用 Interface 於 MainActivity 中控管 Fragment 與 onBackPressed:
https://xnfood.com.tw/fragment01/
    * Android App中ViewPager與Fragment結合的一些問題解決:
https://codertw.com/android-%E9%96%8B%E7%99%BC/350237/
    * 從fragment中呼叫包含該fragment的activity的method(可用於fragment間的切換):
![](https://i.imgur.com/UOwMnHO.png)
https://stackoverflow.com/questions/12659747/call-an-activity-method-from-a-fragment
https://uiop7890.pixnet.net/blog/post/40874446-%5B%E8%BD%89%E8%B2%BC%5D-call-an-activity-method-from-a-fragment
    * 從activityA跳轉到包含有fragment的activityB，並且要選擇要去哪一個fragment，可以從activityA傳送intent msg給activityB，再根據傳送的msg讓activityB show想要的fragment
(getIntent當不一定有intent過來的時候，要用try catch包起來，不然會有error)
* NumberPicker example: 
https://www.zoftino.com/android-numberpicker-dialog-example
* Alert Dialog
    * https://developer.android.com/guide/topics/ui/dialogs?hl=zh-tw
    *  OnClick PositiveButton: 
https://stackoverflow.com/questions/9596029/onclick-listener-for-ok-button
* Alarm Clock
    * 建立起來拍照才能停的鬧鐘：
https://www.clarifai.com/blog/image-recognition-android-alarm-clock-that-sees
    * 倒數計時器：https://www.youtube.com/watch?v=nvv7cUfveqw
    * 鬧鐘範例: 
https://blog.csdn.net/coder_pig/article/details/49423531
https://www.youtube.com/watch?v=KseXIsTLXaY&list=PL4uut9QecF3DLAacEoTctzeqTyvgzqYwA&index=3
    * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html 
![](https://i.imgur.com/5S9nVUe.png)

* extends AppCompatActivity 去掉actionBar:
https://blog.csdn.net/ouyang_peng/article/details/51334761
