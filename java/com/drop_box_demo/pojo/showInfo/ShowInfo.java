package com.drop_box_demo.pojo.showInfo;

public class ShowInfo {
    private String source;
    private String label;
    private String artist ;
    private String year_of_creation;
    private String image;

    public ShowInfo(String source, String label, String artist, String year_of_creation, String image) {
        this.source = source;
        this.label = label;
        this.artist = artist;
        this.year_of_creation = year_of_creation;
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear_of_creation() {
        return year_of_creation;
    }

    public void setYear_of_creation(String year_of_creation) {
        this.year_of_creation = year_of_creation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ShowInfo{" +
                "source='" + source + '\'' +
                ", label='" + label + '\'' +
                ", artist='" + artist + '\'' +
                ", year_of_creation='" + year_of_creation + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
