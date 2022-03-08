package com.conemangames.swinegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
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
	private float accumulator;
	static final float BOX_STEP=1/120f;
	static final int  BOX_VELOCITY_ITERATIONS=8;
	static final int BOX_POSITION_ITERATIONS=3;
	

	//for 3D functionality
	PerspectiveCamera cam;
	CameraInputController camController;
	ModelBatch modelBatch;
	Environment environment;
	
	@Override
	public void create()
	{
		frameRate = new FrameRate();
		spriteBatch = new SpriteBatch();

		objects = new ArrayList<>();

		//box2d world
		world = new World(new Vector2(0,0),true);

		loadLevel();
		
		//3D setup
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f,1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		// camera setup
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(3f, 7f, 10f);
		cam.lookAt(0, 4f, 0);
		cam.update();

		//camera controller
		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
	}

	@Override
	public void render()
	{
		//control camera
		camController.update();

		//clear background
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		//update framerate counter
		frameRate.update();
		
		//call update and draw
		updatePhysics(Gdx.graphics.getDeltaTime());
		updateObjects();
		drawObjects();

		//draw framerate counter on top
		frameRate.render();
	}
	
	private void updatePhysics(float dt)
	{
		accumulator += dt;
		while(accumulator>BOX_STEP)
		{
			world.step(BOX_STEP,BOX_VELOCITY_ITERATIONS,BOX_POSITION_ITERATIONS);
			accumulator-=BOX_STEP;
		}
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
		player.initializePhysics(world, BodyDef.BodyType.DynamicBody,1,2);
		obstacle.initializePhysics(world, BodyDef.BodyType.StaticBody,1,2);
	}

	//load up objects
	public void loadObjects()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).create();
			objects.get(i).load();
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
