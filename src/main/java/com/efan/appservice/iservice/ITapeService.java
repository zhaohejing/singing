package com.efan.appservice.iservice;

import com.efan.controller.inputs.MySongsInput;
import com.efan.core.primary.MySongs;

/**
 * 录音service
 */
public interface ITapeService {
    MySongs CreateMySongs(MySongsInput input);
}
