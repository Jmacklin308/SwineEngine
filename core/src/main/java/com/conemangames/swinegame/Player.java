package com.conemangames.swinegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class Player extends GameObject
{
    private float speed = 5f;
    
    public Player()
    {
    
    }
    
    
    public Player(Vector2 inputPosition)
    {
        position = inputPosition;
    }
    
    
    @Override
    public void Create()
    {
        super.Create();
    }
    
    @Override
    public void Load()
    {
        //load our texture first
        texture = new Texture("img.png");
        super.Load();
    }
    
    @Override
    public void Update(List<GameObject> objects)
    {
        CheckInput();
        super.Update(objects);
    }
    
    private void CheckInput()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            position.y += speed;
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            position.y -= speed;
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            position.x -= speed;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            position.x += speed;
        
    }
}
