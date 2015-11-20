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

    private Planet mSun;
    private Planet mEarth;
    private Planet mMoon;
    private Satellite mSatellite1;
    private Satellite mSatellite2;

    @Override
    public void create() {
        mModelBatch = new ModelBatch();

        mCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mCamera.position.set(0f, 0f, 0f);
        mCamera.lookAt(0, 0, 0);
        mCamera.near = 1f;
        mCamera.far = 300f;
        mCamera.update();

        mEnvironment = new Environment();
        mEnvironment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, 0.1f));
        //mEnvironment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        mEnvironment.add(new PointLight().set(0.9f, 0.9f, 0.9f, 0f, 0f, 0f, 500f));

        Texture skySphereTexture = new Texture("textures/starmap.jpg");
        Material skySphereMaterial = new Material(TextureAttribute.createDiffuse(skySphereTexture));
        ModelBuilder modelBuilder = new ModelBuilder();
        skySphereMaterial.set(new IntAttribute(IntAttribute.CullFace, 0));
        mSkySphereModel = modelBuilder.createSphere(250f, 250f, 250f, 64, 64, skySphereMaterial, VertexAttributes.Usage.Position | VertexAttributes.Usage.TextureCoordinates);

        mSkySphereModelInstance = new ModelInstance(mSkySphereModel);

        mSun = new Planet(null, 0, 0, 3f, 0.408407f, 0.1f, "textures/sunmap.jpg");
        mEarth = new Planet(mSun, 18, 0.5f, 2f, 0.408407f, 0.1f, "textures/earthmap.jpg");
        mMoon = new Planet(mEarth, 4, 1f, 0.5f, 0.4f, 0.1f, "textures/moonmap.jpg");
        mSatellite1 = new Satellite(mEarth, 3f);
        mSatellite2 = new Satellite(mEarth, 3f);
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        float dt = Gdx.graphics.getDeltaTime();

        mSun.step(dt);
        mEarth.step(dt);
        mMoon.step(dt);
        mSatellite1.step(dt);
        mSatellite2.step(dt);

        mEarth.focusCamera(mCamera);

        mModelBatch.begin(mCamera);
        mModelBatch.render(mSkySphereModelInstance, mEnvironment);

        mSun.render(mModelBatch, mEnvironment);
        mEarth.render(mModelBatch, mEnvironment);
        mMoon.render(mModelBatch, mEnvironment);
        mSatellite1.render(mModelBatch, mEnvironment);
        mSatellite2.render(mModelBatch, mEnvironment);

        mModelBatch.end();
    }

    @Override
    public void dispose() {
        mSun.dispose();
        mEarth.dispose();
        mMoon.dispose();
        mSkySphereModel.dispose();
        mSatellite1.dispose();
        mSatellite2.dispose();
    }

}
