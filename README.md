# PokeBallPackFix
Installing a Server-Side Resource Pack currently sometimes messes up the PokeBall sprites for the player. You can fix this by reloading the textures, but then you obviously have to wait for it to re-apply, which is annoying for players. (See https://pixelmonmod.com/tracker.php?p=2&t=20573)

PokeBallPackFix is a small little spigot plugin which aims to work around this bug by temporarily "replacing" Poke Balls in the players inventory with a placeholder "Loading..." item when the Resource Pack is sent from the server, and then restores their Poke Balls when the pack has finished loading. This is done entirely with packets, so their items are safe on the server side and are not actually being removed/replaced- only visually.

Tested on Arclight 1.16- Theoretically should work on any hybrid, 1.16 or 1.20 but has not been tested on others.
Requires Pixelmon, obviously.