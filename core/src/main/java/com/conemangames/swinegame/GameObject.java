package com.conemangames.swinegame;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
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
    public boolean _static = false;
    public boolean is3d = false;
    protected Vector2 center;
    public Model model;

    public GameObject()
    {
    
    }
    
    public void Create()
    {
        center = new Vector2().setZero();
    }
    
    
    // Ran first
    public void Load()
    {
        CalculateCenter();
    }

    //Called every frame. Update logic here
    public void Update(List<GameObject> objects)
    {
    
    }
    
    //Called every frame. Update draw calls here
    public void Draw(SpriteBatch spriteBatch)
    {
        is3d = false;
        
        spriteBatch.begin();
        if (texture != null && active)
        {
            spriteBatch.draw(
                    texture,
                    position.x,
                    position.y,
                    texture.getWidth()*scale,
                    texture.getHeight()*scale
            );
        }
        spriteBatch.end();

    }

    public void Draw(ModelBatch modelBatch, Camera camera, Environment environment)
    {
        is3d = true;
        modelBatch.begin(camera);
        modelBatch.render(new ModelInstance(model), environment);
        modelBatch.end();
    }
    
    private void CalculateCenter()
    {
        if(texture == null)
            return;
        
        center.x = texture.getWidth() / 2f;
        center.y = texture.getHeight() / 2f;
    }
}
