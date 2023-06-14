package com.syberry.school.dto;

import com.syberry.school.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class NewsDto {

    private Long id;
    private String header;
    private String date;
    private String description;
    private byte[] image;
    private byte[] previewImage;

    public static NewsDto toDto(News news) {
        NewsDto dto = new NewsDto();
        dto.setDescription(news.getDescription());
        dto.setId(news.getId());
        dto.setHeader(news.getHeader());
        dto.setImage(news.getImage());
        dto.setPreviewImage(news.getPreviewImage());
        return dto;
    }
}
