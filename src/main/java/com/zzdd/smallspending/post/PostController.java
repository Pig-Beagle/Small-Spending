package com.zzdd.smallspending.post;

import com.zzdd.smallspending.common.ApiMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 작성하기", description = "게시글 작성하기 메소드 입니다.")
    @PostMapping()
    public ResponseEntity<ApiMessage<Boolean>> write(HttpServletRequest request, @RequestBody @Validated PostRequestDto.Post postDto) {
        String authorization = request.getHeader("Authorization");
        int result = postService.write(authorization, postDto);
        if (result != 1) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "작성하기 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "작성하기 성공", true));
    }

    @Operation(summary = "게시글 조회하기", description = "게시글 조회하기 메소드 입니다.")
    @GetMapping("/list")
    public ResponseEntity<ApiMessage<List<PostResponseDto.Post>>> listAll(PostRequestDto.Page pageDto) {
        List<PostResponseDto.Post> list = postService.listAll(pageDto);
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "조회하기 성공", list));
    }

    @Operation(summary = "내가 작성한 게시글 조회하기", description = "내가 작성한 게시글 조회하기 메소드 입니다.")
    @GetMapping("/list/{no}")
    public ResponseEntity<ApiMessage<List<PostResponseDto.Post>>> listByNo(@PathVariable("no") int memberNo, HttpServletRequest request, PostRequestDto.Page pageDto) {
        String authorization = request.getHeader("Authorization");
        pageDto.setMemberNo(memberNo);
        List<PostResponseDto.Post> list = postService.listByNo(authorization, pageDto);
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "조회하기 성공", list));
    }

    @Operation(summary = "게시글 수정하기", description = "게시글 수정하기 메소드 입니다.")
    @PatchMapping()
    public ResponseEntity<ApiMessage<Boolean>> editUserPost(HttpServletRequest request, @RequestBody @Validated PostRequestDto.Post postDto) {
        String authorization = request.getHeader("Authorization");
        int result = postService.editUserPost(authorization, postDto);
        if (result != 1) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "수정하기 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "수정하기 성공", true));
    }

    @Operation(summary = "게시글 삭제하기", description = "게시글 삭제하기 메소드 입니다.")
    @DeleteMapping()
    public ResponseEntity<ApiMessage<Boolean>> deleteUserPost(HttpServletRequest request, @RequestBody PostRequestDto.Delete postDto) {
        String authorization = request.getHeader("Authorization");
        int result = postService.deleteUserPost(authorization, postDto);
        if (result != 1) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "삭제 실패", false));
        }

        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "삭제 성공", true));
    }

    @Operation(summary = "지출 통계", description = "지출 통계 메소드 입니다.")
    @GetMapping("/statistics")
    public ResponseEntity<ApiMessage<List<PostResponseDto.Statistics>>> userStatistics(HttpServletRequest request, PostRequestDto.Statistics statistics) {
        String authorization = request.getHeader("Authorization");
        List<PostResponseDto.Statistics> list = postService.userStatistics(authorization, statistics);
        if (list == null) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "조회 실패", list));
        }

        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "조회 성공", list));
    }

    @Operation(summary = "게시글 반응", description = "게시글 반응 메소드 입니다.")
    @PostMapping("/reaction")
    public ResponseEntity<ApiMessage<Boolean>> addReaction(HttpServletRequest request, @RequestBody PostRequestDto.Reaction reactionDto) {
        String authorization = request.getHeader("Authorization");
        int result = postService.addReaction(authorization, reactionDto);
        if (result != 1) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "좋아요 실패", false));
        }

        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "좋아요 성공", true));
    }

    @Operation(summary = "게시글 반응 취소", description = "게시글 반응 취소 메소드 입니다.")
    @DeleteMapping("/reaction")
    public ResponseEntity<ApiMessage<Boolean>> deleteReaction(HttpServletRequest request, @RequestBody PostRequestDto.Reaction reactionDto) {
        String authorization = request.getHeader("Authorization");
        int result = postService.deleteReaction(authorization, reactionDto);
        if (result != 1) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "좋아요 취소 실패", false));
        }

        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "좋아요 취소 성공", true));
    }



}
