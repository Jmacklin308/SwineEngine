package com.conemangames.swinegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SwineGame extends ApplicationAdapter
{
	ShapeRenderer shape;
	Ball ball;
	int totalNumofBalls = 1000;
	ArrayList<Ball> balls = new ArrayList<>();
	Random random = new Random();

	//for debuging
	FrameRate frameRate;

	//for physics
	World world;
	Box2DDebugRenderer debugRenderer;

	PerspectiveCamera camera;

	public void create()
	{
		frameRate = new FrameRate();
		shape = new ShapeRenderer();
		GenerateBalls(totalNumofBalls, shape);

		//camera setup
		camera = new PerspectiveCamera(70,(float)Gdx.graphics.getWidth(),(float) Gdx.graphics.getHeight());

		//init physics
		Box2D.init();
		world = new World(new Vector2(0,0),true); //vec controls direction of gravity
		debugRenderer = new Box2DDebugRenderer();
	}



	@Override
	public void render()
	{
		//clear background
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//start camera
		camera.update();

		shape.begin(ShapeRenderer.ShapeType.Filled);
			frameRate.update();
			//draw each ball
			for(Ball ball : balls)
			{
				ball.Update();
				ball.Draw(shape);
			}

		shape.end();

		//draw framerate on top
		frameRate.render();

		//update physics debug (must be done before physics step
		debugRenderer.render(world, camera.combined);


		//update physics step
		world.step(1/60f, 6,2);
	}

	private void GenerateBalls(int totalNumofBalls, ShapeRenderer shape)
	{
		for (int i = 0; i < totalNumofBalls; i++)
		{
			//rand properties
			float randX = random.nextInt(Gdx.graphics.getWidth());
			float randY = random.nextInt(Gdx.graphics.getHeight());
			int randSize = random.nextInt(15);
			float randSpeed = (float) random.nextInt(50);

			Color randColor = RandomColor();


			//load up array
			Ball ball = new Ball(new Vector2(randX,randY),randSize,new Vector2(randSpeed,randSpeed));
			balls.add(ball);
		}
	}


	public Color RandomColor()
	{
		random = new Random();
		int r = random.nextInt(70);
		int g = 20;
		int b = 20;
		int a = 100;

		return new Color(r,g,b,a);
	}
}
