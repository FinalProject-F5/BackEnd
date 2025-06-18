package com.finalproject.Backend.util;

import com.finalproject.Backend.dto.ImageDTO;

import java.util.List;

public class ImageValidator {

    private static final List<String> ALLOWED_IMAGE_TYPES = List.of(
        "image/jpeg",
        "image/jpg",
        "image/png"
    );


    public static void validateImages(List<ImageDTO> images) {
        for (ImageDTO image : images) {
            if (!ALLOWED_IMAGE_TYPES.contains(image.getType())) {
                throw new IllegalArgumentException("Tipo de archivo no permitido: " + image.getType());
            } 
        }
    }
}
