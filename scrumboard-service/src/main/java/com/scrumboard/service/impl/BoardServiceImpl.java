package com.scrumboard.service.impl;

import com.scrumboard.domain.*;
import com.scrumboard.repository.BoardRepository;
import com.scrumboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardServiceImpl implements BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createNewEmptyBoard(Long userId, String avatarUrl, String name) {
        Board board = new Board();

        Label sampleLabel1 = new Label("High Priority", "bg-red text-white");
        Label sampleLabel2 = new Label("Design", "bg-orange text-white");
        Label sampleLabel3 = new Label("App", "bg-blue text-white");
        Label sampleLabel4 = new Label("Feature", "bg-green text-white");

        Member member = new Member(
                name,
                avatarUrl,
                userId
        );

        board.setName("Untitled Board");
        board.setUri("untitled-board");
        board.isDeleted(Boolean.FALSE);
        board.setUserId(userId);
        board.setBoardSettings(new BoardSettings("", false, true));
        board.setLists(new ArrayList<>());
        board.setCards(new ArrayList<>());
        board.setMembers(Collections.singletonList(member));
        board.setLabels(Arrays.asList(sampleLabel1, sampleLabel2, sampleLabel3, sampleLabel4));

        boardRepository.save(board);

        return board;
    }


    public List<Board> getBoards(Long userId) {
//        return boardRepository.findAllByIsDeletedIsFalseAndUserId(userId);
        return boardRepository.findAll();
    }

    public Board getBoardByIdAndUserId(Long boardId, Long userId) {
//        Board board = boardRepository.findByIdAndIsDeletedIsFalseAndUserId(boardId, userId)
//                .orElseThrow(() -> new NotFoundException("Board not found with id: " + boardId));

        Board board = boardRepository.findById(boardId).get();

        board.getLists().sort(Comparator.comparing(BoardList::getPosition));

        return board;
    }

    public Board renameBoard(Long boardId, Long userId, String name) {
        Board board = getBoardByIdAndUserId(boardId, userId);

        String uri = name.replaceAll(" ","-");

        board.setName(name);
        board.setUri(uri.toLowerCase());

        return boardRepository.save(board);
    }

    @Override
    public BoardSettings updateSettings(Long boardId, Long userId, BoardSettings settings) {
        Board board = getBoardByIdAndUserId(boardId, userId);

        board.getBoardSettings().setCardCoverImages(settings.getCardCoverImages());
        board.getBoardSettings().setColor(settings.getColor());
        board.getBoardSettings().setSubscribed(settings.getSubscribed());

        boardRepository.save(board);

        return board.getBoardSettings();
    }

    public void deleteBoardById(Long boardId, Long userId) {
        Board board = getBoardByIdAndUserId(boardId, userId);

        board.isDeleted(Boolean.TRUE);

        boardRepository.save(board);
    }

    public void deleteAllBoards() {
        boardRepository.deleteAll();
    }

    public void addCardToBoard(Long boardId, Long userId, Card card) {
        Board board = getBoardByIdAndUserId(boardId, userId);

        board.getCards().add(card);

        boardRepository.save(board);
    }

    public Board removeCard(Long boardId, Long userId, Card card) {
        Board board = getBoardByIdAndUserId(boardId, userId);
        board.getCards().remove(card);

        return boardRepository.save(board);
    }

    public Board reorderCards(List<List<String>> cardsLists, Long userId, Long boardId) {
        Board board = getBoardByIdAndUserId(boardId, userId);

        int i = 0;
        for (List<String> ids : cardsLists) {

            String cardsIds = String.join(", ", ids);

            board.getLists().get(i).setCardsIds(cardsIds);

            i++;
        }

        return boardRepository.save(board);
    }
}
