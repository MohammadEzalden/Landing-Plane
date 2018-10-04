package sample;


import entities.*;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LwjglMain implements Runnable{




    @Override
    public void run()   {

        DisplayManager.createDisplay();
        Loader loader = new Loader();

        TexturedModel destroyedplane = new TexturedModel(OBJLoader.loadObjModel("destroyed",loader),
                new ModelTexture(loader.loadTexture("azo")));
        TexturedModel TexturedModelplane = new TexturedModel(OBJLoader.loadObjModel("Plane6",loader),
                new ModelTexture(loader.loadTexture("azo1")));
        TexturedModel TexturedModelwind1 = new TexturedModel(OBJLoader.loadObjModel("wing3",loader),
                new ModelTexture(loader.loadTexture("1133")));
        TexturedModel TexturedModelwind2 = new TexturedModel(OBJLoader.loadObjModel("wing4",loader),
                new ModelTexture(loader.loadTexture("1133")));
        /****         Elevators **/
        TexturedModel TexturedModelElevators = new TexturedModel(OBJLoader.loadObjModel("elevators",loader),
                new ModelTexture(loader.loadTexture("1133")));
        TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel",loader),
                new ModelTexture(loader.loadTexture("grassTexture")));
        TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern",loader),
                new ModelTexture(loader.loadTexture("fern")));
        TexturedModel tree=new TexturedModel(OBJLoader.loadObjModel("tree",loader),
                new ModelTexture(loader.loadTexture("tree")));
        grass.getTexture().setHasTransparency(true);
        grass.getTexture().setUseFakeLighting(true);
        fern.getTexture().setHasTransparency(true);


        /********************************/
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));

        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("bm1"));
        TerrainTexture blendMap2 = new TerrainTexture(loader.loadTexture("bm4"));
        /*******************************/
        List<Terrain>terrains=new ArrayList<>();
        terrains.add(new Terrain(3, 7, loader, texturePack, blendMap2));
        for(int i=6;i>2;i--) {
            terrains.add(new Terrain(3, i, loader, texturePack, blendMap));
        }
        for(int i=2;i>-10;i--){
            terrains.add(new Terrain(3, i, loader, texturePack, blendMap2));
        }

        Light light=new Light(new Vector3f(2785,2000,3000),new Vector3f(1,1,1));

        MasterRenderer renderer=new MasterRenderer();

        List<Entity> entities=new ArrayList<>();
        Random random=new Random();
        float randomx,randomz;
        for(int i=0;i<900;i++){

            randomx=random.nextFloat()*800+2400;
            randomz=random.nextFloat()*7000;

            if(randomx<2695||randomx>2935)
            entities.add(new Entity(tree,new Vector3f(randomx,0,randomz),0,0,0,3f));
            randomx=random.nextFloat()*800+2400;
            randomz=random.nextFloat()*7000;
            if(randomx<2750.0||randomx>2835.0)
            entities.add(new Entity(grass,new Vector3f(randomx,0,randomz),0,0,0,1f));
            randomx=random.nextFloat()*800+2400;
            randomz=random.nextFloat()*7000;
            if(randomx<2750.0||randomx>2835.0)
            entities.add(new Entity(fern,new Vector3f(randomx,0,randomz),0,0,0,0.6f));
        }
        //209.5
        Plane destroyed_plane=new Plane(destroyedplane,new Vector3f(2785,100,6000),0,180,0,0.1f);
        Plane plane=new Plane(TexturedModelplane,new Vector3f(2785,100,6000),0,180,0,450f);
        Wing wing1=new Wing(TexturedModelwind1,new Vector3f(2780,10,0),10.900005f,180,0,1.5f);
        Wing wing2=new Wing(TexturedModelwind2,new Vector3f(2780,10,0),10.900005f,180,0,1.5f);
        Elevators elevators=new Elevators(TexturedModelElevators,new Vector3f(2790,10,0),0,180,0,1.5f);
        Camera camera = new Camera(plane);

        while(!Display.isCloseRequested()){

            camera.move();
            plane.move();
            //wing1.move();
            destroyed_plane.move();
            //wing2.move();
            //elevators.move();
            if(!Plane.isIsDestroyed()) {
                renderer.processEntity(plane);
                //renderer.processEntity(wing1);
                //renderer.processEntity(wing2);
                //renderer.processEntity(elevators);

            }
            else {
                renderer.processEntity(destroyed_plane);
            }
            for (Terrain terrain : terrains) {
                renderer.processTerrain(terrain);
            }

            for (Entity e:entities) {
                renderer.processEntity(e);
            }
            renderer.render(light,camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }


}

