package com.conemangames.swinegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Ball
{
	Vector2 position;
	int size;
	Vector2 speed;

	//constructor
	public Ball(Vector2 position, int size, Vector2 speed)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;
	}

	public void Update()
	{
		position.x += speed.x;
		position.y += speed.y;

		if(position.x > Gdx.graphics.getWidth() || position.x < 0) speed.x = -speed.x;
		if(position.y > Gdx.graphics.getHeight() || position.y < 0) speed.y = -speed.y;
	}

	public void Draw(ShapeRenderer shape)
	{
		shape.circle(position.x,position.y,size);
	}
}
