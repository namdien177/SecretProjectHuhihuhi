package DataObject.Message;

public class MessageClass {
    private String TextContent;
    private String ImageURL;
    private String ImageDesc;
    private String TextID;
    private String ImageID;

    public MessageClass() {

    }

    public MessageClass(String textContent, String imageURL, String imageDesc, String textID, String imageID) {
        TextContent = textContent;
        ImageURL = imageURL;
        ImageDesc = imageDesc;
        TextID = textID;
        ImageID = imageID;
    }

    public String getTextContent() {
        return TextContent;
    }

    public void setTextContent(String textContent) {
        TextContent = textContent;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getImageDesc() {
        return ImageDesc;
    }

    public void setImageDesc(String imageDesc) {
        ImageDesc = imageDesc;
    }

    public String getTextID() {
        return TextID;
    }

    public void setTextID(String textID) {
        TextID = textID;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }
}
