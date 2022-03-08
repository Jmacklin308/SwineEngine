package com.conemangames.swinegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Obstacle extends GameObject
{


    public Obstacle()
    {
        //for debugging
    }
    
    
    public Obstacle(Vector2 inputPosition, float angle, float scale)
    {
         this.position = inputPosition;
         this.angle = angle;
         this.scale = scale;
    }
    

    @Override
    public void create()
    {
        texture = new Texture(Gdx.files.internal("img_1.png"));
        super.create();
    }
    
    


}
