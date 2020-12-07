## Light Me Up: Minecraft Torch Helmets!
This mod adds a torch version to the iron, gold and diamond helmets!  
Same armor value but lights up the world around you!  

#### Todo: 
- [ ] **Iron version**
  - [x] Basic item with texture
  - [x] Can be equipped  
  - [ ] Equipped texture
  - [x] Recipes added  
  - [ ] Light up surroundings  
- [ ] **Gold version**
  - [x] Item texture
  - [ ] Equipped texture
  - [x] Mirror iron code to implement
  - [ ] Light up surroundings
- [ ] **Diamond version**
  - [x] Item texture
  - [ ] Equipped texture
  - [X] Mirror iron code to implement
  - [ ] Light up surroundings
  
<hr>

Turns out dynamic lighting is pretty hard.  
Current idea:
- As the player walks, constantly replace the blocks beneath him with a "copy" that has lighting level set to 15.
- The copy has dynamic textures based on [TheGreyGhost's CamouflageBlock](https://github.com/TheGreyGhost/MinecraftByExample/tree/1-12-2-final/src/main/java/minecraftbyexample/mbe04_block_dynamic_block_model1).

<hr>

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
  2. Select your build.gradle on the import GUI.
  3. Run `gradlew genIntellijRuns` in the project folder.
  4. Refresh the Gradle Project in IDEA if required.