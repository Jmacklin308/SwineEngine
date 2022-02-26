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

	//constructor
	public Ball(Vector2 position, int size, Vector2 speed)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;

		//create random object
		random = new Random();

		SetRandomColor();
	}

	public void Update()
	{
		float dt = Gdx.graphics.getDeltaTime();
		position.x += speed.x * dt;
		position.y += speed.y * dt;

		if(position.x > Gdx.graphics.getWidth() || position.x < 0) speed.x = -speed.x;
		if(position.y > Gdx.graphics.getHeight() || position.y < 0) speed.y = -speed.y;
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
