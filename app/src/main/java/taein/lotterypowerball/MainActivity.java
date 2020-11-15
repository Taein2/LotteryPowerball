package taein.lotterypowerball;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    static int powerNum = 0;
    private final String dbName = "data";
    private final String tabName = "tables";
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView time = (TextView)findViewById(R.id.timeT);
        TextView tv1 = (TextView)findViewById(R.id.num1);
        TextView tv2 = (TextView)findViewById(R.id.num2);
        TextView tv3 = (TextView)findViewById(R.id.num3);
        TextView tv4 = (TextView)findViewById(R.id.num4);
        TextView tv5 = (TextView)findViewById(R.id.num5);
        TextView tv6 = (TextView)findViewById(R.id.num6);
        LottieAnimationView ani = findViewById(R.id.ani);

        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        time.setText(dateTime);

        int[] num = new int[5];
        ani.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                ani.playAnimation();
                for (int i = 0; i < num.length; i++) {        //5번
                    powerNum = (int) (Math.random() * 26 + 1);
                    num[i] = (int) (Math.random() * 69 + 1);
                    for (int j = 0; j < i; j++) {      // 0 , 1 ,2, 3, 4번
                        if (num[i] == num[j]) {
                            i--;
                        }
                    }
                }
                tv1.setText(Integer.toString(num[0]));
                tv2.setText(Integer.toString(num[1]));
                tv3.setText(Integer.toString(num[2]));
                tv4.setText(Integer.toString(num[3]));
                tv5.setText(Integer.toString(num[4]));
                tv6.setText(Integer.toString(powerNum));
            }
        });

        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-1424326537903507/2515737142");
        //ca-app-pub-1424326537903507~1852218706
        //ca-app-pub-1424326537903507/2515737142

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}