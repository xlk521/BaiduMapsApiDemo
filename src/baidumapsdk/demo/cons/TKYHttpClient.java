package baidumapsdk.demo.cons;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class TKYHttpClient {
	private final static String TAG = "TKYHttpClient";
	private static HttpClient httpClient;
	private final static int REQUEST_TIMEOUT = 10000;
	private final static int READ_TIMEOUT = 10000;
	private final static String ENCODING = HTTP.UTF_8;
	
	public static String connect(String url, String param) {
		try {
//			if(Constant.ISENCRYPT){
//				try {
//					param = Tools.encode(param);
//					param = DES.encryptDES(param);
//					Tools.Log(TAG, "DES encode text is "+param);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}  
//			}
			
			HttpPost httpRequest = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("json", param));
			httpRequest.setHeader("content-type","application/x-www-form-urlencoded; charset=utf-8");
			httpRequest.addHeader("Accept-Charset", "utf-8");
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpParams my_httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(my_httpParams, REQUEST_TIMEOUT);
			HttpConnectionParams.setSoTimeout(my_httpParams, REQUEST_TIMEOUT);
			DefaultHttpClient mHttpClient = new DefaultHttpClient(my_httpParams);
			BasicHttpResponse httpResponse = (BasicHttpResponse) mHttpClient.execute(httpRequest);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				return EntityUtils.toString(httpResponse.getEntity(), "GB2312");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public static boolean tcpConnect(String param) {
//		boolean state = false;
//		try {
//			byte[] sendByte = ClientApp.instance.getSendBytes(param);
//			Socket socket= new Socket(InetAddress.getByName(Constant.BASE_IP),Constant.TCP_SERVER_PORT); //实例化套按字
//			DataInputStream in=new DataInputStream(socket.getInputStream()); //得到输入流
//			DataOutputStream out=new DataOutputStream(socket.getOutputStream()); //得到输出流
//			socket.setSoTimeout(3000);
//
//				out.write(sendByte);
//				byte[] returnByte = new byte[2];
//				returnByte[0] = in.readByte();
//				returnByte[1] = in.readByte();
//				if(returnByte[0] == Constant.UDPRETURNSUCCESS[0] && returnByte[1] == Constant.UDPRETURNSUCCESS[1]){
//					Tools.Log(TAG, "tcp transform success");
//					state = true;
//				}else{
//					state = false;
//				}
//				socket.close(); //关闭套接字
//		} catch (Exception e) {
//			e.printStackTrace();
//			state = false;
//		}
//		return state;
//	}
//
//	public static boolean download(String url, String saveFile)throws Exception {
//		Tools.Log(TAG, "download;url==" + url);
//		url = URLEncoder.encode(url,"utf-8").replaceAll("\\+", "%20");  
//		url = url.replaceAll("%3A", ":").replaceAll("%2F", "/");  
//		Tools.Log(TAG, "download;after deal url==" + url);
//		HttpGet request = new HttpGet(url);
//		HttpClient httpClient = getHttpClient();
//		HttpResponse response = null;
//		OutputStream os = null;
//		try {
//			response = httpClient.execute(request);
//			Tools.Log(TAG, "download;response==" + response.getStatusLine().getStatusCode());
//			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//				HttpEntity entity = response.getEntity();
//				InputStream in = entity.getContent();
//				File File = new File(saveFile);
//				if (!File.exists()) {
//					File.createNewFile();
//				}else{
//					File.delete();
//					File.createNewFile();
//				}
//
//				byte[] bytes = new byte[1024];
//				os = new FileOutputStream(File);
//				int len = 0;
//				while ((len = in.read(bytes)) != -1) {
//					os.write(bytes, 0, len);
//				}
//				return true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			try {
//				if (os != null) {
//					os.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				return false;
//			}
//		}
//		return false;
//	}
	
	private static synchronized HttpClient getHttpClient() {
		if (httpClient == null || httpClient.getConnectionManager() == null) {
			HttpParams params = new BasicHttpParams();
			ConnManagerParams.setTimeout(params, 2000);
			HttpConnectionParams.setConnectionTimeout(params, REQUEST_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params, READ_TIMEOUT);
			params.setParameter("charset", "UTF-8");


			SchemeRegistry schReg = new SchemeRegistry();
			schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

			ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
					params, schReg);
			httpClient = new DefaultHttpClient(conMgr, params);
		}
		return httpClient;
	}
}