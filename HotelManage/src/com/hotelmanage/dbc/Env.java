package com.hotelmanage.dbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Env extends Properties{
private static Env instance=null;

public static Env getInstance(){
	if(instance!=null){
		return instance;
	}else{
		makeInstance();
		return instance;
	}
}

private static synchronized void makeInstance(){
	if(instance==null){
		instance=new Env();
	}
}

private Env(){
	InputStream is=getClass().getResourceAsStream("/dbConnection.Properties");
	try {
		load(is);
	} catch (IOException e) {

		System.out.println("错误,没有读取属性文件");
	}
}
}
