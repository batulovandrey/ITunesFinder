package com.github.batulovandrey.itunesfinder.view;

/**
 * @author Andrey Batulov on 03/10/2017
 */

public interface PlayerView {

    void setSeekBarMax(int max);

    void setSeekBarProgress(int progress);

    void setPlayButtonEnabled(boolean enabled);

    void setPauseButtonEnabled(boolean enabled);
}