# TextileLib

Simply Simple Events

Gradle Dependency:

```
repositories {
    maven {
        name = "JitPack"
        url = "https://jitpack.io"
    }
}

dependencies {
    modCompile "com.github.NerdHubMC:TextileLib:master-SNAPSHOT"
}
```

Usage:
Register a class that handles an event using
`EventRegister#registerEventHandler(ClassObject);`

Use `@EventSubscriber`on a method that will be called once an event is run

Example:
```
@EventSubscriber
public void onEntitySpawned(EntitySpawnedEvent event) {
	System.out.println(event.getEntity().getName() + " Has been spawned in the world!");
}
```

EventsList:
- BlockBreakEvent
- BlockDropsEvent
- BlockPlaceEvent
- EntitySpawnedEvent
- PlayerLeftClick
- PlayerInteractEntity
- RenderWorldEvent