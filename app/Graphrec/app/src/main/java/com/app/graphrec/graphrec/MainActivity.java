package com.app.graphrec.graphrec;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * The main activity class.
 * @author gustav
 */

public class MainActivity extends AppCompatActivity {

    // Intent handles
    static final int CAMERA_ACTIVITY = 1;

    /**
     * Initialize activity, set up layout and UI.
     * @param savedInstanceState If activity is re-initialized then this contains data it supplied to onSaveInstanceState, otherwise null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * This is fired when startActivityForResult(Intent, int) is done.
     * @param requestCode Specifies who the result came from
     * @param resultCode Specifies the status of its child activity through its setResult
     * @param data An intent with returned data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_ACTIVITY && resultCode == RESULT_OK) {
            Uri imageUri = Uri.parse(data.getStringExtra("picture"));
            invokeResultActivity(imageUri);
        }
    }

    /**
     * Created a new camera activity and starts it.
     * @param view THe current view
     */
    public void invokeCameraIntent(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivityForResult(intent, CAMERA_ACTIVITY);
    }

    private void invokeResultActivity(Uri uri) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("main", true);
        intent.putExtra("picture", uri.toString());
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

}
