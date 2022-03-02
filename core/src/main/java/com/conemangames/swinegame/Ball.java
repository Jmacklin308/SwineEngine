package com.conemangames.swinegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Ball
{
	Vector2 position;
	int size;
	Vector2 speed;
	Color color;
	Random random;

	//for hitting sides
	Color cRed = new Color(200,0,0,1);
	Color cBlue = new Color(0,0,200,1);
	Color cGreen = new Color(0,200,0,1);
	Color cWhite = new Color(200,200,200,1);

	//constructor
	public Ball(Vector2 position, int size, Vector2 speed)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;

		//create random object
		random = new Random();

		color = Color.CYAN;

	}

	public void Update()
	{
		float dt = Gdx.graphics.getDeltaTime();
		position.x += speed.x * dt;
		position.y += speed.y * dt;

		if(position.x > Gdx.graphics.getWidth() )
		{
			color = cRed;
			speed.x = -speed.x;
		}else if(position.x < 0)
		{
			color = cBlue;
			speed.x = -speed.x;
		}
		if(position.y > Gdx.graphics.getHeight())
		{
			color = cGreen;
			speed.y = -speed.y;
		}else if(position.y < 0) {
			color = cWhite;
			speed.y = -speed.y;
		}
	}


	public void SetRandomColor()
	{
		float r = random.nextFloat();
		float g = random.nextFloat();
		float b = random.nextFloat();
		float a = 100;

		color = new Color(r,g,b,a);
	}


	public void Draw(ShapeRenderer shape)
	{
		shape.circle(position.x,position.y,size);
		shape.setColor(color);
	}
}
