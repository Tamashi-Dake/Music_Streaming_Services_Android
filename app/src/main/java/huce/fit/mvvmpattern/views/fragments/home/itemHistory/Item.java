package huce.fit.mvvmpattern.views.fragments.home.itemHistory;

public class Item {

//    set ảnh từ drawable nên cần resouceId
    private int resouceId;
private String title;

    public Item(int resouceId, String title) {
        this.resouceId = resouceId;
        this.title = title;
    }

    public int getResouceId() {
        return resouceId;
    }

    public void setResouceId(int resouceId) {
        this.resouceId = resouceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
