package com.hr_handlers.board.controller;

import com.hr_handlers.board.dto.CommentActionResponseDto;
import com.hr_handlers.board.dto.CommentRequestDto;
import com.hr_handlers.board.dto.CommentResponseDto;
import com.hr_handlers.board.service.CommentService;
import com.hr_handlers.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 특정 게시글의 댓글 조회
    @GetMapping("/post/{post_id}/comment")
    public SuccessResponse<List<CommentResponseDto>> getCommentsByPost(@PathVariable Long post_id) {
        return commentService.getCommentsByPost(post_id);
    }

    // 특정 게시글에 댓글 작성
    @PostMapping("/post/{post_id}/comment")
    public SuccessResponse<CommentActionResponseDto> createComment(
            @PathVariable Long post_id,
            @RequestBody CommentRequestDto request,
            Authentication authentication
    ) {
        return commentService.createComment(post_id, request, authentication.getName());
    }

    /*

    // 특정 댓글 수정
    @PutMapping("/comment/{id}")
    public SuccessResponse<CommentActionResponseDto> updateComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDto request,
            Authentication authentication
    ) {
        return commentService.updateComment(id, request, authentication.getName());
    }

    // 특정 댓글 삭제
    @DeleteMapping("/comment/{id}")
    public SuccessResponse<String> deleteComment(@PathVariable Long id, Authentication authentication) {
        commentService.deleteComment(id, authentication.getName());
        return SuccessResponse.of("댓글 삭제 성공", "댓글이 삭제되었습니다.");
    }

    // 대댓글 조회
    @GetMapping("/reply/{pcomment_id}")
    public SuccessResponse<List<CommentResponseDto>> getRepliesByComment(@PathVariable Long pcomment_id) {
        return commentService.getRepliesByComment(pcomment_id);
    }

    // 대댓글 작성
    @PostMapping("/reply/{pcomment_id}")
    public SuccessResponse<CommentActionResponseDto> createReply(
            @PathVariable Long pcomment_id,
            @RequestBody CommentRequestDto request,
            Authentication authentication
    ) {
        return commentService.createReply(pcomment_id, request, authentication.getName());
    }

    */
}
