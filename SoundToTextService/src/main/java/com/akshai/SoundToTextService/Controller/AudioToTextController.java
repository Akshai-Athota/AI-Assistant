package com.akshai.SoundToTextService.Controller;

import com.akshai.SoundToTextService.Service.AudioToTextService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/audio-text")
@Tag(name = "Audio-to-Text Service", description = "Convert audio files to text using OpenAI API")
public class AudioToTextController {

    private AudioToTextService audioToTextService;

    public AudioToTextController(AudioToTextService audioToTextService) {
        this.audioToTextService = audioToTextService;
    }

    @PostMapping
    @Operation(summary = "Convert audio file to text", description = "Provide the file path to an audio file, and the text transcription will be published")
    public ResponseEntity<String> convertAudio(@RequestParam("file") MultipartFile audioFile){
        try {
            return ResponseEntity.ok(audioToTextService.convertAudioToText(audioFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
