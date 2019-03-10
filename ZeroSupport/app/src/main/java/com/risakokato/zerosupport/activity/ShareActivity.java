package com.risakokato.zerosupport.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.risakokato.zerosupport.R;

public class ShareActivity extends AppCompatActivity {
    private final int LINE_ID = 0;
    private final int TWITTER_ID = 1;
    private final String[] sharePackages = {"jp.naver.line.android","com.twitter.android"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
    }

    public void tw (View v){
        if(isShareAppInstall(TWITTER_ID)) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setPackage(sharePackages[TWITTER_ID]);
            intent.setType("image/png");
            intent.putExtra(Intent.EXTRA_TEXT, "アプリから共有\n『Zero Support』をDLして、忘れ物を無くそう！\n#App\nhttps://play.google.com/store/apps/details?id=com.risakokato.zerosupport");
            startActivity(intent);
        } else {
            shareAppDl(TWITTER_ID);
        }
    }
    public void line(View v){
        if(isShareAppInstall(LINE_ID)){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("line://msg/text/" + "『Zero Support』をDLして、忘れ物を無くそう！　GooglePlayStore⇨https://play.google.com/store/apps/details?id=com.risakokato.zerosupport"));
            startActivity(intent);
        }else {
            shareAppDl(LINE_ID);
        }
    }

    private Boolean isShareAppInstall(int shareId){
        try {
            PackageManager pm = getPackageManager();
            pm.getApplicationInfo(sharePackages[shareId], PackageManager.GET_META_DATA);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    // アプリが無かったのでGooglePlayに飛ばす
    private void shareAppDl(int shareId){
        Uri uri = Uri.parse("market://details?id="+sharePackages[shareId]);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
