package me.thomasleese.satellitegame;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.*;
import com.badlogic.gdx.graphics.g3d.environment.*;
import com.badlogic.gdx.graphics.g3d.utils.*;
import com.badlogic.gdx.math.*;

import me.thomasleese.satellitegame.game.*;

public class SatelliteGame extends ApplicationAdapter {

    private Environment mEnvironment;
    private Camera mCamera;
    private ModelBatch mModelBatch;

    private Model mSkySphereModel;
    private ModelInstance mSkySphereModelInstance;

    private SolarSystem mSolarSystem;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        mModelBatch = new ModelBatch();

        mCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mCamera.position.set(200f, 0f, 0f);
        mCamera.lookAt(0, 0, 0);
        mCamera.near = 1f;
        mCamera.far = 6000f;
        mCamera.update();

        mEnvironment = new Environment();
        mEnvironment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.05f, 0.05f, 0.05f, 1f));
        //mEnvironment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        mEnvironment.add(new PointLight().set(0.9f, 0.9f, 0.9f, 0f, 0f, 0f, 2000f));

        Texture skySphereTexture = new Texture("textures/starmap.jpg");
        Material skySphereMaterial = new Material(TextureAttribute.createDiffuse(skySphereTexture));
        ModelBuilder modelBuilder = new ModelBuilder();
        skySphereMaterial.set(new IntAttribute(IntAttribute.CullFace, 0));
        mSkySphereModel = modelBuilder.createSphere(5000f, 5000f, 5000f, 64, 64, skySphereMaterial, VertexAttributes.Usage.Position | VertexAttributes.Usage.TextureCoordinates);

        mSkySphereModelInstance = new ModelInstance(mSkySphereModel);

        mSolarSystem = new SolarSystem();
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        float dt = Gdx.graphics.getDeltaTime();

        mSolarSystem.step(dt);
        mSolarSystem.focusCamera(mCamera);

        mModelBatch.begin(mCamera);
        mModelBatch.render(mSkySphereModelInstance, mEnvironment);
        mSolarSystem.render(mModelBatch, mEnvironment);
        mModelBatch.end();
    }

    @Override
    public void dispose() {
        mSkySphereModel.dispose();
        mSolarSystem.dispose();
    }

}
