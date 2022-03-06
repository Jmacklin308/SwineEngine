package com.conemangames.swinegame;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

public class Cam
{
    private ModelBatch modelBatch;
    private Environment environment;
    private Camera camera;
    private CameraInputController cameraInputController;



    public Cam(ModelBatch modelBatch, Environment environment, Camera camera, CameraInputController cameraInputController)
    {
        this.modelBatch = modelBatch;
        this.environment = environment;
        this.camera = camera;
        this.cameraInputController = cameraInputController;
    }

    public void create()
    {

    }


    public ModelBatch getModelBatch()
    {
        return modelBatch;
    }

    public void setModelBatch(ModelBatch modelBatch)
    {
        this.modelBatch = modelBatch;
    }

    public Environment getEnvironment()
    {
        return environment;
    }

    public void setEnvironment(Environment environment)
    {
        this.environment = environment;
    }

    public Camera getCamera()
    {
        return camera;
    }

    public void setCamera(Camera camera)
    {
        this.camera = camera;
    }

    public CameraInputController getCameraInputController()
    {
        return cameraInputController;
    }

    public void setCameraInputController(CameraInputController cameraInputController)
    {
        this.cameraInputController = cameraInputController;
    }

}
