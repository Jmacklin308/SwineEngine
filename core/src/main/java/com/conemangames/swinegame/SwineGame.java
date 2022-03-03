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

	//for debuging
	FrameRate frameRate;

	//for physics
	World world;
	Box2DDebugRenderer debugRenderer;

	//camera
	PerspectiveCamera camera;

	public void create()
	{
		frameRate = new FrameRate();
		shape = new ShapeRenderer();

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
		Update();
		Draw();
	}
	
	private void Update()
	{
		
		//update camera
		camera.update();
		
		//update framerate;
		frameRate.update();
		
		
		//update physics step
		world.step(1/60f, 6,2);
	
	}
	
	private void Draw()
	{
		//clear background
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		
		shape.begin(ShapeRenderer.ShapeType.Filled);
		

		shape.end();

		//draw framerate on top
		frameRate.render();

		//update physics debug (must be done before physics step
		debugRenderer.render(world, camera.combined);
		
		
	}
	
}
