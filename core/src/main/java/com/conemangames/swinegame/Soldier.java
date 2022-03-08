package com.conemangames.swinegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;

public class Soldier extends GameObject
{
    @Override
    public void create()
    {
        ModelLoader loader = new ObjLoader();
		model = loader.loadModel(Gdx.files.internal("Models/Soldier_Mk2.obj"));
    }
}
