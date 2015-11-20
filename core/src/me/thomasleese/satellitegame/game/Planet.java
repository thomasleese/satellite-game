package me.thomasleese.satellitegame.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.*;
import com.badlogic.gdx.graphics.g3d.environment.*;
import com.badlogic.gdx.graphics.g3d.utils.*;
import com.badlogic.gdx.math.*;

public class Planet extends Body {

    private static final String TAG = "Planet";

    private float mRadius;

    private Model mModel;
    private ModelInstance mInstance;

    public Planet(Planet primary, float radius, float meanDistanceFromPrimary, String textureName) {
        super(primary, meanDistanceFromPrimary, 1f);

        Gdx.app.debug(TAG, "Created with radius = " + radius + " and altitude = " + meanDistanceFromPrimary);

        mRadius = radius;

        Texture diffuse = new Texture(textureName);
        Material material = new Material(TextureAttribute.createDiffuse(diffuse), new ColorAttribute(ColorAttribute.Emissive));

        float diameter = radius * 2f;

        ModelBuilder modelBuilder = new ModelBuilder();
        mModel = modelBuilder.createSphere(diameter, diameter, diameter, 64, 64, material,
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        mInstance = new ModelInstance(mModel);
    }

    @Override
    public void dispose() {
        mModel.dispose();
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(mInstance, environment);
    }

    @Override
    public void step(float dt) {
        super.step(dt);

        mInstance.transform.setTranslation(mPosition);
    }

}
