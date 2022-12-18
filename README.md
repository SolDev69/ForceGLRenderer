# ForceGLRenderer
Not to be confused with ForceGL20. This mod tricks Minecraft into thinking you're running a newer version of OpenGL then you really have, please be advised that mod compatibility is iffy at best with mods that affect the renderer.

Fixes for some broken mods:
- Sodium/Rubidium: Add `mixin.features.chunk_rendering=false` to `config/sodium-mixins.properties` or `config/rubidium-mixins.properties`
- Sodium/Rubidium Extra: Add these lines to `config/sodium-extra.properties` and set `snooperEnabled=false` in `options.txt`.
```properties
mixin.adaptive_sync=false
mixin.sodium.vsync=false
```
- Flywheel/Create: Disable rendering optimizations by changing `backend="INSTANCING"` to `backend="OFF"` in `config/flywheel-client.toml` or by opening the Flywheel config menu in game and going to `Configure > Access Configs of other Mods > Flywheel` and changing backend to `OFF` from there
