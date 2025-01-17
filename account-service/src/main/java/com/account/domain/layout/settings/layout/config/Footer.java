package com.account.domain.layout.settings.layout.config;

import javax.persistence.*;

@Entity
@Table(name = "footer")
public class Footer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "display")
    private Boolean display = Boolean.TRUE;

    @Column(name = "style")
    private String style = "fixed";

    @Column(name = "position")
    private String position = "below";

    public Footer() {

    }

    public Footer(Boolean display, String style, String position) {
        this.display = display;
        this.style = style;
        this.position = position;
    }

    public Footer(Long id, Boolean display, String style, String position) {
        this.id = id;
        this.display = display;
        this.style = style;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
