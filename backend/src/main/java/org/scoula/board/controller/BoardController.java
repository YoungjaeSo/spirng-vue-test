package org.scoula.board.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.common.util.UploadFiles;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    final private BoardService service;

    @GetMapping("/list")
    public void list(Model model){
        log.info("list");
        model.addAttribute("list", service.getList());
    }
    @GetMapping("/create")
    public void create(){
        log.info("create");
    }


    @GetMapping({"/get", "update"})
    public void get(@RequestParam("no") Long no, Model model){
        log.info("get or update");
        model.addAttribute("board", service.get(no));
    }

    @PostMapping("/update")
    public String update(BoardDTO board){
        log.info("update" + board);
        service.update(board);
        return "redirect:/board/list";
    }

//==============================================================================//
    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getList(){
        return ResponseEntity.ok(service.getList());
    }

    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> getById(@PathVariable Long no){
        return ResponseEntity.ok(service.get(no));
    }
    @PostMapping("")
    public ResponseEntity<BoardDTO>create(BoardDTO board) {
        return ResponseEntity.ok(service.create(board));
    }
    @PutMapping("/{no}")
    public ResponseEntity<BoardDTO>update(@PathVariable Long no, BoardDTO board) {
        return ResponseEntity.ok(service.update(board));
    }
    @DeleteMapping("/{no}")
    public ResponseEntity<BoardDTO>delete(@PathVariable Long no) {
        return ResponseEntity.ok(service.delete(no));
    }

    @GetMapping("/download/{no}")
    public void download(@PathVariable Long no, HttpServletResponse response)throws Exception{
        BoardAttachmentVO attachment=service.getAttachment(no);
        File file = new File(attachment.getPath());
        UploadFiles.download(response, file,attachment.getFilename());
    }
    @DeleteMapping("/deleteAttachment/{no}")
    public ResponseEntity<Boolean>deleteAttachment(@PathVariable Long no)throws Exception{
        return ResponseEntity.ok(service.deleteAttachment(no));
    }
}
