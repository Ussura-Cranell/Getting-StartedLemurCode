package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Command;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.BaseStyles;

public class GuiDemo extends SimpleApplication {

    public static void main( String... args ) {
        GuiDemo main = new GuiDemo();
        main.start();
    }

    @Override
    public void simpleInitApp() {

        // Initialize the globals access so that the defualt
        // components can find what they need.
        GuiGlobals.initialize(this);

        // Load the 'glass' style
        BaseStyles.loadGlassStyle();

        // Set 'glass' as the default style when not specified
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");

        // Create a simple container for our elements
        Container myWindow = new Container();
        guiNode.attachChild(myWindow);

        // Put it somewhere that we will see it
        // Note: Lemur GUI elements grow down from the upper left corner.
        myWindow.setLocalTranslation(300, 300, 0);

        // Add some elements
        myWindow.addChild(new Label("Hello, World."));
        Button clickMe = myWindow.addChild(new Button("Click Me"));
        clickMe.addClickCommands(new Command<Button>() {
            static int clickCount = 0;
            @Override
            public void execute( Button source ) {
                System.out.println("Number of clicks: " + ++clickCount);
            }
        });

        // Other code
        assetManager.registerLocator("src/main/java/com/assets", FileLocator.class);

        float Offset = 5.0f;
        createBox(0.0f, 0.0f, 0.0f);
        createBox(Offset, 0.0f, 0.0f);
        createBox(0.0f, Offset, 0.0f);
        createBox(0.0f, 0.0f, Offset);
    }

    private void createBox(float x, float y, float z, float scaleX, float scaleY, float scaleZ, Material boxMaterial){
        Box boxShape = new Box(scaleX, scaleY, scaleZ);
        Geometry boxGeometry = new Geometry("box", boxShape);
        boxGeometry.move(x, y, z);

        if (boxMaterial==null){
            boxMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            //boxMaterial.setColor("Color", ColorRGBA.Yellow);
            Texture boxTestTexture = assetManager.loadTexture("Textures/test-texture.jpg");
            boxMaterial.setTexture("ColorMap", boxTestTexture);
            boxGeometry.setMaterial(boxMaterial);
        }
        rootNode.attachChild(boxGeometry);
    }

    private void createBox(float x, float y, float z){
        this.createBox(x, y, z, 1.0f, 1.0f, 1.0f, null);
    }
}