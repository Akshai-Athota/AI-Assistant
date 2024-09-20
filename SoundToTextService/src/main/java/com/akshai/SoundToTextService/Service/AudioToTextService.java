package com.akshai.SoundToTextService.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AudioToTextService {
    String convertAudioToText(MultipartFile audioFile) throws IOException;
}
