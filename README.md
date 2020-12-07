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