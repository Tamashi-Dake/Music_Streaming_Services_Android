package huce.fit.mvvmpattern.views.fragments.home.itemRandom;

public class RandomTrack {

//    set ảnh từ drawable nên cần resourceId
    private int resourceId;
private String title;

    public RandomTrack(int resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
