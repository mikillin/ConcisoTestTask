package de.conciso.entity;

import javax.persistence.*;

@Entity
@Table(name = "HISTORY")
public class History {
    public static int MAX_HISTORY_QUEUE = 20;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "LAT")
    private float lat;
    @Column(name = "LNG")
    private float lng;
    @Column(name = "ZOOM")
    private int zoom;
    @Column(name = "CREATION_TIME")
    private long creationTime;

    public History() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public long getCreationTime() {return creationTime;}

    public void setCreationTime(long creationTime) {this.creationTime = creationTime;}
}
