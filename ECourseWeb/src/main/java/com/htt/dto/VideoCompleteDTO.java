/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.dto;

import com.htt.pojo.Video;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoCompleteDTO {

    private Date completedDate;
    private VideoDTO video;
    private UserDTO user;
}
