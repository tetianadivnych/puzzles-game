package com.divnych.puzzlesgame.controller;

import com.divnych.puzzlesgame.converter.ImageConverter;
import com.divnych.puzzlesgame.playload.ImageUrlRequest;
import com.divnych.puzzlesgame.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/split")
    public List<String> split(@RequestBody ImageUrlRequest request) throws IOException {
        String stringUrl = request.getImageUrl();
        URL url = new URL(stringUrl);
        List<File> imageFiles = imageService.split(url);
        return imageFiles.stream().map(file -> ImageConverter.convertFile(file)).collect(Collectors.toList());
    }



}


