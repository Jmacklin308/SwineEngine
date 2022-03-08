package com.conemangames.swinegame;

import com.badlogic.gdx.physics.box2d.*;

public class SwineContactListener implements ContactListener
{
    
    private GameObject parent;
    
    public SwineContactListener(GameObject parent)
    {
        this.parent = parent;
    }
    
    
    
    @Override
    public void beginContact(Contact contact)
    {
        System.out.println("CONTACT");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());
    
    }
    
    @Override
    public void endContact(Contact contact)
    {
    
    }
    
    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {
    
    }
    
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {
    
    }
}
