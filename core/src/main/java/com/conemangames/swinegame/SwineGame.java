package com.conemangames.swinegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

/** com.badlogic.gdx.ApplicationListener implementation shared by all platforms. */
public class SwineGame extends ApplicationAdapter
{
	//for debuging
	FrameRate frameRate;

	//initialization
	private ArrayList<GameObject> objects;
	private SpriteBatch spriteBatch;

	//for physics
	private World world;
	private ArrayList<Body> bodies;
	private Box2DDebugRenderer b2dr; //debugging physics

	//for 3D functionality
	OrthographicCamera cam;
	CameraInputController camController;
	ModelBatch modelBatch;
	Environment environment;
	
	@Override
	public void create()
	{
		frameRate = new FrameRate();
		spriteBatch = new SpriteBatch();

		objects = new ArrayList<>();

		//box2d physics
		world = new World(new Vector2(0,0),true);
		bodies = new ArrayList<>();
		b2dr = new Box2DDebugRenderer();
		
		
	
		
		
		
		//load the starter level
		loadLevel();
		
		//3D setup
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f,1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		// camera setup
		cam = new OrthographicCamera();
		cam.position.set(3f, 7f, 10f);
		cam.lookAt(0, 4f, 0);
		cam.update();

		//camera controller
		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
		
		
		//initialize all the physics bodies of objects
		for (int i = 0; i < objects.size(); i++) {
		
		}
	}

	@Override
	public void render()
	{
		//update physics
		world.step(1/60f,6,2);
		
		//control camera
		camController.update();

		//clear background
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		//update framerate counter
		frameRate.update();
		
		//call update and draw
		updateObjects();
		drawObjects();
		
		//debug
		b2dr.render(world,cam.combined);

		//draw framerate counter on top
		frameRate.render();
	}
	
	@Override
	public void resize(int width, int height)
	{
		cam.setToOrtho(false,width/2,height/2);
	}
	
	
	@Override
	public void dispose()
	{
		frameRate.dispose();
		world.dispose();
		b2dr.dispose();
	}
	
	
	// object loading-------------------------------------------------------------------------
	public void loadLevel()
	{
		Player player = new Player(new Vector2(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f),0,0.2f);
		objects.add(player);
		
		Obstacle obstacle = new Obstacle(new Vector2(0,0),0,0.2f);
		objects.add(obstacle);

		//initially load objects at start
		loadObjects();
		
		//add physics bodies to list array
		for (int i = 0; i < objects.size(); i++) {
			bodies.add(objects.get(i).body);
		}
	}

	//load up objects
	public void loadObjects()
	{
		for (GameObject object : objects) {
			object.create();
			object.load();
		}
	}

	public void updateObjects()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).update(objects);
		}
	}

	public void drawObjects()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).draw(spriteBatch);

			//render 3d if model exist
			if(objects.get(i).model != null)
			{
				objects.get(i).draw(modelBatch,cam,environment);
			}
		}
	}
}
