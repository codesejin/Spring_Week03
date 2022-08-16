package com.sparta.week03.controller;

import com.sparta.week03.domain.Memo;
import com.sparta.week03.domain.MemoRepository;
import com.sparta.week03.domain.MemoRequestDto;
import com.sparta.week03.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@RestController
public class MemoController {
    //아래는 멤버변수기 때문에 private으로 선언할 뿐 만 아니라 필수적이기에 final
    // @RequiredArgsConstructor가 필요한 이유는
    // MemoController도 결국 어딘가에서 new MemoController로 객체 생성할텐데 그 작업을 스프링이해줌
    private final MemoRepository memoRepository;
    private final MemoService memoService;
    //생성부분
    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }
    //조회하기
    @GetMapping("/api/memos")
    public List<Memo> readMemo(){
        LocalDateTime now = LocalDateTime.now(); //지금 값
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1); // 오늘-1 = 어제
        return memoRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc( yesterday,now );
        // start에 들어가는 값은 yesterday
        // end에 들어가는 값은 now
    }
    //삭제하기
    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        memoRepository.deleteById(id);
        return id;
    }
    //업데이트하기
    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        memoService.update(id, requestDto);
        return id;
    }
}
