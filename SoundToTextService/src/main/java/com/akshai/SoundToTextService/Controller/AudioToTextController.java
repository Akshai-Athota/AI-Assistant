package com.akshai.SoundToTextService.Controller;

import com.akshai.SoundToTextService.Service.AudioToTextService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/audio-text")
public class AudioToTextController {

    private AudioToTextService audioToTextService;

    public AudioToTextController(AudioToTextService audioToTextService) {
        this.audioToTextService = audioToTextService;
    }

    @PostMapping
    public ResponseEntity<String> convertAudio(@RequestParam("file") MultipartFile audioFile){
        try {
            return ResponseEntity.ok(audioToTextService.convertAudioToText(audioFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
