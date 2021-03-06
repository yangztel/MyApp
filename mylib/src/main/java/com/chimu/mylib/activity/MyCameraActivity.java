package com.chimu.mylib.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chimu.mylib.R;
import com.chimu.mylib.util.ToastUtil;
import com.chimu.mylib.widget.CameraPreview;
import com.chimu.mylib.widget.WaterWaveView;

import java.io.IOException;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

/**
 * Created by Longwj on 2017/7/17.
 */

public class MyCameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    WaterWaveView waterWaveView;
    FrameLayout flPreview;
    Button btCapture;

    private CameraPreview mPreview;
    private Camera mCamera;
    private SurfaceView surfaceView;
    private Button btn;
    private SurfaceHolder holder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  //设置全屏
        setContentView(R.layout.activity_camera);

        initCamera();
    }

    /**
     *
     */
    private void initCamera() {
        if (!checkCameraHardware(this)) {
            ToastUtil.showShort(this, "没有相机");
            return;
        }
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
//        mPreview = new CameraPreview(this, mCamera);

        FrameLayout preview = (FrameLayout) findViewById(R.id.fl_preview);
        btn = (Button) findViewById(R.id.bt_capture);

//        mCamera.setPreviewCallback(new Camera.PreviewCallback() {
//            @Override
//            public void onPreviewFrame(byte[] data, Camera camera) {
//                Log.i("msg","原始图片.size:" + (data == null? 0 : data.length));
//            }
//        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自动对焦后拍照
                mCamera.autoFocus(autoFocusCallback);
            }
        });
    }

    /**
     * Check if this device has a mCamera
     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a mCamera
            return true;
        } else {
            // no mCamera on this device
            return false;
        }
    }


    /**
     * 自动对焦 对焦成功后 就进行拍照
     */
    Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {//对焦成功
                camera.takePicture(new Camera.ShutterCallback() {//按下快门
                    @Override
                    public void onShutter() {
                        //按下快门瞬间的操作
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {//是否保存原始图片的信息

                    }
                }, pictureCallback);
            }
        }
    };

    /**
     * 获取图片
     */
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            final Bitmap resource = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (resource == null) {
                Toast.makeText(MyCameraActivity.this, "拍照失败", Toast.LENGTH_SHORT).show();
            }
            final Matrix matrix = new Matrix();
            matrix.setRotate(90);
            final Bitmap bitmap = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
            Log.i("msg", "bitmap.size:" + bitmap.getByteCount());
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCamera.release();//
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();//
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void onInitView() {
        waterWaveView = (WaterWaveView) findViewById(R.id.waterWaveView);
        flPreview = (FrameLayout) findViewById(R.id.fl_preview);
        btCapture = (Button) findViewById(R.id.bt_capture);
    }
}
