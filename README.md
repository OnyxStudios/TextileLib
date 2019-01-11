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

<style>
code {
  display: block;
  white-space: pre-wrap   
}
</style>

<code>
@EventSubscriber
public void onEntitySpawned(EntitySpawnedEvent event) {
	System.out.println(event.getEntity().getName() + " Has been spawned in the world!");
}
</code><br>
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