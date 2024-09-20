package com.akshai.SoundToTextService.Service.Impl;

import com.akshai.SoundToTextService.Service.AudioToTextService;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AudioToTextImpl implements AudioToTextService {

    private OpenAiAudioTranscriptionModel openAIModel;

    @Value("${spring.ai.openai.api-key}")
    private String openAiKey;

    public AudioToTextImpl(){
        OpenAiAudioApi audioTextApi = new OpenAiAudioApi(this.openAiKey);
        this.openAIModel=new OpenAiAudioTranscriptionModel(audioTextApi);
    }

    @Override
    public String convertAudioToText(MultipartFile audioFile) throws IOException {
        System.out.println("I am in service");
        File tempAudioFile = File.createTempFile("audio",".wav");
        audioFile.transferTo(tempAudioFile);

        OpenAiAudioTranscriptionOptions transcriptionOptions = OpenAiAudioTranscriptionOptions.builder()
                .withResponseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .withLanguage("en")
                .withTemperature(0f)
                .build();

        FileSystemResource audioFinalFile= new FileSystemResource(tempAudioFile);

        AudioTranscriptionPrompt transcriptionRequest = new AudioTranscriptionPrompt(audioFinalFile, transcriptionOptions);
        AudioTranscriptionResponse response = openAIModel.call(transcriptionRequest);

        tempAudioFile.delete();

        return response.getResult().getOutput();
    }
}
