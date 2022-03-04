package com.conemangames.swinegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Map
{
    public ArrayList<Wall> walls = new ArrayList<Wall>();
    Texture wallTexture;
    
    //tile parameters for a map
    public int mapWidth = 15;
    public int mapHeight = 9;
    public int tileSize = 128;
    
    
    public void Load()
    {
        wallTexture = new Texture("blackPixle.png");
    }
    
    public void CheckCollision(Rectangle input)
    {
        for (int i = 0; i < walls.size(); i++) {
            if(walls.get(i) != null && walls.get(i).wall.)
        }
    }
}

