package com.harsha.cloudcomputing.courseservice.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.harsha.cloudcomputing.courseservice.datamodel.Board;
import com.harsha.cloudcomputing.courseservice.service.BoardService;
import io.swagger.annotations.Api;

/**
 * BoardsResource
 */
@Api
@Path("/boards")
public class BoardsResource {

    private BoardService boardService = new BoardService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Board> getBoards() {
        return boardService.getBoards();
    }

    @GET
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Board getBoard(@PathParam("boardId") String boardId) {
        return boardService.getBoard(boardId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Board createBoard(Board board) {
        return boardService.createBoard(board);
    }

    @PUT
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Board updateBoard(@PathParam("boardId") String id, Board board) {
        return boardService.updateBoard(id, board);
    }

    @DELETE
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Board deleteBoard(@PathParam("boardId") String id) {
        return boardService.deleteBoard(id);
    }
}
