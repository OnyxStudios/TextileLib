# TextileLib
<br>
Simply Simple<br>
<br>
Usage:<br>
Register a class that handles an event using<br>
<code>EventRegister#registerEventHandler(ClassObject);</code><br>
<br>
Use <code>@EventSubscriber</code> on a method that will be called once an event is run<br>
Example:<br>
<br>
<code>
@EventSubscriber<br>
public void onEntitySpawned(EntitySpawnedEvent event) {<br>
	System.out.println(event.getEntity().getName() + " Has been spawned in the world!");<br>
}<br>
</code>
<br>
EventsList:<br>
<br>
BlockBreakEvent<br>
BlockDropsEvent<br>
BlockPlaceEvent<br>
EntitySpawnedEvent<br>
PlayerLeftClick<br>
PlayerInteractEntity<br>
RenderWorldEvent<br>