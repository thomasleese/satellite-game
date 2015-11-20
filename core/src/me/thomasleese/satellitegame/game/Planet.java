package me.thomasleese.satellitegame.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.*;
import com.badlogic.gdx.graphics.g3d.environment.*;
import com.badlogic.gdx.graphics.g3d.utils.*;
import com.badlogic.gdx.math.*;

public class Planet extends Body {

    private float mRadius;
    private float mObliquity;
    private float mRotationalSpeed;

    private float mRotation;

    private Model mModel;
    private ModelInstance mInstance;

    public Planet(Body primary, float altitude, float speed, float radius, float obliquity, float rotationalSpeed, String textureName) {
        super(primary, altitude, speed);

        mRadius = radius;
        mObliquity = obliquity;
        mRotationalSpeed = rotationalSpeed;

        mRotation = 0;

        Texture diffuse = new Texture(textureName);
        Material material = new Material(TextureAttribute.createDiffuse(diffuse));

        float diameter = radius * 2f;

        ModelBuilder modelBuilder = new ModelBuilder();
        mModel = modelBuilder.createSphere(diameter, diameter, diameter, 64, 64, material,
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        mInstance = new ModelInstance(mModel);
    }

    @Override
    public void step(float dt) {
        super.step(dt);

        mRotation += dt * mRotationalSpeed;

        mInstance.transform.setTranslation(mPosition);
        //mInstance.transform.setToRotationRad(0, 0, 1, mObliquity); //.rotateRad(0, 1, 0, mRotation);
    }

    public void dispose() {
        mModel.dispose();
    }

    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(mInstance, environment);
    }

}
