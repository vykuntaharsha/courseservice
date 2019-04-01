package com.harsha.cloudcomputing.courseservice.service;

import java.util.List;
import java.util.UUID;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.harsha.cloudcomputing.courseservice.datamodel.Board;
import com.harsha.cloudcomputing.courseservice.datamodel.DynamoDBConnector;

/**
 * BoardService
 */
public class BoardService {

    DynamoDBMapper mapper;

    public BoardService() {
        DynamoDBConnector.init();
        mapper = new DynamoDBMapper(new DynamoDBConnector().getClient());
    }

    public Board createBoard(Board board) {
        String boardId = "board-" + UUID.randomUUID();
        board.setBoardId(boardId);
        mapper.save(board);
        return board;
    }

    public List<Board> getBoards() {
        PaginatedList<Board> boards = mapper.scan(Board.class, new DynamoDBScanExpression());
        boards.loadAllResults();
        return boards;
    }

    public Board getBoard(String id) {
        return mapper.load(Board.class, id);
    }

    public Board updateBoard(String id, Board board) {
        Board boardToUpdate = getBoard(id);
        if (boardToUpdate == null) {
            return null;
        }
        board.setBoardId(boardToUpdate.getBoardId());
        board.setId(boardToUpdate.getId());
        mapper.save(board);
        return board;
    }

    public Board deleteBoard(String id) {
        Board board = getBoard(id);
        if (board == null) {
            return null;
        }
        mapper.delete(board);
        return board;
    }


}
