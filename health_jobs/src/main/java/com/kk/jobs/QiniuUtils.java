package com.kk.jobs;

/**
 * @author ：seanyang
 * @date ：Created in 2019/6/10
 * @description ：
 * @version: 1.0
 */

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

/**
 * 七牛云工具类
 */
public class QiniuUtils {
/*	public  static String accessKey = "fhuiPBXFTZIgJdtZGuMtCUJ3zSrT7k3AHmVNE4UA";
	public  static String secretKey = "zKKxCW_HjQmwtl4JI80nMT0Ve2V7VZDbqT4zt_tT";
	public  static String bucket = "itcast_health";*/
	public  static String accessKey = "wLOdEkyD7ktYTqkUQN1RUkbYBWq7FtcXShc7r69x";
	public  static String secretKey = "aKWwz9I9S3LQv1m3G3n2WSl4WC1D1pB9PHcTFCyM";
	public  static String bucket = "dubbo-health";
	/**
	 * 删除文件
	 * @param fileName 服务端文件名
	 */
	public static void deleteFileFromQiniu(String fileName){
		//构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		String key = fileName;
		Auth auth = Auth.create(accessKey, secretKey);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
			bucketManager.delete(bucket, key);
		} catch (QiniuException ex) {
			//如果遇到异常，说明删除失败
			System.err.println(ex.code());
			System.err.println(ex.response.toString());
		}
	}

}
