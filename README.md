## Light Me Up: Minecraft Torch Helmets!
This mod adds a torch version to the iron, gold and diamond helmets!  
Same armor value but lights up the world around you!  

#### Todo: 

#### Dynamic lighting SHELVED! 
#### Focus on armor model!

- [x] remove all the camouflage block stuff

- [ ] **Iron version**
  - [x] Basic item with texture
  - [x] Can be equipped  
  - [ ] Equipped texture
  - [x] Recipes added  
- [ ] **Gold version**
  - [x] Item texture
  - [ ] Equipped texture
  - [x] Mirror iron code to implement
- [ ] **Diamond version**
  - [x] Item texture
  - [ ] Equipped texture
  - [X] Mirror iron code to implement

## Project Setup Instructions
This is a MinecraftForge project.  
See the Forge Documentation online for more detailed instructions:
http://mcforge.readthedocs.io/en/latest/gettingstarted/

**Step 1:**
- git clone `<this repo>`

**Step 2:**
- Eclipse instructions:
  1. Install IntelliJ
  2. Continue below ↓

- IntelliJ instructions:
  1. Open IDEA, select import project.
  2. Select the build.gradle file on the import GUI.
  3. Run `gradlew genIntellijRuns` in the project folder.
  4. Refresh the Gradle Project / restart IDEA if required.
  5. To fix assets not loading in the dev env, edit the Run Configurations:
     1. In the "Before Launch" zone, add task (click the plus).
     2. Int he "Add New Task" dialog, select "Build Artifacts".
     3. Select and add the "assets" artifact.
