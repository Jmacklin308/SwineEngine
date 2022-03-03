package com.conemangames.swinegame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.List;

public class GameObject
{
    protected Texture texture;
    public Vector2 position;
    public Color color;
    public float scale = 1f;
    public float rotation = 0f;
    public float layerDepth = .5f;
    public boolean active = true;
    protected Vector2 center;
   
    
    public GameObject()
    {
    
    }
    
    public void Initialize()
    {
    
    }
    
    
    //Load the image of the object in question
    public void Load(SpriteBatch spriteBatch)
    {
    
    }

    //access all game objects in game
    public void Update(List<GameObject> objects)
    {
    
    }
    
    public void Draw(SpriteBatch spriteBatch)
    {
    
    }

}
