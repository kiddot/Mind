package com.android.common.push;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.common.BuildConfig;
import com.android.common.base.componet.BaseActivity;
import com.android.tph.api.Client;
import com.android.tph.api.ClientListener;
import com.android.tph.client.ClientConfig;

/**
 * Created by kiddo on 17-9-15.
 */

public class InitPush implements ClientListener{
    private static final String TAG = "InitPush";

    private static InitPush mInitPush = null;
    private InitPush(){}

    public static InitPush getInstance(){
        synchronized (InitPush.class){
               if (mInitPush == null){
                    mInitPush = new InitPush();
               }
        }
        return mInitPush;
    }

    /**
     * 在这里将用户输入的数据，做一层缓存
     * @param allocServer
     * @param userId
     */
    public void initPush(Context context, String allocServer, String userId, String deviceId) {
        //公钥由服务端提供和私钥对应
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCghPCWCobG8nTD24juwSVataW7iViRxcTkey/B792VZEhuHjQvA3cAJgx2Lv8GnX8NIoShZtoCg3Cx6ecs+VEPD2fBcg2L4JK7xldGpOJ3ONEAyVsLOttXZtNXvyDZRijiErQALMTorcgi79M5uVX9/jMv2Ggb2XAeZhlLD28fHwIDAQAB";

        String[] address = allocServer.split(":");
        String serverHost = address[1].substring(2, address[1].length());
        int serverPort = Integer.parseInt(address[2]);
        Toast.makeText(context, serverHost + serverPort, Toast.LENGTH_LONG).show();


        ClientConfig cc = ClientConfig.build()
                .setPublicKey(publicKey)
                .setAllotServer(allocServer)
                .setServerHost(serverHost)
                .setServerPort(serverPort)
                .setDeviceId(deviceId)
                .setClientVersion(BuildConfig.VERSION_NAME)
                .setLogEnabled(BuildConfig.DEBUG)
                //.setSessionStorageDir(MainActivity.class.getResource("/").getFile())
                .setEnableHttpProxy(true)
                .setUserId(userId);
        Push.I.checkInit(context).setClientConfig(cc);
        ClientConfig.I.setClientListener(this);
    }

    @Deprecated
    public String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            String time = Long.toString((System.currentTimeMillis() / (1000 * 60 * 60)));
            deviceId = time + time;
        }
        return deviceId;
    }

    public void startPush(Context context) {
        Push.I.checkInit(context).startPush();
        Log.d(TAG, "startPush: ");
    }

    public void bindUser(String userId) {
        if (!TextUtils.isEmpty(userId)) {
            Push.I.bindAccount(userId, "mpush:" + (int) (Math.random() * 10), "alias");
        }
    }

    @Override
    public void onConnected(Client client) {

    }

    @Override
    public void onDisConnected(Client client) {

    }

    @Override
    public void onHandshakeOk(Client client, int i) {

    }

    @Override
    public void onReceivePush(Client client, byte[] bytes, int i) {

    }

    @Override
    public void onKickUser(String s, String s1) {

    }

    @Override
    public void onBind(boolean b, String s) {

    }

    @Override
    public void onUnbind(boolean b, String s) {

    }
}
