package com.conemangames.swinegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class swineGame extends ApplicationAdapter
{

	//Draw our ball
	ShapeRenderer shape;
	Ball ball;
	int totalNumofBalls = 10;
	ArrayList<Ball> balls = new ArrayList<>();
	Random random = new Random();


	//for debuging
	FrameRate frameRate;

	public void create()
	{
		frameRate = new FrameRate();

		shape = new ShapeRenderer();
		for (int i = 0; i < totalNumofBalls; i++)
		{
			//rand properties
			float randX = random.nextInt(Gdx.graphics.getWidth());
			float randY = random.nextInt(Gdx.graphics.getHeight());
			int randSize = random.nextInt(15);
			float randSpeed = (float) random.nextInt(5);

			//load up array
			Ball ball = new Ball(new Vector2(randX,randY),randSize,new Vector2(randSpeed,randSpeed));
			balls.add(ball);
		}

	}

	@Override
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shape.begin(ShapeRenderer.ShapeType.Filled);

		frameRate.update();
		frameRate.render();

		//draw each ball
		for(Ball ball : balls)
		{
			ball.Update();
			ball.Draw(shape);
		}

		shape.end();
	}
}