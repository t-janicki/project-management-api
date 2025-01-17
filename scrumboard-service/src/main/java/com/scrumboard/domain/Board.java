package com.scrumboard.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String uri;

    @Column
    private Boolean isDeleted;

    @Column
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private BoardType boardType;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private BoardSettings boardSettings;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BoardList> lists;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Card> cards;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Label> labels;

    public Board() {
    }

    public Board(Long id, String name, String description, String uri, BoardSettings boardSettings,
                 List<BoardList> lists, List<Card> cards, List<Label> labels) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.uri = uri;
        this.boardSettings = boardSettings;
        this.lists = lists;
        this.cards = cards;
        this.labels = labels;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void isDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BoardType getBoardType() {
        return boardType;
    }

    public void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }

    public BoardSettings getBoardSettings() {
        return boardSettings;
    }

    public void setBoardSettings(BoardSettings boardSettings) {
        this.boardSettings = boardSettings;
    }

    public List<BoardList> getLists() {
        return lists;
    }

    public void setLists(List<BoardList> lists) {
        this.lists = lists;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

}
