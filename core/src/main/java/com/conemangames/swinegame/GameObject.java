package com.conemangames.swinegame;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.List;

public class GameObject
{
    protected Texture texture;
    public Vector2 position;
    public Color color;
    public float scale = 1f;
    public float angle = 0f;
    public float layerDepth = .5f;
    public boolean active = true;
    public boolean _static = false;
    public boolean is3d = false;
    protected Vector2 center;
    public Model model;
    
    //physics
    public Body body;
    private World world;
    public boolean isStatic = false;
    public boolean isKinematic = false;
    public boolean hasFixedRotation = true;
    
    public GameObject()
    {
        //base constructor. good to have for debugging
    }
    
    public void create()
    {
        center = new Vector2().setZero();
        if(world != null) body = createObjectBody();
        
    }
    
    private Body createObjectBody()
    {
        
        Body pbody;
        
        BodyDef def = new BodyDef(); // properties of the body
        
        if(isStatic) def.type = BodyDef.BodyType.StaticBody;
        else if(isKinematic) def.type = BodyDef.BodyType.KinematicBody;
        else def.type = BodyDef.BodyType.DynamicBody;
        
        def.position.set(position);
        def.fixedRotation = hasFixedRotation;
        
        //initialize body
        pbody = world.createBody(def);
        
        //give shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32 /2, 32/2);
        
        body.createFixture(shape, 1.0f);
        
        //clean up
        shape.dispose();
        
        return pbody;
    }
    
    // Ran first
    public void load()
    {
        calculateCenter();
    }

    public void update(List<GameObject> objects)
    {
        //pass
    }
    
    //Called every frame. Update draw calls here
    public void draw(SpriteBatch spriteBatch)
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

    //called every frame on the main thread
    public void draw(ModelBatch modelBatch, Camera camera, Environment environment)
    {
        is3d = true;
        modelBatch.begin(camera);
        modelBatch.render(new ModelInstance(model), environment);
        modelBatch.end();
    }
    
    //called locally
    private void calculateCenter()
    {
        if(texture == null)
            return;
        
        center.x = texture.getWidth() / 2f;
        center.y = texture.getHeight() / 2f;
    }
    
   
}
