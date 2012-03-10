package min3d.sampleProject1;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;
import min3d.vos.Number3d;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * How to load a model from a .obj file
 *
 * @author dennis.ippel
 *
 */
public class ExampleLoadObjFile extends RendererActivity implements SensorEventListener, OnTouchListener {
	private Object3dContainer objModel,bikeModel,envModel;

	@Override
	public void initScene() {
		scene.lights().add(new Light());
		
		//Add the track
		IParser parser = Parser.createParser(Parser.Type.OBJ,
				getResources(), "min3d.sampleProject1:raw/mytrack_obj",true);
		parser.parse();
		Log.d("initScene","Parsed the object");
		objModel = parser.getParsedObject();
		objModel.scale().x = objModel.scale().y = objModel.scale().z = .7f;
		scene.addChild(objModel);
		
		
		//Add the bike
		IParser parser2 = Parser.createParser(Parser.Type.OBJ,
				getResources(), "min3d.sampleProject1:raw/spartan_obj",true);
		parser2.parse();
		bikeModel = parser2.getParsedObject();
		bikeModel.scale().x = bikeModel.scale().y = bikeModel.scale().z = .09f;
		scene.addChild(bikeModel);
		
		//Add the environment
		IParser parser3 = Parser.createParser(Parser.Type.OBJ,
				getResources(), "min3d.sampleProject1:raw/env_obj",true);
		parser3.parse();
		envModel = parser3.getParsedObject();
		envModel.scale().x = envModel.scale().y = envModel.scale().z = 1.0f;
		scene.addChild(envModel);
		//envModel.position().y -= 1;
		
		//Set the camera to a position that is comfortable for playing
		scene.camera().position.y += 1;
		
		//Add lights
		Light light = new Light();
    	scene.lights().add(light);
    	
	}
	
	//@Override
    public boolean onTouch(View v, MotionEvent event) {
            return true;
    }
	
	//@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}
	
	//@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
	}

	@Override
	public void updateScene() {
		bikeModel.position().z -= 0.2;
		scene.camera().position.z = bikeModel.position().z+5;
		scene.camera().target = new Number3d(bikeModel.position().x, bikeModel.position().y, bikeModel.position().z);
		//objModel.rotation().y++;
		//objModel.rotation().z++;
		
	}
}