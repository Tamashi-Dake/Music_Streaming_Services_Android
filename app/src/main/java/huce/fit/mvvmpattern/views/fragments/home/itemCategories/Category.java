package huce.fit.mvvmpattern.views.fragments.home.itemCategories;

public class Category {

//    set ảnh từ drawable nên cần resourceId
    private int resourceId;
private String title;

        public Category(int resourceId, String title) {
            this.resourceId = resourceId;
            this.title = title;
        }

        public int getResourceId() {
            return resourceId;
        }

        public void setResourceId(int resouceId) {
            this.resourceId = resouceId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
}
