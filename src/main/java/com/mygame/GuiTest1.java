package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.simsilica.lemur.event.DefaultMouseListener;
import com.simsilica.lemur.event.MouseAppState;
import com.simsilica.lemur.event.MouseEventControl;

public class GuiTest1 extends SimpleApplication{

    private final static String TITLE = "TESTING";
    private final static int RESOLUTION_WIDTH = 1200;
    private final static int RESOLUTION_HEIGHT = 800;

    public static void main(String[] args) {

        GuiTest1 app = new GuiTest1();
        app.setSettings(windowSetting());
        app.start();
    }

    @Override
    public void simpleInitApp() {

        flyCam.setMoveSpeed(5.0f);
        cam.setFov(60.0f);

        // инициализация для обработки курсора
        stateManager.attach(new MouseAppState(this));
        // подключение папки asset
        assetManager.registerLocator("src/main/java/com/assets", FileLocator.class);

        // code
        addSpatialListener(createBox("skiagram", 0.0f, 0.0f, 0.0f));
        addSpatialListener(createBox("flabelliform", 3.0f, 0.0f, 0.0f));
        addSpatialListener(createBox("semovedly", 6.0f, 0.0f, 0.0f));
        addSpatialListener(createBox("fantod", 0.0f, 3.0f, 0.0f));
        addSpatialListener(createBox("oxter", 3.0f, 3.0f, 0.0f));
        addSpatialListener(createBox("cosmetology", 6.0f, 3.0f, 0.0f));
    }

    private Geometry createBox(String name, float x, float y, float z, float scaleX, float scaleY, float scaleZ, Material boxMaterial){
        Box boxShape = new Box(scaleX, scaleY, scaleZ);
        Geometry boxGeometry = new Geometry(name, boxShape);
        boxGeometry.move(x, y, z);

        if (boxMaterial==null){
            boxMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            Texture boxTestTexture = assetManager.loadTexture("Textures/test-texture.jpg");
            boxMaterial.setTexture("ColorMap", boxTestTexture);
        }
        boxGeometry.setMaterial(boxMaterial);

        rootNode.attachChild(boxGeometry);
        return boxGeometry;
    }

    private Geometry createBox(float x, float y, float z){
        return this.createBox("moving box", x, y, z, 1.0f, 1.0f, 1.0f, null);
    }
    private Geometry createBox(String name, float x, float y, float z){
        return this.createBox(name, x, y, z, 1.0f, 1.0f, 1.0f, null);
    }

    private static AppSettings windowSetting(){
        AppSettings defaultSetting = new AppSettings(true);
        defaultSetting.setTitle(TITLE);
        defaultSetting.setVSync(true);
        defaultSetting.setResolution(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
        defaultSetting.setFrequency(60);
        return defaultSetting;
    }

    private void addSpatialListener(Spatial spatial){
        MouseEventControl.addListenersToSpatial(spatial, new DefaultMouseListener() {
            static int clickCount = 0;
            @Override
            protected void click(MouseButtonEvent event, Spatial target, Spatial capture ) {
                String button;
                switch (event.getButtonIndex()){
                    case 0:
                        button = "LEFT";
                        break;
                    case 1:
                        button = "RIGHT";
                        break;
                    case 2:
                        button = "MIDDLE";
                        break;
                    default:
                        button = "OTHER";
                        break;
                }
                System.out.println(++clickCount + ": You clicked on the object <" + target.getName().toUpperCase() + "> with the <" + button + "> mouse button");
            }
        });
    }
}
