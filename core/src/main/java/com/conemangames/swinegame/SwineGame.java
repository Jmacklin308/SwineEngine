package com.conemangames.swinegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.BoxShapeBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SwineGame extends ApplicationAdapter
{
	//for debuging
	FrameRate frameRate;

	//initialization
	public ArrayList<GameObject> objects;
	private SpriteBatch spriteBatch;

	//for 3D functionality
	PerspectiveCamera cam;
	CameraInputController camController;
	ModelBatch modelBatch;
	Array<ModelInstance> instances;
	Environment environment;


	//testing 3d
	Model model;
	ModelInstance ground;
	ModelInstance ball;

	@Override
	public void create()
	{
		frameRate = new FrameRate();
		spriteBatch = new SpriteBatch();

		//initialize list
		objects = new ArrayList<GameObject>();

		LoadLevel();

		//3D setup
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f,1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));


		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(3f, 7f, 10f);
		cam.lookAt(0, 4f, 0);
		cam.update();

		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);


		ModelBuilder mb = new ModelBuilder();
		mb.begin();
		mb.node().id = "ground";
		mb.part("box", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, new Material(ColorAttribute.createDiffuse(Color.RED))).box(5f, 1f, 5f);
		mb.node().id = "ball";
		mb.part("sphere", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, new Material(ColorAttribute.createDiffuse(Color.GREEN)))
			.sphere(1f, 1f, 1f, 10, 10);
		model = mb.end();



		ground = new ModelInstance(model, "ground");
		ball = new ModelInstance(model, "ball");
		ball.transform.setToTranslation(0, 9f, 0);

		instances = new Array<ModelInstance>();
		instances.add(ground);
		instances.add(ball);


	}

	@Override
	public void render()
	{
		//control camera
		camController.update();

		//clear background
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		//update framerate;
		frameRate.update();
		UpdateObjects();
		DrawObjects();


		//3d model draw todo: incorporate into game-object class
//		modelBatch.begin(cam);
//		modelBatch.render(instances, environment);
//		modelBatch.end();


		//draw framerate on top
		frameRate.render();
	}

	@Override
	public void dispose()
	{
		modelBatch.dispose();
		model.dispose();
	}


	public void LoadLevel()
	{
		Player player = new Player(new Vector2(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f));
		objects.add(player);
		player.active = false;
		//add soldier
		objects.add(new Soldier());


		LoadObjects();
	}

	//load up objects
	public void LoadObjects()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).Create();
			objects.get(i).Load();
		}
	}


	public void UpdateObjects()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).Update(objects);
		}
	}


	public void DrawObjects()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).Draw(spriteBatch);

			//render 3d if model exist
			if(objects.get(i).model != null)
			{
				objects.get(i).Draw(modelBatch,cam,environment);
			}
		}
	}
}
