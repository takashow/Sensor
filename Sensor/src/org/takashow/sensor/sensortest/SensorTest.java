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
	
	// ����Sensor������
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
		// ��ȡ�����ϵ�TextView���
		etOrientation = (TextView) findViewById(R.id.etOrientation);
		etMagnetic = (TextView) findViewById(R.id.etMagnetic); 
		etTemperature = (TextView) findViewById(R.id.etTemperature);
		etLight = (TextView) findViewById(R.id.etLight);
		etPressure = (TextView) findViewById(R.id.etPressure);
		// ��ȡ�������������
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE); // ��
	}
	@Override
	protected void onResume() {
		super.onResume();
		// Ϊϵͳ���򴫸���ע�������
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), 
				SensorManager.SENSOR_DELAY_GAME);
		// ���ϵͳ������·���
//		mSensorManager.getOrientation(R, values);
		// Ϊϵͳ�ų�������ע�������
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), 
				SensorManager.SENSOR_DELAY_GAME);
		// Ϊϵͳ�¶ȴ�����ע�������
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), 
				SensorManager.SENSOR_DELAY_GAME);
		// Ϊϵͳ���ߴ�����ע�������
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), 
				SensorManager.SENSOR_DELAY_GAME);
		// Ϊϵͳѹ��������ע�������
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
		// ��ȡ����event�Ĵ���������
		int sensorType = event.sensor.getType();
		StringBuilder sb = null;
		// �ж����ĸ������������仯
		switch (sensorType) {
		// ���򴫸���
		case Sensor.TYPE_ORIENTATION:
			 sb = new StringBuilder(); 
			 sb.append("��Z��ת���ĽǶȣ�");
			 sb.append(values[0]);
			 sb.append("\n��X��ת���ĽǶȣ�");
			 sb.append(values[1]);
			 sb.append("\n��Y��ת���ĽǶȣ�");
			 sb.append(values[2]);
			 etOrientation.setText(sb.toString());
			break;
		// �ų�������	
		case Sensor.TYPE_MAGNETIC_FIELD:
			 sb = new StringBuilder(); 
			 sb.append("X��Ÿ�Ӧǿ�ȣ�");
			 sb.append(values[0]);
			 sb.append("\nY��Ÿ�Ӧǿ�ȣ�");
			 sb.append(values[1]);
			 sb.append("\nZ��Ÿ�Ӧǿ�ȣ�");
			 sb.append(values[2]);	
			 etMagnetic.setText(sb.toString());
			 break;
		// �¶ȴ�����
		case Sensor.TYPE_AMBIENT_TEMPERATURE:
			 sb = new StringBuilder(); 
			 sb.append("��ǰ�¶�Ϊ��");
			 sb.append(values[0]);
			 etTemperature.setText(sb.toString());
			break;
		// ���ߴ�����
		case Sensor.TYPE_LIGHT:
			 sb = new StringBuilder(); 
			 sb.append("��ǰ����ǿ��Ϊ��");
			 sb.append(values[0]);
			 etLight.setText(sb.toString());
			break;
		// ѹ��������	
		case Sensor.TYPE_PRESSURE:
			 sb = new StringBuilder(); 
			 sb.append("��ǰѹ��Ϊ��");
			 sb.append(values[0]);
			 etPressure.setText(sb.toString());
			break;
		}
		
	}

	@Override
	// ���������ȸı�ʱ��ص�����
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onPause() {
		// ������ͣʱȡ��ע�ᴫ����������
		mSensorManager.unregisterListener(this);
		super.onPause();
	}
	@Override
	protected void onStop() {
		// ������ֹʱȡ��ע�ᴫ����������.opppppppp
		mSensorManager.unregisterListener(this);
		super.onStop();
	}
}