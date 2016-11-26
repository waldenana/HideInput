# HideInput

# 使用

## Step 1

- 添加以下代码到 build.gradle

``` groovy
compile 'com.github.anzewei:hideinput:1.0'
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


# License

Copyright 2015 anzewei

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.