# HideInput

# 使用

## Step 1

- 添加以下代码到 build.gradle

``` groovy
compile 'com.github.anzewei.hideinput:hideinput:1.1'
``` 
	
## 用法

``` java
public class LoginActivity extends Activity {


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new InputHelper(this).onPostCreate();
    }

}
```

## 高级用法

### 设置某些View点击后不隐藏键盘

``` java

mInputHelper.setIgnoreView(R.id.email_sign_in_button，R.id.btn_login);

mInputHelper.setIgnoreView(mBtnLogin，mBtnRegist);


```

### 设置点击区域隐藏键盘

``` java
//点击焦点View外面隐藏
mInputHelper.setMode(InputHelper.MODE_OUTSIDE);


//点击焦点View下面隐藏
mInputHelper.setMode(InputHelper.MODE_BOTTOM);

//点击焦点View上面隐藏
mInputHelper.setMode(InputHelper.MODE_TOP);

//点击焦点View上面或者下面隐藏
mInputHelper.setMode(InputHelper.MODE_BOTH);


```

# License

Copyright 2016 anzewei

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
