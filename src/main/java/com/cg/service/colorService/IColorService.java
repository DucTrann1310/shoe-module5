package com.cg.service.colorService;

import com.cg.model.Color;
import com.cg.service.IGeneralService;

public interface IColorService extends IGeneralService<Color, Long> {

    Color findColorByName(String name);
}
