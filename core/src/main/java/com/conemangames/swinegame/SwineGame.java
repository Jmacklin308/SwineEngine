package com.conemangames.swinegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SwineGame extends ApplicationAdapter
{
	//for debuging
	FrameRate frameRate;

	//initialization
	public ArrayList<GameObject> objects;
	private SpriteBatch spriteBatch;


	@Override
	public void create()
	{
		frameRate = new FrameRate();
		spriteBatch = new SpriteBatch();

		//initialize list
		objects = new ArrayList<GameObject>();

		LoadLevel();
	}

	@Override
	public void render()
	{
		//clear background
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(255,255,255,1);

		//update framerate;
		frameRate.update();
		UpdateObjects();
		DrawObjects();

		//draw framerate on top
		frameRate.render();
	}

	@Override
	public void dispose()
	{
	}


	public void LoadLevel()
	{
		objects.add(new Player(new Vector2(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f)));

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
		}
	}
}
