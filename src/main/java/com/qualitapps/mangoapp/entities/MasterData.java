package com.qualitapps.mangoapp.entities;

import java.util.Date;
import com.qualitapps.mangoapp.mapper.TranslationMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="master_data")
public class MasterData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer moduleId;

	private String categoryType;
	private String categoryTypeKey;
	private String data;

    @Convert(converter = TranslationMapper.class)
    @Column(columnDefinition = "json")
    private TranslationData translation;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private Boolean isDeleted;
}
