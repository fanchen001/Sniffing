# Sniffing
【次元番】使用的，一个基于webview/x5webview的视频嗅探工具,能准确解析绝大多数手机在线视频网站的视频真实链接。例如:https://www.kankan001.com ， https://www.kankanwu.com   等

基本使用方法请参照[TestActivity](https://github.com/fanchen001/Sniffing/blob/master/app/src/main/java/com/fanchen/sniffingtest/TestActivity.java)或次元番


#添加依赖
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
  
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.fanchen001:Sniffing:1.0.1'
	}
