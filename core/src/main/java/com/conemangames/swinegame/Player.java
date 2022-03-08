package com.conemangames.swinegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.List;

public class Player extends GameObject
{
    private float speed = 5f;
    
    
    public Player() {}
    
    
    public Player(Vector2 inputPosition, float angle)
    {
         this.position = inputPosition;
         this.angle = angle;

       
    }
    
    
    @Override
    public void create()
    {
        //load our texture first
        texture = new Texture("DapperDoggo.png");
        scale = 0.5f;
        

        
        
        super.create();
    }
    
    
    @Override
    public void update(List<GameObject> objects)
    {
        checkInput();
        super.update(objects);
    }
    
    private void checkInput()
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
    
    
    public void setSpeed(float value)
    {
        speed = value;
    }
    
    public float getSpeed()
    {
        return speed;
    }
    
}
