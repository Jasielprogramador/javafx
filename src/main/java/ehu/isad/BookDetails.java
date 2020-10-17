package ehu.isad;

import java.util.Arrays;

public class BookDetails {

    private String[] publishers;
    private String title;
    private Integer number_of_pages;

    public String[] getPublishers() {
        return publishers;
    }

    public void setPublishers(String[] publishers) {
        this.publishers = publishers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber_of_pages() {
        return number_of_pages;
    }

    public void setNumber_of_pages(Integer number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    @Override
    public String toString() {
        return "BookDetails{" +
                "publishers=" + Arrays.toString(publishers) +
                ", title='" + title + '\'' +
                ", number_of_pages=" + number_of_pages +
                '}';
    }
}