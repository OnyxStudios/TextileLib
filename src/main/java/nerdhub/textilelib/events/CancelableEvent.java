package nerdhub.textilelib.events;

public abstract class CancelableEvent implements Event {

    private boolean isCanceled = false;

    public boolean isCanceled() {
        return isCanceled;
    }

    public void cancelEvent() {
        isCanceled = true;
    }
}
