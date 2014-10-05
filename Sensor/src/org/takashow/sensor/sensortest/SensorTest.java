package org.takashow.sensor.sensortest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class SensorTest extends Activity implements SensorEventListener {
	
	// 定义Sensor管理器
	private SensorManager mSensorManager;
	TextView etOrientation;
	TextView etMagnetic;
	TextView etTemperature;
	TextView etLight;
	TextView etPressure;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_test);
		// 获取界面上的TextView组件
		etOrientation = (TextView) findViewById(R.id.etOrientation);
		etMagnetic = (TextView) findViewById(R.id.etMagnetic); 
		etTemperature = (TextView) findViewById(R.id.etTemperature);
		etLight = (TextView) findViewById(R.id.etLight);
		etPressure = (TextView) findViewById(R.id.etPressure);
		// 获取传感器管理服务
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE); // ①
	}
	@Override
	protected void onResume() {
		super.onResume();
		// 为系统方向传感器注册监听器
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), 
				SensorManager.SENSOR_DELAY_GAME);
		// 获得系统方向的新方法
//		mSensorManager.getOrientation(R, values);
		// 为系统磁场传感器注册监听器
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), 
				SensorManager.SENSOR_DELAY_GAME);
		// 为系统温度传感器注册监听器
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), 
				SensorManager.SENSOR_DELAY_GAME);
		// 为系统光线传感器注册监听器
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), 
				SensorManager.SENSOR_DELAY_GAME);
		// 为系统压力传感器注册监听器
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE), 
				SensorManager.SENSOR_DELAY_GAME);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
		// 获取触发event的传感器类型
		int sensorType = event.sensor.getType();
		StringBuilder sb = null;
		// 判断是哪个传感器发生变化
		switch (sensorType) {
		// 方向传感器
		case Sensor.TYPE_ORIENTATION:
			 sb = new StringBuilder(); 
			 sb.append("绕Z轴转过的角度：");
			 sb.append(values[0]);
			 sb.append("\n绕X轴转过的角度：");
			 sb.append(values[1]);
			 sb.append("\n绕Y轴转过的角度：");
			 sb.append(values[2]);
			 etOrientation.setText(sb.toString());
			break;
		// 磁场传感器	
		case Sensor.TYPE_MAGNETIC_FIELD:
			 sb = new StringBuilder(); 
			 sb.append("X轴磁感应强度：");
			 sb.append(values[0]);
			 sb.append("\nY轴磁感应强度：");
			 sb.append(values[1]);
			 sb.append("\nZ轴磁感应强度：");
			 sb.append(values[2]);	
			 etMagnetic.setText(sb.toString());
			 break;
		// 温度传感器
		case Sensor.TYPE_AMBIENT_TEMPERATURE:
			 sb = new StringBuilder(); 
			 sb.append("当前温度为：");
			 sb.append(values[0]);
			 etTemperature.setText(sb.toString());
			break;
		// 光线传感器
		case Sensor.TYPE_LIGHT:
			 sb = new StringBuilder(); 
			 sb.append("当前光线强度为：");
			 sb.append(values[0]);
			 etLight.setText(sb.toString());
			break;
		// 压力传感器	
		case Sensor.TYPE_PRESSURE:
			 sb = new StringBuilder(); 
			 sb.append("当前压力为：");
			 sb.append(values[0]);
			 etPressure.setText(sb.toString());
			break;
		}
		
	}

	@Override
	// 传感器精度改变时间回调方法
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onPause() {
		// 程序暂停时取消注册传感器监听器
		mSensorManager.unregisterListener(this);
		super.onPause();
	}
	@Override
	protected void onStop() {
		// 程序终止时取消注册传感器监听器.opppppppp
		mSensorManager.unregisterListener(this);
		super.onStop();
	}
}