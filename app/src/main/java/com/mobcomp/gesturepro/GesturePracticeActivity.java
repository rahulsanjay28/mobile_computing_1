package com.mobcomp.gesturepro;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class GesturePracticeActivity extends AppCompatActivity {

    private static final int REQUEST_VIDEO_CAPTURE = 1;
    private static final int MEDIA_TYPE_IMAGE = 0;
    private static final int MEDIA_TYPE_VIDEO = 1;
    private static final String TAG = GesturePracticeActivity.class.getSimpleName();
    private static final String IMAGE_DIRECTORY_NAME = "GesturePractice";
    private static int practiceNumber = 0;
    private TextView uploadButton, recordButton;
    private Uri savedVideoUri;
    private String SERVER_PATH = "http://192.168.43.202:8000";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_practice);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        uploadButton = findViewById(R.id.upload_button);
        recordButton = findViewById(R.id.record_button);

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                    practiceNumber++;
                    Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    takeVideoIntent.putExtra(android.provider.MediaStore.EXTRA_VIDEO_QUALITY, 0);
                    takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
                    savedVideoUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
                    Log.i(TAG, savedVideoUri.toString());
                    takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, savedVideoUri);
                    if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takeVideoIntent,
                                REQUEST_VIDEO_CAPTURE);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "No Camera on this device",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Uploading", Toast.LENGTH_SHORT).show();
                String pathToStoredVideo = getRealPathFromURIPath(savedVideoUri, GesturePracticeActivity.this);
                uploadVideoToServer(pathToStoredVideo);
                Intent intent = new Intent(GesturePracticeActivity.this, GestureSelectActivity.class);
                startActivity(intent);
            }
        });
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK){
            Log.i(TAG, "onActivityResult");
        }
    }

    public Uri getOutputMediaFileUri(int type) {
        Log.i(TAG, Uri.fromFile(getOutputMediaFile(type)).toString());
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "GESTURE_PRACTICE_" + practiceNumber + "_AVVARU" + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    private void uploadVideoToServer(String pathToVideoFile){

        Map<String, RequestBody> map = new HashMap<>();

        File videoFile = new File(pathToVideoFile);
        Log.i(TAG, "Rahulsan " + videoFile.exists());
        RequestBody videoBody = RequestBody.create(MediaType.parse("*/*"), videoFile);
        RequestBody groupId = RequestBody.create(MediaType.parse("text/plain"), "24");
        RequestBody asuId = RequestBody.create(MediaType.parse("text/plain"), "1215133127");
        map.put("file\"; filename=\"" + videoFile.getName() + "\"", videoBody);
        map.put("group_id", groupId);
        map.put("id", asuId);

//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        VideoInterface vInterface = retrofit.create(VideoInterface.class);
        Call<ResponseBody> serverCom = vInterface.uploadVideoToServer(map, 1);
        serverCom.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, "onResponse " + response);
                Toast.makeText(GesturePracticeActivity.this, "Upload Successful", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "Error message " + t.getMessage());
            }
        });
    }
}
