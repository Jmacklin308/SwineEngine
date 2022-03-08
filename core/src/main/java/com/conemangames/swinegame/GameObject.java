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
    static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_WORLD_TO = 100f;
	static final float BOX_STEP=1/120f;
	static final int BOX_VELOCITY_ITERATIONS = 8;
	static final int BOX_POSITION_ITERATIONS = 3;
    float accumulator;
	private Body body;

    public GameObject()
    {
        //base constructor. good to have for debugging
    }
    
    public void create()
    {
        center = new Vector2().setZero();
    }
    

    // Ran first
    public void load()
    {
        calculateCenter();
    }

    //Called every frame. Update logic here
    public void update(List<GameObject> objects)
    {
        //update logic here
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

    public void draw(ModelBatch modelBatch, Camera camera, Environment environment)
    {
        is3d = true;
        modelBatch.begin(camera);
        modelBatch.render(new ModelInstance(model), environment);
        modelBatch.end();
    }
    
    private void calculateCenter()
    {
        if(texture == null)
            return;
        
        center.x = texture.getWidth() / 2f;
        center.y = texture.getHeight() / 2f;
    }
    
    
    
    public void initializePhysics(World world,BodyDef.BodyType bodyType,float density, float restitution)
    {
        if(texture == null)
            return;
        
        
        createBody(world,position,angle);
        makeRectFixture(texture.getWidth(), texture.getHeight(),bodyType,density,restitution,position,angle);
    }
    
    
    
    //Physics setup-------------------------------
    private void createBody(World world, Vector2 pos, float angle)
	{
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(convertToBox(pos.x), convertToBox(pos.y));
		bodyDef.angle = angle;
		body = world.createBody(bodyDef);
	}
    
    
    //creates fixture and applies it to the objects body
    private void makeRectFixture(float width, float height, BodyDef.BodyType bodyType, float density, float restitution, Vector2 pos, float angle)
	{
		PolygonShape bodyShape = new PolygonShape();
		float w = convertToBox(width/2f);
		float h = convertToBox(height/2f);
		bodyShape.setAsBox(w,h);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.shape = bodyShape;
		
		body.createFixture(fixtureDef);
		bodyShape.dispose();
	}
    
    
    private float convertToBox(float x)
	{
    	return x*WORLD_TO_BOX;
	}
 
    
}
