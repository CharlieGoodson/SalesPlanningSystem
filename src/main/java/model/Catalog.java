package model;

public class Catalog {

    private int id;
    private String ref;
    private String title;

    public Catalog(String ref, String title) {
        this.ref = ref;
        this.title = title;
    }

    public Catalog(int id, String ref, String title) {
        this.id = id;
        this.ref = ref;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return id + " | " + ref + " | " + title;
    }
}
