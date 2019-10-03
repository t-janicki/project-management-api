package com.utility.dto.settings;

public final class ToolbarDTO {
    private Long id;
    private Boolean display;
    private String style;
    private String position;

    public ToolbarDTO() {

    }

    public ToolbarDTO(Long id, Boolean display, String style, String position) {
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

    public String getStyle() {
        return style;
    }

    public String getPosition() {
        return position;
    }
}