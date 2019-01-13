package nerdhub.textilelib.eventhandlers;

import nerdhub.textilelib.events.Event;

public abstract class CancelableEvent implements Event {

    private boolean isCanceled = false;

    public boolean isCanceled() {
        return isCanceled;
    }

    public void cancelEvent() {
        isCanceled = true;
    }
}
