package main.data_base.objects.products_and_components.components;

public class Camera {
    private Camera(){}
    /** Сделать свойства переменных объекта обязательно заполняемыми! */

    private int code;
    private String mainCamera;
    private String frontCamera;
    private String recordVideo;
    private String typeSpark;

    public int getCode() {
        return code;
    }

    public String getMainCamera() {
        return mainCamera;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public String getRecordVideo() {
        return recordVideo;
    }

    public String getTypeSpark() {
        return typeSpark;
    }

    public static Builder newBuilder(){
        return new Camera().new Builder();
    }

    public class Builder{

        private Builder(){}

        public Camera build(){
            return Camera.this;
        }

        public Builder setCode(int code) {
            Camera.this.code = code;
            return this;
        }

        public Builder setMainCamera(String mainCamera) {
            Camera.this.mainCamera = mainCamera;
            return this;
        }

        public Builder setFrontCamera(String frontCamera) {
            Camera.this.frontCamera = frontCamera;
            return this;
        }

        public Builder setRecordVideo(String recordVideo) {
            Camera.this.recordVideo = recordVideo;
            return this;
        }

        public Builder setTypeSpark(String typeSpark) {
            Camera.this.typeSpark = typeSpark;
            return this;
        }

    }
}

