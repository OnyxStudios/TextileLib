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

There are currently 2 ways to register and use events and event handlers

**Method 1:**

Register a class that handles an event using
`EventRegister.INSTANCE.registerEventHandler(ClassObject);`

Use `@EventSubscriber`on a method that will be called once an event is run

Example:
```
@EventSubscriber
public void onEntitySpawned(EntitySpawnedEvent event) {
	System.out.println(event.getEntity().getName() + " Has been spawned in the world!");
}
```

**Method 2:**
Create a method that takes in the event as a parameter. Then call that method using the example below

```
EventRegistry.INSTANCE.registerEventHandler(EventToCall.class, ClassHoldingEventMethod::eventMethodName);
```