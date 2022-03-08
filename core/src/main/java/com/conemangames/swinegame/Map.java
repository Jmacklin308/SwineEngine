package com.conemangames.swinegame;

import com.badlogic.gdx.graphics.Texture;


import java.util.ArrayList;

public class Map
{
    public ArrayList<Wall> walls = new ArrayList<Wall>();
    Texture wallTexture;
    
    //tile parameters for a map
    public int mapWidth = 15;
    public int mapHeight = 9;
    public int tileSize = 128;

}

