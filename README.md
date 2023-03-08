# Hold your ground
This plugin is designed to help Tilemen and Chunkmen stay within their unlocked boundaries.

The plugin will restrict actions that could cause you to leave your allowed region. It does this by enforcing customisable levels of restriction on your player character which control how your character interacts with NPCs.

Both distance and line of sight are considered when determining whether you can perform your desired interaction. This helps to ensure your player character does not path towards an NPC that is not targetable by your attack or interaction.

Disclaimer: Unfortunately it's not possible to completely lock down a players movement so don't hold me to accidentally breaking your tileman rules

## Configuration
### Max Tile Range
Default value: 1

This value allows you to customise how far your character will accept inputs for an interaction.

It's important to set this value to the max range of the interaction type you are performing. I've made a little table below for the varieties of ranges but it is incomplete so please experiment.

**Weapon**| **Range**
:-----:|:-----:
Talk-to| 1
Pickpocket| 1
Salamander| 1
Shortbow| 7
Shortbow (Defensive)| 7
Crossbow| 7
Powered staff (i.e Trident)| 7
Crossbow (Defensive)| 9
Armadyl crossbow| 8
Armadyl crossbow (defensive)| 10
Magic spell| 10
Use item| NPC render distance I guess?

### Hold your attacks

This toggle will determine whether the left/right click option "Attack" is held back for NPCs that are outside of your Max Tile Range.

It's useful to set this if you are trying to melee enemies without being dragged off of your tiles, or trying to range distant enemies.

### Hold your spells

This toggle will determine whether the left/right click option for "Cast" is held back for NPCs that are outside of your Max Tile Range.

It's useful when single casting NPCs to lure them towards you.

### Hold your conversations

This toggle will determine whether the left/right click option for "Talk-to" is held back for NPCs that are outside of your Max Tile Range.

Useful for interacting with non-attackable NPCs, in shops for example.

### Hold your trades

This toggle will determine whether the left/right click option for "Trade" is held back for NPCs that are outside of your Max Tile Range.

Useful for interacting with shop keepers.

### Hold your pickpockets

This toggle will determine whether the left/right click option for "Pickpocket" is held back for NPCs that are outside of your Max Tile Range.

With a Max Tile Range of 1 this toggle will stop you from moving diagonally to pickpocket people, good if you've only got one tile for example.

### Hold your uses

This toggle will determine whether the left/right click option for "Use" is held back for NPCs that are outside of your Max Tile Range.

Possibly useful in quests where you need to deliver items??

### Hold your horses

This toggle will stop the player character from being able to move via clicking on the game world tiles.

Useful if you are spam clicking on an NPC as it's walking around to ensure you don't miss the chance to start attacking it.

### Require stationary

This toggle will only allow clicks on NPCs if they are currently on their idle animation.

Useful if you don't want to be dragged out of your unlocked area by a NPC forcing you to move during an interaction.

### Show overlay

This toggle will show/hide a tile marker below the mouse cursor that will change colour based on whether that tile has line of sight and is within your Max Tile Range.

Useful to see where your Max Tile Range boundaries are.

### Show chat messages

This toggle will show/hide the messages added to chat for when a click has been disabled for breaking one of the restrictions you have configured.

Useful to know what restriction is causing your clicks to not work.









