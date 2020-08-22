package co.nilin.kycdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import co.nilin.kycsdk.kyc.api.KYCDataHolder;
import co.nilin.kycsdk.kyc.api.RequestBuilder;
import co.nilin.kycsdk.kyc.api.Result;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = findViewById(R.id.start);
        start.setOnClickListener(v -> {
            Intent intent = new RequestBuilder(this)
                    .withFaceImage()
                    .withFaceCaptcha()
                    .withFaceVideo(3000)
                    .withCard(false)
                    .withSignature()
                    .build();
            startActivityForResult(intent, 33);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Result result = KYCDataHolder.getResult();
    }
}