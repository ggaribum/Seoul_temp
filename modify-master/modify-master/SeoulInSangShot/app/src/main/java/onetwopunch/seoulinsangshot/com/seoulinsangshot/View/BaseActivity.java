/*

 Fragment_Detail_Info -> 팁 표시한 채로 탭 이동시 이상함 (Best만)

 */


package onetwopunch.seoulinsangshot.com.seoulinsangshot.View;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Album_DataManager;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Test_DataManager;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.Model.Model_Image;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.Model.Model_Test;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.R;

import static onetwopunch.seoulinsangshot.com.seoulinsangshot.Controller.Constants.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;

public class BaseActivity extends AppCompatActivity {

    public static List<Model_Test> testArr;
    public static ArrayList<Model_Image> imageList;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        intent = new Intent(getApplicationContext(), MainActivity.class);

        testArr = new ArrayList<>();
        imageList = new ArrayList<>();

        Test_DataManager test_dataManager = new Test_DataManager();
        test_dataManager.loadData();
        Album_DataManager album_DataManager = new Album_DataManager();
        album_DataManager.loadData();

        if(!checkPermissionWRITE_EXTERNAL_STORAGE(this)){
            Toast.makeText(this, "권한 허가가 이루어지지 않았습니다. 앱을 완전히 종료하고 다시 실행시켜 주십시오.", Toast.LENGTH_LONG).show();
        } else {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }

    }

    public boolean checkPermissionWRITE_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;

        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    showDialog("외부 저장소", context,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                }
                return false;

            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("접근 권한 허가 필요");
        alertBuilder.setMessage(msg + "에 관한 권한이 필요합니다. \n공유를 원하시면 권한설정을 확인해 주십시오.");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한이 허가되었습니다. 잠시후 앱이 실행됩니다.", Toast.LENGTH_SHORT).show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);
                } else {
                    Toast.makeText(this, "권한 허가가 이루어지지 않았습니다. 앱을 완전히 종료하고 다시 실행시켜 주십시오.", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }

    }

}
