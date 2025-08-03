package d3.buggr.models;

public class Embed {

    public String title;

    public String description;

    public String colors;

    public String footer;

    public String thumbnailURL;

    public String imageURL;

    public Embed(String title, String description, String colors, String footer, String thumbnailURL, String imageURL) {
        this.title = title;
        this.description = description;
        this.colors = colors;
        this.footer = footer;
        this.thumbnailURL = thumbnailURL;
        this.imageURL = imageURL;
    }

}
