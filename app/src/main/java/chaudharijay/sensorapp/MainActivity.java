package chaudharijay.sensorapp;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView textViewx,textViewy,textViewz;
    SensorManager sm;
    Sensor mySensor;
    ToggleButton btnBomb;
    Sensor gyroSensor;
    Boolean flag=false;
    MediaPlayer mpact,mpdec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewx=(TextView)findViewById(R.id.x_tv);
        textViewy=(TextView)findViewById(R.id.y_tv);
        textViewz=(TextView)findViewById(R.id.z_tv);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        //mySensor=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sm.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);

        btnBomb=(ToggleButton)findViewById(R.id.btnBomb);
        gyroSensor=sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sm.registerListener(this,gyroSensor,SensorManager.SENSOR_DELAY_NORMAL);
        mpact = MediaPlayer.create(this, R.raw.csact);
        mpdec = MediaPlayer.create(this, R.raw.csdec);

        btnBomb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    flag=true;
                }
                else
                {
                    flag=false;
                    mpdec.start();
                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(flag==true) {
            if (event.values[2] > 0.5f) { // anticlockwise
                //getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                        mpact.start();

            } else if (event.values[2] < -0.5f) { // clockwise
                //getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                        mpact.start();
            }
        }
        if(flag==false) {

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}


//https://github.com/zjayn/ContactAppDemo.git