package com.conemangames.swinegame;

import com.badlogic.gdx.maps.objects.RectangleMapObject;


public class Wall
{
    public RectangleMapObject wall;
    public boolean active;
    
    
    public Wall()
    {
    
    }
    
    public Wall(RectangleMapObject inputRectangle)
    {
        wall = inputRectangle;
    }
    
    
    
}
