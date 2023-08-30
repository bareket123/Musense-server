package com.dev.responses;

public class ImageResponse extends BasicResponse{

    String imageUrl;

    public ImageResponse(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageResponse(boolean success, Integer errorCode, String imageUrl) {
        super(success, errorCode);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
