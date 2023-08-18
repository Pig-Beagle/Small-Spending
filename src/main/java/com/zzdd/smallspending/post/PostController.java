package com.zzdd.smallspending.post;

import com.zzdd.smallspending.common.ApiMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping()
    public ResponseEntity<ApiMessage<Boolean>> write(HttpServletRequest request, PostDto postDto) {
        String authorization = request.getHeader("Authorization");
        int result = postService.write(authorization, postDto);
        if (result != 1) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "작성하기 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "작성하기 성공", true));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiMessage<List<PostDto>>> listAll(PageDto pageDto) {
        List<PostDto> list = postService.listAll(pageDto);
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "조회하기 성공", list));
    }

    @GetMapping("/list/{no}")
    public ResponseEntity<ApiMessage<List<PostDto>>> listByNo(@PathVariable("no") int memberNo, HttpServletRequest request, PageDto pageDto) {
        String authorization = request.getHeader("Authorization");
        pageDto.setMemberNo(memberNo);
        List<PostDto> list = postService.listByNo(authorization, pageDto);
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "조회하기 성공", list));
    }

    @PatchMapping()
    public ResponseEntity<ApiMessage<Boolean>> editUserPost(HttpServletRequest request, PostDto postDto) {
        String authorization = request.getHeader("Authorization");
        int result = postService.editUserPost(authorization, postDto);
        if (result != 1) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "수정하기 실패", false));
        }
        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "수정하기 성공", true));
    }

    @DeleteMapping()
    public ResponseEntity<ApiMessage<Boolean>> deleteUserPost(HttpServletRequest request, PostDto postDto) {
        String authorization = request.getHeader("Authorization");
        int result = postService.deleteUserPost(authorization, postDto);
        if (result != 1) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "삭제 실패", false));
        }

        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "삭제 성공", true));
    }

    @GetMapping("statistics")
    public ResponseEntity<ApiMessage<List<StatisticsDto>>> userStatistics(HttpServletRequest request, StatisticsRequestDto statisticsRequestDto) {
        String authorization = request.getHeader("Authorization");
        List<StatisticsDto> list = postService.userStatistics(authorization, statisticsRequestDto);
        if (list == null) {
            return ResponseEntity.badRequest().body(new ApiMessage<>(HttpStatus.BAD_REQUEST, "조회 실패", list));
        }

        return ResponseEntity.ok(new ApiMessage<>(HttpStatus.OK, "조회 성공", list));
    }




}
